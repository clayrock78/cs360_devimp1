public class TutorNotFoundException extends IllegalArgumentException {

    public TutorNotFoundException(String message) {
        super(message);
    }

    public TutorNotFoundException() {
        super();
    }
}
