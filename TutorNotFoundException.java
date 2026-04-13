public class TutorNotFoundException extends IllegalArgumentException {

    TutorNotFoundException(String string) {
        throw new IllegalArgumentException(string);
    }

    public TutorNotFoundException() {
        //TODO Auto-generated constructor stub
    }

}
