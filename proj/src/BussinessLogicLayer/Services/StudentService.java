package BussinessLogicLayer.Services;

import BussinessLogicLayer.Models.StudentsModel;
import DataAccessLayer.DAO.StudentsDao;

import java.util.ArrayList;

public class StudentService
{
    public static ArrayList<StudentsModel> GetAllStudents()
    {
        return StudentsDao.GetAllStudents();
    }
}
