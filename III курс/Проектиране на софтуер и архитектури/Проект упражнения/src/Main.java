public class Main {
    public static void main(String[] args) {
        // Create fitnessCenter
        FitnessCenter fitnessCenter = FitnessCenter.getInstance();

        // Create objs - GymMembers
        GymMember trainee1 = new GymMember("Stoqn K.");
        GymMember trainee2 = new GymMember("Bai Kiro");

        // Basic tren program
        TrainingProgram basicProgram = new BasicTrainingProgram();
        // Personalizing with decorator and mapping gymMember with his personalized program
        TrainingProgram personalizedProgram = new IntensiveCardioDecorator(basicProgram);
        fitnessCenter.attach(trainee1, personalizedProgram);

        TrainingProgram anotherPersonalizedProgram = new BjjDecorator(personalizedProgram);
        fitnessCenter.attach(trainee2, anotherPersonalizedProgram);

        // Notify gymMembers for changes
        fitnessCenter.notifyObservers();
    }
}
