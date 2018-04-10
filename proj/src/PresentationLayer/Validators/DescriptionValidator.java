package PresentationLayer.Validators;

public class DescriptionValidator {
    public static void validate(String desc)
    {
        if(desc.length()==0)
        {
            throw new IllegalArgumentException("Description cannot be empty!");
        }
    }
}
