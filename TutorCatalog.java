import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TutorCatalog extends Databaser {

    private List<Tutor> tutors;

    public TutorCatalog() {
        tutors = new ArrayList<>();
        populateSampleTutors();
    }

    public void addTutor(Tutor newTutor) {
        tutors.add(newTutor);
    }

    public void removeTutor(Tutor toRemove) {
        boolean wasRemoved = tutors.remove(toRemove);

        if (!wasRemoved) {
            throw new TutorNotFoundException();
        }
    }

    public List<Tutor> getAllTutors() {
        return tutors;
    }

    public Tutor findTutorByName(String name) throws TutorNotFoundException {

        for (Tutor tutor : tutors) {
            if (tutor.getName().equalsIgnoreCase(name)) {
                return tutor;
            }
        }

        throw new TutorNotFoundException("No tutor found with name: " + name);
    }

    public List<Tutor> findTutorsByName(String name) throws TutorNotFoundException {
        List<Tutor> Tutors = new ArrayList<>();
        for (Tutor tutor : tutors) {
            if (tutor.getName().toLowerCase().contains(name.toLowerCase())) {
                Tutors.add(tutor);
            }
        }

        return Tutors;
    }

    public List<Tutor> findBestTutorsBySubjects(List<String> requestedSubjects) {

        List<Tutor> matches = new ArrayList<>();
        int highestScore = 0;

        for (Tutor tutor : tutors) {

            int score = countMatches(tutor, requestedSubjects);

            if (score > highestScore) {
                matches.clear();
                matches.add(tutor);
                highestScore = score;
            }
            else if (score == highestScore && score > 0) {
                matches.add(tutor);
            }
        }

        return matches;
    }

    private int countMatches(Tutor tutor, List<String> requestedSubjects) {

        int count = 0;

        for (String requested : requestedSubjects) {

            for (String tutorSubject : tutor.getTutoringSubjects()) {

                if (tutorSubject.equalsIgnoreCase(requested)) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    /**
     * Seed some tutors so searches work in oreu demo
     */
    private void populateSampleTutors() {

        Tutor t1 = new Tutor("Marcus Weemer", "marcus@pfw.edu");
        t1.setTutoringSubjects(Arrays.asList("Math", "Physics"));

        Tutor t2 = new Tutor("Sarah Chen", "sarah@pfw.edu");
        t2.setTutoringSubjects(Arrays.asList("Computer Science", "Java"));

        Tutor t3 = new Tutor("David Lopez", "david@pfw.edu");
        t3.setTutoringSubjects(Arrays.asList("Chemistry", "Biology"));

        Tutor t4 = new Tutor("Emily Carter", "emily@pfw.edu");
        t4.setTutoringSubjects(Arrays.asList("Math", "Statistics"));

        addTutor(t1);
        addTutor(t2);
        addTutor(t3);
        addTutor(t4);
    }
}