package PresentationLayer.Validators;

public class ZIPValidator
{
    public static void validate(String zip)
    {
        String zipCodePattern = "\\d{5}(-\\d{4})?";
        if(!zip.matches(zipCodePattern))
        {
            throw new IllegalArgumentException("zipcode not valid!");
        }
    }
}
