CREATE TABLE AppUsers
(
  UserId       NVARCHAR(128) NOT NULL
    CONSTRAINT PK_AppUsers
    PRIMARY KEY,
  UserName     NVARCHAR(256) NOT NULL,
  PasswordHash NVARCHAR(MAX) NOT NULL,
  Email        NVARCHAR(256) NOT NULL
)
GO

CREATE TABLE sysdiagrams
(
  name         SYSNAME NOT NULL,
  principal_id INT     NOT NULL,
  diagram_id   INT IDENTITY
    PRIMARY KEY,
  version      INT,
  definition   VARBINARY(MAX),
  CONSTRAINT UK_principal_name
  UNIQUE (principal_id, name)
)
GO

CREATE TABLE AppRoles
(
  RoleId   INT IDENTITY
    CONSTRAINT PK_AppRoles
    PRIMARY KEY,
  RoleName NCHAR(20) NOT NULL
)
GO

CREATE TABLE AppUserRoles
(
  RoleId INT           NOT NULL
    CONSTRAINT FK_AppUserRoles_AppRoles
    REFERENCES AppRoles,
  UserId NVARCHAR(128) NOT NULL
    CONSTRAINT FK_AppUserRoles_AppUsers
    REFERENCES AppUsers,
  CONSTRAINT PK_AppUserRoles
  PRIMARY KEY (RoleId, UserId)
)
GO

CREATE TABLE UserProfile
(
  UserId  NVARCHAR(128) NOT NULL
    CONSTRAINT PK_UserProfile
    PRIMARY KEY
    CONSTRAINT FK_UserProfile_AppUsers
    REFERENCES AppUsers,
  Name    NVARCHAR(128) NOT NULL,
  Surname NVARCHAR(128) NOT NULL,
  MidName NVARCHAR(128)
)
GO

CREATE TABLE Students
(
  UserId    NVARCHAR(128) NOT NULL
    CONSTRAINT PK_Students
    PRIMARY KEY
    CONSTRAINT FK_Students_AppUsers
    REFERENCES AppUsers,
  StudentId INT IDENTITY
)
GO

CREATE UNIQUE INDEX IX_Students
  ON Students (StudentId)
GO

CREATE TABLE StudentProfile
(
  StudentId INT NOT NULL
    CONSTRAINT PK_StudentProfile
    PRIMARY KEY
)
GO

ALTER TABLE Students
  ADD CONSTRAINT FK_Students_StudentProfile
FOREIGN KEY (StudentId) REFERENCES StudentProfile
GO

CREATE TABLE YearOfStudy
(
  Id          INT IDENTITY
    CONSTRAINT PK_YearOfStudy
    PRIMARY KEY,
  YearName    NVARCHAR(128) NOT NULL,
  Description NVARCHAR(MAX)
)
GO

CREATE TABLE Subjects
(
  Id          INT IDENTITY
    CONSTRAINT PK_Subjects
    PRIMARY KEY,
  SubjectName NVARCHAR(128) NOT NULL,
  YearOfStudy INT           NOT NULL,
  Description NVARCHAR(128)
)
GO

CREATE TABLE YearOfStudySubjects
(
  SubjectId     INT NOT NULL
    CONSTRAINT FK_YearOfStudySubjects_Subjects
    REFERENCES Subjects,
  YearOfStudyId INT NOT NULL
    CONSTRAINT FK_YearOfStudySubjects_YearOfStudy
    REFERENCES YearOfStudy,
  CONSTRAINT PK_YearOfStudySubjects
  PRIMARY KEY (SubjectId, YearOfStudyId)
)
GO

CREATE TABLE StudentEnrollment
(
  StudentId INT NOT NULL
    CONSTRAINT FK_StudentEnrollment_Students
    REFERENCES Students (StudentId),
  GroupId   INT NOT NULL,
  CONSTRAINT PK_StudentEnrollment
  PRIMARY KEY (StudentId, GroupId)
)
GO

