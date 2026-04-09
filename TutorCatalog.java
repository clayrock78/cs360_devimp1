import java.util.ArrayList;
import java.util.List;

public class TutorCatalog extends Databaser {

    private List<Tutor> tutors;

    private void addTutor(Tutor newTutor) {
        tutors.add(newTutor);
    }

    private void removeTutor(Tutor toRemove) {
        boolean wasRemoved = tutors.remove(toRemove);
        if (!wasRemoved) {
            throw new TutorNotFoundException();
        }
    }

    public List<Tutor> getAllTutors() {
        return tutors;
    }

    public Tutor findTutorByName(String name) {
        for(Tutor tutor : tutors) {
            if(tutor.getName().equalsIgnoreCase(name)) {
                return tutor;
            }
        }
        throw new TutorNotFoundException();
    }

    public List<Tutor> findBestTutorsBySubjects(List<String> requestedSubjects) {
        List<Tutor> matches = new ArrayList<>();
        int highestScore = 0;

        for(Tutor tutor : tutors) {
            int score = countMatches(tutor, requestedSubjects);

            if(score > highestScore) {
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

        for(String requested : requestedSubjects) {
            for(String tutorSubject : tutor.getTutoringSubjects()) {
                if(tutorSubject.equalsIgnoreCase(requested)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }


}