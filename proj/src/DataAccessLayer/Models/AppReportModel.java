package DataAccessLayer.Models;

import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;

public class AppReportModel
{
    private String Name;
    private String Surname;
    private String MidName;
    private String StudentId;

    private String Group;

    private String Year;

    private ArrayList<Pair<SubjectsModel,Float>> grades;

    public ArrayList<Pair<SubjectsModel, Float>> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<Pair<SubjectsModel, Float>> grades) {
        this.grades = grades;
    }

    public AppReportModel() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getMidName() {
        return MidName;
    }

    public void setMidName(String midName) {
        MidName = midName;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }




    @Override
    public String toString() {
        String report= "Student Report\n" +
                "Name=" + Name + '\n' +
                "Surname=" + Surname + '\n' +
                "MidName=" + MidName + '\n' +
                "StudentId=" + StudentId + '\n' +
                "Group=" + Group + '\n' +
                "Year=" + Year + '\n' + '\n' +'\n'+
               "Grades: \n";

        report+='\n';

        for (Pair<SubjectsModel,Float> p:grades
             ) {
            report+="Subject: "+p.fst.getSubjectName()+ "    Grade: "+p.snd+ '\n' ;
        }


        return  report;
    }
}