CREATE TABLE Group
(
  Id            INT IDENTITY
    CONSTRAINT PK_Group
    PRIMARY KEY,
  YearOfStudyId INT           NOT NULL
    CONSTRAINT FK_Group_YearOfStudy
    REFERENCES YearOfStudy,
  GroupName     NVARCHAR(128) NOT NULL,
  Description   NVARCHAR(128),
  IsActive      BIT           NOT NULL
)
GO

ALTER TABLE StudentEnrollment
  ADD CONSTRAINT FK_StudentEnrollment_Group
FOREIGN KEY (GroupId) REFERENCES Group
GO

CREATE TABLE StudentGrades
(
  StudentId INT NOT NULL
    CONSTRAINT FK_StudentGrades_Students
    REFERENCES Students (StudentId),
  SubjectId INT NOT NULL
    CONSTRAINT FK_StudentGrades_Subjects
    REFERENCES Subjects,
  Grade     REAL,
  CONSTRAINT PK_StudentGrades
  PRIMARY KEY (StudentId, SubjectId)
)
GO


CREATE PROCEDURE dbo.sp_upgraddiagrams
AS
  BEGIN
    IF OBJECT_ID(N'dbo.sysdiagrams') IS NOT NULL
      RETURN 0;

    CREATE TABLE dbo.sysdiagrams
    (
      name         SYSNAME NOT NULL,
      principal_id INT     NOT NULL, -- we may change it to varbinary(85)
      diagram_id   INT PRIMARY KEY IDENTITY,
      version      INT,

      definition   VARBINARY(MAX)
        CONSTRAINT UK_principal_name UNIQUE
          (
            principal_id,
            name
          )
    );


    /* Add this if we need to have some form of extended properties for diagrams */
    /*
    IF OBJECT_ID(N'dbo.sysdiagram_properties') IS NULL
    BEGIN
      CREATE TABLE dbo.sysdiagram_properties
      (
        diagram_id int,
        name sysname,
        value varbinary(max) NOT NULL
      )
    END
    */

    IF OBJECT_ID(N'dbo.dtproperties') IS NOT NULL
      BEGIN
        INSERT INTO dbo.sysdiagrams
        (
          [name],
          [principal_id],
          [version],
          [definition]
        )
          SELECT
            convert(SYSNAME, dgnm.[uvalue]),
            DATABASE_PRINCIPAL_ID(N'dbo'),
            -- will change to the sid of sa
            0,
            -- zero for old format, dgdef.[version],
            dgdef.[lvalue]
          FROM dbo.[dtproperties] dgnm
            INNER JOIN dbo.[dtproperties] dggd
              ON dggd.[property] = 'DtgSchemaGUID' AND dggd.[objectid] = dgnm.[objectid]
            INNER JOIN dbo.[dtproperties] dgdef
              ON dgdef.[property] = 'DtgSchemaDATA' AND dgdef.[objectid] = dgnm.[objectid]

          WHERE dgnm.[property] = 'DtgSchemaNAME' AND dggd.[uvalue] LIKE N'_EA3E6268-D998-11CE-9454-00AA00A3F36E_'
        RETURN 2;
      END
    RETURN 1;
  END


GO


CREATE PROCEDURE dbo.sp_helpdiagrams
  (
    @diagramname SYSNAME = NULL,
    @owner_id    INT = NULL
  )
  WITH EXECUTE AS N'dbo'
AS
  BEGIN
    DECLARE @user SYSNAME
    DECLARE @dboLogin BIT
    EXECUTE AS CALLER;
    SET @user = USER_NAME();
    SET @dboLogin = CONVERT(BIT, IS_MEMBER('db_owner'));
    REVERT;
    SELECT
        [Database] = DB_NAME(),
        [Name] = name,
        [ID] = diagram_id,
        [Owner] = USER_NAME(principal_id),
        [OwnerID] = principal_id
    FROM
      sysdiagrams
    WHERE
      (@dboLogin = 1 OR USER_NAME(principal_id) = @user) AND
      (@diagramname IS NULL OR name = @diagramname) AND
      (@owner_id IS NULL OR principal_id = @owner_id)
    ORDER BY
      4, 5, 1
  END


