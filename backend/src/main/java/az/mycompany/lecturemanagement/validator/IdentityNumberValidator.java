package az.mycompany.lecturemanagement.validator;

import az.mycompany.lecturemanagement.exception.InvalidIdentityNumberException;

public class IdentityNumberValidator {

    public static void validateIdentityNumber(String identityNumber) {
        if (!isValid(identityNumber)) {
            throw new InvalidIdentityNumberException("Invalid identity number: " + identityNumber);
        }
    }

    private static boolean isValid(String identityNumber) {
        return identityNumber != null && identityNumber.length() == 11;
    }

}
