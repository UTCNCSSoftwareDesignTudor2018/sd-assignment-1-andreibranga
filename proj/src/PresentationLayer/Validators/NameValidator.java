package PresentationLayer.Validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator
{
    public static void validateLetters(String txt) {
        if(txt.equals("-"))
            return;

        String regx = "[a-zA-Z]+\\.?";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txt);
        if(!matcher.find())
        {
            throw new IllegalArgumentException("Name not valid!");
        }

    }
}