GO


CREATE PROCEDURE dbo.sp_helpdiagramdefinition
  (
    @diagramname SYSNAME,
    @owner_id    INT = NULL
  )
  WITH EXECUTE AS N'dbo'
AS
  BEGIN
    SET NOCOUNT ON

    DECLARE @theId INT
    DECLARE @IsDbo INT
    DECLARE @DiagId INT
    DECLARE @UIDFound INT

    IF (@diagramname IS NULL)
      BEGIN
        RAISERROR (N'E_INVALIDARG', 16, 1);
        RETURN -1
      END

    EXECUTE AS CALLER;
    SELECT @theId = DATABASE_PRINCIPAL_ID();
    SELECT @IsDbo = IS_MEMBER(N'db_owner');
    IF (@owner_id IS NULL)
      SELECT @owner_id = @theId;
    REVERT;

    SELECT
      @DiagId = diagram_id,
      @UIDFound = principal_id
    FROM dbo.sysdiagrams
    WHERE principal_id = @owner_id AND name = @diagramname;
    IF (@DiagId IS NULL OR (@IsDbo = 0 AND @UIDFound <> @theId))
      BEGIN
        RAISERROR ('Diagram does not exist or you do not have permission.', 16, 1);
        RETURN -3
      END

    SELECT
      version,
      definition
    FROM dbo.sysdiagrams
    WHERE diagram_id = @DiagId;
    RETURN 0
  END


GO


CREATE PROCEDURE dbo.sp_creatediagram
  (
    @diagramname SYSNAME,
    @owner_id    INT = NULL,
    @version     INT,
    @definition  VARBINARY(MAX)
  )
  WITH EXECUTE AS 'dbo'
AS
  BEGIN
    SET NOCOUNT ON

    DECLARE @theId INT
    DECLARE @retval INT
    DECLARE @IsDbo INT
    DECLARE @userName SYSNAME
    IF (@version IS NULL OR @diagramname IS NULL)
      BEGIN
        RAISERROR (N'E_INVALIDARG', 16, 1);
        RETURN -1
      END

    EXECUTE AS CALLER;
    SELECT @theId = DATABASE_PRINCIPAL_ID();
    SELECT @IsDbo = IS_MEMBER(N'db_owner');
    REVERT;

    IF @owner_id IS NULL
      BEGIN
        SELECT @owner_id = @theId;
      END
    ELSE
      BEGIN
        IF @theId <> @owner_id
          BEGIN
            IF @IsDbo = 0
              BEGIN
                RAISERROR (N'E_INVALIDARG', 16, 1);
                RETURN -1
              END
            SELECT @theId = @owner_id
          END
      END
    -- next 2 line only for test, will be removed after define name unique
    IF EXISTS(SELECT diagram_id
              FROM dbo.sysdiagrams
              WHERE principal_id = @theId AND name = @diagramname)
      BEGIN
        RAISERROR ('The name is already used.', 16, 1);
        RETURN -2
      END

    INSERT INTO dbo.sysdiagrams (name, principal_id, version, definition)
    VALUES (@diagramname, @theId, @version, @definition);

    SELECT @retval = @@IDENTITY
    RETURN @retval
  END


GO


CREATE PROCEDURE dbo.sp_renamediagram
  (
    @diagramname     SYSNAME,
    @owner_id        INT = NULL,
    @new_diagramname SYSNAME

  )
  WITH EXECUTE AS 'dbo'
