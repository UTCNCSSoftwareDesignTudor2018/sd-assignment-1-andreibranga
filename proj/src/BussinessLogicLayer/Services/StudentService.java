package BussinessLogicLayer.Services;

import DataAccessLayer.Models.*;
import DataAccessLayer.DAO.*;

import java.util.ArrayList;

public class StudentService
{
    public static ArrayList<StudentsModel> GetAllStudents()
    {
        return StudentsDao.GetAllStudents();
    }

    public static StudentsModel getStudentById(int id)
    {
        for (StudentsModel p : StudentsDao.GetAllStudents()) {
            if (p.getStudentId() == id) {
                return p;
            }
        }
        return null;
    }

    public static GroupModel getStudentGroup(int studentId)
    {
        StudentEnrollmentModel studentEnrollmentModel= StudentEnrollmentDAO.GetAllGroupsForStudent(studentId).get(0);

        return GroupDAO.findById(studentEnrollmentModel.getGroupId());
    }

    public static YearOfStudyModel getYearOfStudyForStudent(int studentId)
    {

        StudentEnrollmentModel studentEnrollmentModel= StudentEnrollmentDAO.GetAllGroupsForStudent(studentId).get(0);
      GroupModel group=GroupDAO.findById(studentEnrollmentModel.getGroupId());

        YearOfStudyModel yearOfStudyModel= YearOfStudyDAO.findById(group.getYearOfStudyId());
return yearOfStudyModel;
    }

    public static ArrayList<SubjectsModel> getAllSubjectsForStudent(int studentId)
    {
        StudentEnrollmentModel studentEnrollmentModel= StudentEnrollmentDAO.GetAllGroupsForStudent(studentId).get(0);
        GroupModel group=GroupDAO.findById(studentEnrollmentModel.getGroupId());

        ArrayList<YearOfStudySubjectsModel> subj= YearOfStudySubjectsDAO.GetAllYearOfStudySubjectsForYear(group.getYearOfStudyId());
        ArrayList<SubjectsModel> subjects=new ArrayList<>();

        for (YearOfStudySubjectsModel item :subj
             ) {
            subjects.add(SubjectsDAO.findById(item.getSubjectId()));
        }

        return subjects;
    }

    public static int MakeUserStudent(String Userid)
    {
        StudentsDao.MakeUserStudent(Userid);
        StudentsModel found=null;
        for (StudentsModel p : StudentsDao.GetAllStudents()) {
            if (p.getUserId().equals( Userid)) {
                found = p;
                break;
            }
        }

        return found.getStudentId();

    }

    public static void AddStudentToGroup(int studentId,int GroupId)
    {
        StudentEnrollmentDAO.AddStudentToGroup(studentId,GroupId);
    }

    public static ArrayList<GroupModel> getAllGroups()
    {
        return GroupDAO.GetAllGroups();
    }
}
