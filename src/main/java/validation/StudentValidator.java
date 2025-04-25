package validation;

import domain.Student;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentValidator implements Validator<Student> {

    /**
     * Valideaza un student
     * @param entity - studentul pe care il valideaza
     * @throws ValidationException - daca studentul nu e valid
     */
    @Override
    public void validate(Student entity) throws ValidationException {
        try {
            int idInteger = Integer.parseInt(entity.getID());
            if(idInteger <= 0){
                throw new ValidationException("Id incorect!");
            }
        }
        catch (NumberFormatException e) {
            throw new ValidationException("Id incorect!");
        }
        if(entity.getID().equals("")){
            throw new ValidationException("Id incorect!");
        }
        if(entity.getID() == null || entity.getID().charAt(0) == '-'){
            throw new ValidationException("Id incorect!");
        }
        if(entity.getNume() == ""){
            throw new ValidationException("Nume incorect!");
        }
        if(entity.getGrupa() < 0) {
            throw new ValidationException("Grupa incorecta!");
        }
        if(entity.getEmail() == null){
            throw new ValidationException("Email incorect!");
        }
        if(entity.getNume() == null){
            throw new ValidationException("Nume incorect!");
        }
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(entity.getEmail());

        if(!matcher.matches()){
            throw new ValidationException("Email incorect!");
        }
    }
}