AS
  BEGIN
    SET NOCOUNT ON
    DECLARE @theId INT
    DECLARE @IsDbo INT

    DECLARE @UIDFound INT
    DECLARE @DiagId INT
    DECLARE @DiagIdTarg INT
    DECLARE @u_name SYSNAME
    IF ((@diagramname IS NULL) OR (@new_diagramname IS NULL))
      BEGIN
        RAISERROR ('Invalid value', 16, 1);
        RETURN -1
      END

    EXECUTE AS CALLER;
    SELECT @theId = DATABASE_PRINCIPAL_ID();
    SELECT @IsDbo = IS_MEMBER(N'db_owner');
    IF (@owner_id IS NULL)
      SELECT @owner_id = @theId;
    REVERT;

    SELECT @u_name = USER_NAME(@owner_id)

    SELECT
      @DiagId = diagram_id,
      @UIDFound = principal_id
    FROM dbo.sysdiagrams
    WHERE principal_id = @owner_id AND name = @diagramname
    IF (@DiagId IS NULL OR (@IsDbo = 0 AND @UIDFound <> @theId))
      BEGIN
        RAISERROR ('Diagram does not exist or you do not have permission.', 16, 1)
        RETURN -3
      END

    -- if((@u_name is not null) and (@new_diagramname = @diagramname))	-- nothing will change
    --	return 0;

    IF (@u_name IS NULL)
      SELECT @DiagIdTarg = diagram_id
      FROM dbo.sysdiagrams
      WHERE principal_id = @theId AND name = @new_diagramname
    ELSE
      SELECT @DiagIdTarg = diagram_id
      FROM dbo.sysdiagrams
      WHERE principal_id = @owner_id AND name = @new_diagramname

    IF ((@DiagIdTarg IS NOT NULL) AND @DiagId <> @DiagIdTarg)
      BEGIN
        RAISERROR ('The name is already used.', 16, 1);
        RETURN -2
      END

    IF (@u_name IS NULL)
      UPDATE dbo.sysdiagrams
      SET [name] = @new_diagramname, principal_id = @theId
      WHERE diagram_id = @DiagId
    ELSE
      UPDATE dbo.sysdiagrams
      SET [name] = @new_diagramname
      WHERE diagram_id = @DiagId
    RETURN 0
  END


GO


CREATE PROCEDURE dbo.sp_alterdiagram
  (
    @diagramname SYSNAME,
    @owner_id    INT = NULL,
    @version     INT,
    @definition  VARBINARY(MAX)
  )
  WITH EXECUTE AS 'dbo'
AS
  BEGIN
    SET NOCOUNT ON

    DECLARE @theId INT
    DECLARE @retval INT
    DECLARE @IsDbo INT

    DECLARE @UIDFound INT
    DECLARE @DiagId INT
    DECLARE @ShouldChangeUID INT

    IF (@diagramname IS NULL)
      BEGIN
        RAISERROR ('Invalid ARG', 16, 1)
        RETURN -1
      END

    EXECUTE AS CALLER;
    SELECT @theId = DATABASE_PRINCIPAL_ID();
    SELECT @IsDbo = IS_MEMBER(N'db_owner');
    IF (@owner_id IS NULL)
      SELECT @owner_id = @theId;
    REVERT;

    SELECT @ShouldChangeUID = 0
    SELECT
      @DiagId = diagram_id,
      @UIDFound = principal_id
    FROM dbo.sysdiagrams
    WHERE principal_id = @owner_id AND name = @diagramname

    IF (@DiagId IS NULL OR (@IsDbo = 0 AND @theId <> @UIDFound))
      BEGIN
        RAISERROR ('Diagram does not exist or you do not have permission.', 16, 1);
        RETURN -3
      END

    IF (@IsDbo <> 0)
      BEGIN
        IF (@UIDFound IS NULL OR USER_NAME(@UIDFound) IS NULL) -- invalid principal_id
          BEGIN
            SELECT @ShouldChangeUID = 1;
          END
      END

    -- update dds data			
    UPDATE dbo.sysdiagrams
    SET definition = @definition
    WHERE diagram_id = @DiagId;

    -- change owner
    IF (@ShouldChangeUID = 1)
      UPDATE dbo.sysdiagrams
      SET principal_id = @theId
      WHERE diagram_id = @DiagId;

    -- update dds version
    IF (@version IS NOT NULL)
      UPDATE dbo.sysdiagrams
      SET version = @version
      WHERE diagram_id = @DiagId;

    RETURN 0
  END


