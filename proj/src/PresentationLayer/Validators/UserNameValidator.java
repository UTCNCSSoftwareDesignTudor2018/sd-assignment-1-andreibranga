package PresentationLayer.Validators;

public class UserNameValidator {
    public static void validate(String username)
    {
        if(!username.matches("\\b[a-zA-Z][a-zA-Z0-9\\-._]{3,}\\b"))
        {
            throw new IllegalArgumentException("Username not valid");
        }
    }
}
