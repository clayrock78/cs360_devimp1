package src.main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TutorCatalog extends Database {

    private final List<Tutor> tutors;

    public TutorCatalog() {
        tutors = new ArrayList<>();
        populateSampleTutors();
    }

    public void addTutor(Tutor newTutor) {
        tutors.add(newTutor);
    }

    public void removeTutor(Tutor tutorToRemove) {
        boolean wasRemoved = tutors.remove(tutorToRemove);

        if (!wasRemoved) {
            throw new TutorNotFoundException();
        }
    }

    public List<Tutor> getAllTutors() {
        return tutors;
    }

    public Tutor findTutorByName(String name) throws TutorNotFoundException {

        for (Tutor tutor : tutors) {
            if (hasExactNameMatch(tutor, name)) {
                return tutor;
            }
        }

        throw new TutorNotFoundException("No tutor found with name: " + name);
    }

    public List<Tutor> findTutorsByName(String name) throws TutorNotFoundException {
        List<Tutor> matchingTutors = new ArrayList<>();
        for (Tutor tutor : tutors) {
            if (hasPartialNameMatch(tutor, name)) {
                matchingTutors.add(tutor);
            }
        }

        if (matchingTutors.isEmpty()) {
            throw new TutorNotFoundException("No tutor found with name: " + name);
        }

        return matchingTutors;
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
            } else if (score == highestScore && score > 0) {
                matches.add(tutor);
            }
        }

        return matches;
    }

    private int countMatches(Tutor tutor, List<String> requestedSubjects) {

        int count = 0;

        for (String requestedSubject : requestedSubjects) {
            if (hasSubjectMatch(tutor, requestedSubject)) {
                count++;
            }
        }

        return count;
    }

    private boolean hasExactNameMatch(Tutor tutor, String name) {
        return tutor.getName().equalsIgnoreCase(name);
    }

    private boolean hasPartialNameMatch(Tutor tutor, String name) {
        return tutor.getName().toLowerCase().contains(name.toLowerCase());
    }

    private boolean hasSubjectMatch(Tutor tutor, String requestedSubject) {
        for (String tutorSubject : tutor.getTutoringSubjects()) {
            if (tutorSubject.equalsIgnoreCase(requestedSubject)) {
                return true;
            }
        }
        return false;
    }

    private void populateSampleTutors() {
        addSampleTutor("Marcus Weemer", "marcus@pfw.edu", Arrays.asList("Math", "Physics"));
        addSampleTutor("Sarah Chen", "sarah@pfw.edu", Arrays.asList("Computer Science", "Java"));
        addSampleTutor("David Lopez", "david@pfw.edu", Arrays.asList("Chemistry", "Biology"));
        addSampleTutor("Emily Carter", "emily@pfw.edu", Arrays.asList("Math", "Statistics"));
    }

    private void addSampleTutor(String name, String email, List<String> subjects) {
        Tutor tutor = new Tutor(name, email);
        tutor.setTutoringSubjects(subjects);
        addTutor(tutor);
    }
}
