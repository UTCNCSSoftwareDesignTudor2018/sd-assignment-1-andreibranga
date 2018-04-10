package PresentationLayer.Validators;

public class GradeValidator
{
    public static void validate(String grade)
    {
        float x;
        if(grade.equals("NOT SET"))
        {
            return;
        }
        try{
             x=Float.parseFloat(grade);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Grade is not valid");
        }

        if(x<1 || x>10)

        {
            throw new IllegalArgumentException("Grade is not valid");

        }
    }
}