GO


CREATE PROCEDURE dbo.sp_dropdiagram
  (
    @diagramname SYSNAME,
    @owner_id    INT = NULL
  )
  WITH EXECUTE AS 'dbo'
AS
  BEGIN
    SET NOCOUNT ON
    DECLARE @theId INT
    DECLARE @IsDbo INT

    DECLARE @UIDFound INT
    DECLARE @DiagId INT

    IF (@diagramname IS NULL)
      BEGIN
        RAISERROR ('Invalid value', 16, 1);
        RETURN -1
      END

    EXECUTE AS CALLER;
    SELECT @theId = DATABASE_PRINCIPAL_ID();
    SELECT @IsDbo = IS_MEMBER(N'db_owner');
    IF (@owner_id IS NULL)
      SELECT @owner_id = @theId;
    REVERT;

    SELECT
      @DiagId = diagram_id,
      @UIDFound = principal_id
    FROM dbo.sysdiagrams
    WHERE principal_id = @owner_id AND name = @diagramname
    IF (@DiagId IS NULL OR (@IsDbo = 0 AND @UIDFound <> @theId))
      BEGIN
        RAISERROR ('Diagram does not exist or you do not have permission.', 16, 1)
        RETURN -3
      END

    DELETE FROM dbo.sysdiagrams
    WHERE diagram_id = @DiagId;

    RETURN 0;
  END


GO


CREATE FUNCTION dbo.fn_diagramobjects()
  RETURNS INT
  WITH EXECUTE AS N'dbo'
AS
  BEGIN
    DECLARE @id_upgraddiagrams INT
    DECLARE @id_sysdiagrams INT
    DECLARE @id_helpdiagrams INT
    DECLARE @id_helpdiagramdefinition INT
    DECLARE @id_creatediagram INT
    DECLARE @id_renamediagram INT
    DECLARE @id_alterdiagram INT
    DECLARE @id_dropdiagram INT
    DECLARE @InstalledObjects INT

    SELECT @InstalledObjects = 0

    SELECT
      @id_upgraddiagrams = object_id(N'dbo.sp_upgraddiagrams'),
      @id_sysdiagrams = object_id(N'dbo.sysdiagrams'),
      @id_helpdiagrams = object_id(N'dbo.sp_helpdiagrams'),
      @id_helpdiagramdefinition = object_id(N'dbo.sp_helpdiagramdefinition'),
      @id_creatediagram = object_id(N'dbo.sp_creatediagram'),
      @id_renamediagram = object_id(N'dbo.sp_renamediagram'),
      @id_alterdiagram = object_id(N'dbo.sp_alterdiagram'),
      @id_dropdiagram = object_id(N'dbo.sp_dropdiagram')

    IF @id_upgraddiagrams IS NOT NULL
      SELECT @InstalledObjects = @InstalledObjects + 1
    IF @id_sysdiagrams IS NOT NULL
      SELECT @InstalledObjects = @InstalledObjects + 2
    IF @id_helpdiagrams IS NOT NULL
      SELECT @InstalledObjects = @InstalledObjects + 4
    IF @id_helpdiagramdefinition IS NOT NULL
      SELECT @InstalledObjects = @InstalledObjects + 8
    IF @id_creatediagram IS NOT NULL
      SELECT @InstalledObjects = @InstalledObjects + 16
    IF @id_renamediagram IS NOT NULL
      SELECT @InstalledObjects = @InstalledObjects + 32
    IF @id_alterdiagram IS NOT NULL
      SELECT @InstalledObjects = @InstalledObjects + 64
    IF @id_dropdiagram IS NOT NULL
      SELECT @InstalledObjects = @InstalledObjects + 128

    RETURN @InstalledObjects
  END


GO


