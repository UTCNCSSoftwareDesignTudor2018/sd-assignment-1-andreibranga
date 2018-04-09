package BussinessLogicLayer.Services;

import BussinessLogicLayer.Models.StudentGradesModel;
import DataAccessLayer.DAO.StudentGradesDAO;

import java.util.Optional;

public class GradingService
{
    public static String getGradeforSubject(int studentId,int subjectId)
    {
        for (StudentGradesModel p : StudentGradesDAO.GetAllGradesForStudent(studentId)) {
            if (p.getSubjectId() == subjectId) {
                return p.getGrade().toString();
            }
        }
        return "NOT SET";
    }


    public static void updateGradeForSubject(int studentId,int subjectId,String grade)
    {
        StudentGradesModel studentGradesModel=null;
        for (StudentGradesModel p : StudentGradesDAO.GetAllGradesForStudent(studentId)) {
            if (p.getSubjectId() == subjectId) {
                studentGradesModel=p;
            }
        }

        if(studentGradesModel==null)
        {
            StudentGradesDAO.AddGradeToStudent(studentId,subjectId,Float.valueOf(grade));
        }
        else
        {
                StudentGradesDAO.update(studentId,subjectId,Float.valueOf(grade));
        }
    }

}
