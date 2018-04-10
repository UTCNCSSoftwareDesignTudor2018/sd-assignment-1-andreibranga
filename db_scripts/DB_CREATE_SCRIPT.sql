USE [StudentsDB]
GO
/****** Object:  Table [dbo].[AppRoles]    Script Date: 10/04/2018 17:41:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AppRoles](
	[RoleId] [int] IDENTITY(1,1) NOT NULL,
	[RoleName] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_AppRoles] PRIMARY KEY CLUSTERED 
(
	[RoleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AppUserRoles]    Script Date: 10/04/2018 17:41:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AppUserRoles](
	[RoleId] [int] NOT NULL,
	[UserId] [nvarchar](128) NOT NULL,
 CONSTRAINT [PK_AppUserRoles] PRIMARY KEY CLUSTERED 
(
	[RoleId] ASC,
	[UserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AppUsers]    Script Date: 10/04/2018 17:41:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AppUsers](
	[UserId] [nvarchar](128) NOT NULL,
	[UserName] [nvarchar](256) NOT NULL,
	[PasswordHash] [nvarchar](max) NOT NULL,
	[Email] [nvarchar](256) NOT NULL,
 CONSTRAINT [PK_AppUsers] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Group]    Script Date: 10/04/2018 17:41:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Group](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[YearOfStudyId] [int] NOT NULL,
	[GroupName] [nvarchar](128) NOT NULL,
	[Description] [nvarchar](128) NULL,
	[IsActive] [bit] NOT NULL,
 CONSTRAINT [PK_Group] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[StudentEnrollment]    Script Date: 10/04/2018 17:41:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[StudentEnrollment](
	[StudentId] [int] NOT NULL,
	[GroupId] [int] NOT NULL,
 CONSTRAINT [PK_StudentEnrollment] PRIMARY KEY CLUSTERED 
(
	[StudentId] ASC,
	[GroupId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[StudentGrades]    Script Date: 10/04/2018 17:41:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[StudentGrades](
	[StudentId] [int] NOT NULL,
	[SubjectId] [int] NOT NULL,
	[Grade] [real] NULL,
 CONSTRAINT [PK_StudentGrades] PRIMARY KEY CLUSTERED 
(
	[StudentId] ASC,
	[SubjectId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[StudentProfile]    Script Date: 10/04/2018 17:41:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[StudentProfile](
	[StudentId] [int] NOT NULL,
 CONSTRAINT [PK_StudentProfile] PRIMARY KEY CLUSTERED 
(
	[StudentId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Students]    Script Date: 10/04/2018 17:41:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Students](
	[UserId] [nvarchar](128) NOT NULL,
	[StudentId] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_Students] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Subjects]    Script Date: 10/04/2018 17:41:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subjects](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[SubjectName] [nvarchar](128) NOT NULL,
	[Description] [nvarchar](128) NULL,
 CONSTRAINT [PK_Subjects] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserProfile]    Script Date: 10/04/2018 17:41:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserProfile](
	[UserId] [nvarchar](128) NOT NULL,
	[Name] [nvarchar](128) NOT NULL,
	[Surname] [nvarchar](128) NOT NULL,
	[MidName] [nvarchar](128) NULL,
	[Phone] [nvarchar](50) NOT NULL,
	[Nationality] [nvarchar](50) NOT NULL,
	[Country] [nvarchar](50) NOT NULL,
	[County] [nvarchar](50) NULL,
	[Address] [nvarchar](50) NOT NULL,
	[ZIP] [nvarchar](20) NOT NULL,
 CONSTRAINT [PK_UserProfile] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[YearOfStudy]    Script Date: 10/04/2018 17:41:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[YearOfStudy](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[YearName] [nvarchar](128) NOT NULL,
	[Description] [nvarchar](max) NULL,
 CONSTRAINT [PK_YearOfStudy] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[YearOfStudySubjects]    Script Date: 10/04/2018 17:41:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[YearOfStudySubjects](
	[SubjectId] [int] NOT NULL,
	[YearOfStudyId] [int] NOT NULL,
 CONSTRAINT [PK_YearOfStudySubjects] PRIMARY KEY CLUSTERED 
(
	[SubjectId] ASC,
	[YearOfStudyId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[AppRoles] ON 
GO
INSERT [dbo].[AppRoles] ([RoleId], [RoleName]) VALUES (1, N'teacher ')
GO
INSERT [dbo].[AppRoles] ([RoleId], [RoleName]) VALUES (2, N'student')
GO
SET IDENTITY_INSERT [dbo].[AppRoles] OFF
GO
INSERT [dbo].[AppUserRoles] ([RoleId], [UserId]) VALUES (1, N'1d71d0b5-5549-47e4-9d36-c76baa24812e')
GO
INSERT [dbo].[AppUserRoles] ([RoleId], [UserId]) VALUES (2, N'03e55bfa-3831-408c-8923-b0faa9901e6b')
GO
INSERT [dbo].[AppUserRoles] ([RoleId], [UserId]) VALUES (2, N'06a8eb50-322b-4a26-880c-436c7979645a')
GO
INSERT [dbo].[AppUserRoles] ([RoleId], [UserId]) VALUES (2, N'1e68bb71-506d-48ed-9bd4-9787293e012d')
GO
INSERT [dbo].[AppUserRoles] ([RoleId], [UserId]) VALUES (2, N'3ad5a243-93e8-4cef-8104-1417c2811c78')
GO
INSERT [dbo].[AppUserRoles] ([RoleId], [UserId]) VALUES (2, N'9fc2cb5f-bee8-44c2-939c-1d4ddd0fc5d6')
GO
INSERT [dbo].[AppUserRoles] ([RoleId], [UserId]) VALUES (2, N'd8f0b9fe-f591-40b0-bd35-01a45f310538')
GO
INSERT [dbo].[AppUsers] ([UserId], [UserName], [PasswordHash], [Email]) VALUES (N'03e55bfa-3831-408c-8923-b0faa9901e6b', N'craciunsergiu', N'qwerty123', N'craciunsergiu@gmail.com')
GO
INSERT [dbo].[AppUsers] ([UserId], [UserName], [PasswordHash], [Email]) VALUES (N'06a8eb50-322b-4a26-880c-436c7979645a', N'florinp', N'qwerty123', N'florinp@gmail.com')
GO
INSERT [dbo].[AppUsers] ([UserId], [UserName], [PasswordHash], [Email]) VALUES (N'1d71d0b5-5549-47e4-9d36-c76baa24812e', N'abranga', N'qwerty123', N'abranga96@gmail.com')
GO
INSERT [dbo].[AppUsers] ([UserId], [UserName], [PasswordHash], [Email]) VALUES (N'1e68bb71-506d-48ed-9bd4-9787293e012d', N'mariapreda', N'qwerty123', N'mariaPreda@gmail.com')
GO
INSERT [dbo].[AppUsers] ([UserId], [UserName], [PasswordHash], [Email]) VALUES (N'3ad5a243-93e8-4cef-8104-1417c2811c78', N'ioanapopa', N'qwerty123', N'ioanapopa@gmail.com')
GO
INSERT [dbo].[AppUsers] ([UserId], [UserName], [PasswordHash], [Email]) VALUES (N'9fc2cb5f-bee8-44c2-939c-1d4ddd0fc5d6', N'danfulga', N'qwerty123', N'danfulga@gmail.com')
GO
INSERT [dbo].[AppUsers] ([UserId], [UserName], [PasswordHash], [Email]) VALUES (N'bbb00794-5465-4fe0-835a-49416657c84f', N'craciunsergiu', N'qwerty123', N'craciunsergiu@gmail.com')
GO
INSERT [dbo].[AppUsers] ([UserId], [UserName], [PasswordHash], [Email]) VALUES (N'd8f0b9fe-f591-40b0-bd35-01a45f310538', N'andreipopa', N'qwerty123', N'apopa@gmail.com')
GO
SET IDENTITY_INSERT [dbo].[Group] ON 
GO
INSERT [dbo].[Group] ([Id], [YearOfStudyId], [GroupName], [Description], [IsActive]) VALUES (4, 1, N'30431', N'Group Number 1', 1)
GO
SET IDENTITY_INSERT [dbo].[Group] OFF
GO
INSERT [dbo].[StudentEnrollment] ([StudentId], [GroupId]) VALUES (9, 4)
GO
INSERT [dbo].[StudentEnrollment] ([StudentId], [GroupId]) VALUES (10, 4)
GO
INSERT [dbo].[StudentEnrollment] ([StudentId], [GroupId]) VALUES (11, 4)
GO
INSERT [dbo].[StudentEnrollment] ([StudentId], [GroupId]) VALUES (12, 4)
GO
INSERT [dbo].[StudentEnrollment] ([StudentId], [GroupId]) VALUES (13, 4)
GO
INSERT [dbo].[StudentEnrollment] ([StudentId], [GroupId]) VALUES (14, 4)
GO
INSERT [dbo].[StudentGrades] ([StudentId], [SubjectId], [Grade]) VALUES (9, 1, 7,78)
GO
INSERT [dbo].[StudentGrades] ([StudentId], [SubjectId], [Grade]) VALUES (9, 2, 10)
GO
INSERT [dbo].[StudentGrades] ([StudentId], [SubjectId], [Grade]) VALUES (9, 3, 6)
GO
INSERT [dbo].[StudentGrades] ([StudentId], [SubjectId], [Grade]) VALUES (10, 1, 7)
GO
INSERT [dbo].[StudentGrades] ([StudentId], [SubjectId], [Grade]) VALUES (10, 2, 7,54)
GO
INSERT [dbo].[StudentGrades] ([StudentId], [SubjectId], [Grade]) VALUES (10, 3, 8,92)
GO
INSERT [dbo].[StudentGrades] ([StudentId], [SubjectId], [Grade]) VALUES (11, 1, 9,6)
GO
INSERT [dbo].[StudentGrades] ([StudentId], [SubjectId], [Grade]) VALUES (11, 2, 10)
GO
INSERT [dbo].[StudentGrades] ([StudentId], [SubjectId], [Grade]) VALUES (11, 3, 7,63)
GO
SET IDENTITY_INSERT [dbo].[Students] ON 
GO
INSERT [dbo].[Students] ([UserId], [StudentId]) VALUES (N'9fc2cb5f-bee8-44c2-939c-1d4ddd0fc5d6', 9)
GO
INSERT [dbo].[Students] ([UserId], [StudentId]) VALUES (N'03e55bfa-3831-408c-8923-b0faa9901e6b', 10)
GO
INSERT [dbo].[Students] ([UserId], [StudentId]) VALUES (N'3ad5a243-93e8-4cef-8104-1417c2811c78', 11)
GO
INSERT [dbo].[Students] ([UserId], [StudentId]) VALUES (N'1e68bb71-506d-48ed-9bd4-9787293e012d', 12)
GO
INSERT [dbo].[Students] ([UserId], [StudentId]) VALUES (N'd8f0b9fe-f591-40b0-bd35-01a45f310538', 13)
GO
INSERT [dbo].[Students] ([UserId], [StudentId]) VALUES (N'06a8eb50-322b-4a26-880c-436c7979645a', 14)
GO
SET IDENTITY_INSERT [dbo].[Students] OFF
GO
SET IDENTITY_INSERT [dbo].[Subjects] ON 
GO
INSERT [dbo].[Subjects] ([Id], [SubjectName], [Description]) VALUES (1, N'Mathematics', N'-')
GO
INSERT [dbo].[Subjects] ([Id], [SubjectName], [Description]) VALUES (2, N'Computer Architecture', N'-')
GO
INSERT [dbo].[Subjects] ([Id], [SubjectName], [Description]) VALUES (3, N'Data Structures and algorithms', N'-')
GO
SET IDENTITY_INSERT [dbo].[Subjects] OFF
GO
INSERT [dbo].[UserProfile] ([UserId], [Name], [Surname], [MidName], [Phone], [Nationality], [Country], [County], [Address], [ZIP]) VALUES (N'03e55bfa-3831-408c-8923-b0faa9901e6b', N'Sergiu', N'Craciun', N'Mihai', N'0789123456', N'Romanian', N'Romania', N'Cluj', N'Baritiu 12', N'400456')
GO
INSERT [dbo].[UserProfile] ([UserId], [Name], [Surname], [MidName], [Phone], [Nationality], [Country], [County], [Address], [ZIP]) VALUES (N'06a8eb50-322b-4a26-880c-436c7979645a', N'Florin', N'Popa', N'-', N'0754456345', N'Romanian', N'Romania', N'Ilfov', N'Cantemir 2', N'33221')
GO
INSERT [dbo].[UserProfile] ([UserId], [Name], [Surname], [MidName], [Phone], [Nationality], [Country], [County], [Address], [ZIP]) VALUES (N'1d71d0b5-5549-47e4-9d36-c76baa24812e', N'Andrei', N'Branga', N'N', N'0754458404', N'Romanian', N'Romania', N'Cluj', N'Baritiu 12,Cluj', N'40048')
GO
INSERT [dbo].[UserProfile] ([UserId], [Name], [Surname], [MidName], [Phone], [Nationality], [Country], [County], [Address], [ZIP]) VALUES (N'1e68bb71-506d-48ed-9bd4-9787293e012d', N'Maria', N'Preda', N'-', N'0264543234', N'Romanian', N'Romania', N'Cluj', N'Dorobantilor 1', N'322123')
GO
INSERT [dbo].[UserProfile] ([UserId], [Name], [Surname], [MidName], [Phone], [Nationality], [Country], [County], [Address], [ZIP]) VALUES (N'3ad5a243-93e8-4cef-8104-1417c2811c78', N'Ioana', N'Popa', N'-', N'0976123564', N'Romanian', N'Romania', N'Bucuresti', N'Cosma,23', N'221133')
GO
INSERT [dbo].[UserProfile] ([UserId], [Name], [Surname], [MidName], [Phone], [Nationality], [Country], [County], [Address], [ZIP]) VALUES (N'9fc2cb5f-bee8-44c2-939c-1d4ddd0fc5d6', N'Fulga', N'Dan', N'-', N'0754123456', N'Romanian', N'Romania', N'Cluj', N'Baritiu12', N'40048')
GO
INSERT [dbo].[UserProfile] ([UserId], [Name], [Surname], [MidName], [Phone], [Nationality], [Country], [County], [Address], [ZIP]) VALUES (N'd8f0b9fe-f591-40b0-bd35-01a45f310538', N'Andrei', N'Popa', N'-', N'0765432123', N'Romanian', N'Romania', N'Sibiu', N'Balcescu, 2', N'223344')
GO
SET IDENTITY_INSERT [dbo].[YearOfStudy] ON 
GO
INSERT [dbo].[YearOfStudy] ([Id], [YearName], [Description]) VALUES (1, N'Year 1', N'Actually year 1')
GO
SET IDENTITY_INSERT [dbo].[YearOfStudy] OFF
GO
INSERT [dbo].[YearOfStudySubjects] ([SubjectId], [YearOfStudyId]) VALUES (1, 1)
GO
INSERT [dbo].[YearOfStudySubjects] ([SubjectId], [YearOfStudyId]) VALUES (2, 1)
GO
INSERT [dbo].[YearOfStudySubjects] ([SubjectId], [YearOfStudyId]) VALUES (3, 1)
GO
ALTER TABLE [dbo].[AppUserRoles]  WITH CHECK ADD  CONSTRAINT [FK_AppUserRoles_AppRoles] FOREIGN KEY([RoleId])
REFERENCES [dbo].[AppRoles] ([RoleId])
GO
ALTER TABLE [dbo].[AppUserRoles] CHECK CONSTRAINT [FK_AppUserRoles_AppRoles]
GO
ALTER TABLE [dbo].[AppUserRoles]  WITH CHECK ADD  CONSTRAINT [FK_AppUserRoles_AppUsers] FOREIGN KEY([UserId])
REFERENCES [dbo].[AppUsers] ([UserId])
GO
ALTER TABLE [dbo].[AppUserRoles] CHECK CONSTRAINT [FK_AppUserRoles_AppUsers]
GO
ALTER TABLE [dbo].[Group]  WITH CHECK ADD  CONSTRAINT [FK_Group_YearOfStudy] FOREIGN KEY([YearOfStudyId])
REFERENCES [dbo].[YearOfStudy] ([Id])
GO
ALTER TABLE [dbo].[Group] CHECK CONSTRAINT [FK_Group_YearOfStudy]
GO
ALTER TABLE [dbo].[StudentEnrollment]  WITH CHECK ADD  CONSTRAINT [FK_StudentEnrollment_Group] FOREIGN KEY([GroupId])
REFERENCES [dbo].[Group] ([Id])
GO
ALTER TABLE [dbo].[StudentEnrollment] CHECK CONSTRAINT [FK_StudentEnrollment_Group]
GO
ALTER TABLE [dbo].[StudentEnrollment]  WITH CHECK ADD  CONSTRAINT [FK_StudentEnrollment_Students] FOREIGN KEY([StudentId])
REFERENCES [dbo].[Students] ([StudentId])
GO
ALTER TABLE [dbo].[StudentEnrollment] CHECK CONSTRAINT [FK_StudentEnrollment_Students]
GO
ALTER TABLE [dbo].[StudentGrades]  WITH CHECK ADD  CONSTRAINT [FK_StudentGrades_Students] FOREIGN KEY([StudentId])
REFERENCES [dbo].[Students] ([StudentId])
GO
ALTER TABLE [dbo].[StudentGrades] CHECK CONSTRAINT [FK_StudentGrades_Students]
GO
ALTER TABLE [dbo].[StudentGrades]  WITH CHECK ADD  CONSTRAINT [FK_StudentGrades_Subjects] FOREIGN KEY([SubjectId])
REFERENCES [dbo].[Subjects] ([Id])
GO
ALTER TABLE [dbo].[StudentGrades] CHECK CONSTRAINT [FK_StudentGrades_Subjects]
GO
ALTER TABLE [dbo].[Students]  WITH CHECK ADD  CONSTRAINT [FK_Students_AppUsers] FOREIGN KEY([UserId])
REFERENCES [dbo].[AppUsers] ([UserId])
GO
ALTER TABLE [dbo].[Students] CHECK CONSTRAINT [FK_Students_AppUsers]
GO
ALTER TABLE [dbo].[UserProfile]  WITH CHECK ADD  CONSTRAINT [FK_UserProfile_AppUsers] FOREIGN KEY([UserId])
REFERENCES [dbo].[AppUsers] ([UserId])
GO
ALTER TABLE [dbo].[UserProfile] CHECK CONSTRAINT [FK_UserProfile_AppUsers]
GO
ALTER TABLE [dbo].[YearOfStudySubjects]  WITH CHECK ADD  CONSTRAINT [FK_YearOfStudySubjects_Subjects] FOREIGN KEY([SubjectId])
REFERENCES [dbo].[Subjects] ([Id])
GO
ALTER TABLE [dbo].[YearOfStudySubjects] CHECK CONSTRAINT [FK_YearOfStudySubjects_Subjects]
GO
ALTER TABLE [dbo].[YearOfStudySubjects]  WITH CHECK ADD  CONSTRAINT [FK_YearOfStudySubjects_YearOfStudy] FOREIGN KEY([YearOfStudyId])
REFERENCES [dbo].[YearOfStudy] ([Id])
GO
ALTER TABLE [dbo].[YearOfStudySubjects] CHECK CONSTRAINT [FK_YearOfStudySubjects_YearOfStudy]
GO
