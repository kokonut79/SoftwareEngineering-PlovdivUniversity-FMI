import java.util.HashMap;
import java.util.Map;

public class FitnessCenter {
    private static FitnessCenter instance;
    private Map<GymMember, TrainingProgram> traineePrograms = new HashMap<>();

    private FitnessCenter() {}

    public static synchronized FitnessCenter getInstance() {
        if (instance == null) {
            instance = new FitnessCenter();
        }
        return instance;
    }
    // свързване на фитнес програма с трениращ
    public void attach(GymMember trainee, TrainingProgram trainingProgram) {
        traineePrograms.put(trainee, trainingProgram);
    }
    // премахване на трениращ от програма
    public void detach(GymMember trainee) {
        traineePrograms.remove(trainee);
    }
    // уведомяване за update - ти 
    public void notifyObservers() {
        for (Map.Entry<GymMember, TrainingProgram> entry : traineePrograms.entrySet()) {
            entry.getKey().update(entry.getValue());
        }
    }
    // edit метод
    public void setTrainingProgram(GymMember trainee, TrainingProgram trainingProgram) {
        traineePrograms.put(trainee, trainingProgram);
        notifyObservers();
    }
}
