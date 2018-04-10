package PresentationLayer.Validators;

public class AddressValidator
{
    public static void validateAddress( String address ) {
        if(!(address.length()>5))
        {
            throw new IllegalArgumentException("Address is not valid!");
        }
    } // end method validateAddress

}
