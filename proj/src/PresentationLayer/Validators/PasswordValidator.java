package PresentationLayer.Validators;

public class PasswordValidator {

        public static void validate(String pas)
    {
        String passwd = "aaZZa44@";
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if(!passwd.matches(pattern))
        {
            throw new IllegalArgumentException("Password not accepted!");
        }
    }

}
