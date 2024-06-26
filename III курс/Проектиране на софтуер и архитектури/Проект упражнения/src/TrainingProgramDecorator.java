public abstract class TrainingProgramDecorator implements TrainingProgram {
    protected TrainingProgram trainingProgram;

    public TrainingProgramDecorator(TrainingProgram trainingProgram) {
        this.trainingProgram = trainingProgram;
    }

    @Override
    public String execute() {
        return trainingProgram.execute();
    }
}

class IntensiveCardioDecorator extends TrainingProgramDecorator {
    public IntensiveCardioDecorator(TrainingProgram trainingProgram) {
        super(trainingProgram);
    }

    @Override
    public String execute() {
        return trainingProgram.execute() + "Intensive cardio";
    }
}

class BjjDecorator extends TrainingProgramDecorator {
    public BjjDecorator(TrainingProgram trainingProgram) {
        super(trainingProgram);
    }

    @Override
    public String execute() {
        return trainingProgram.execute() + "BJJ";
    }
}
