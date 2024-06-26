public interface TrainingProgram {
    String execute();
}

class BasicTrainingProgram implements TrainingProgram {
    @Override
    public String execute() {
        return "Basic Training Program";
    }
}

class CardioTrainingProgram implements TrainingProgram {
    @Override
    public String execute() {
        return "Cardio Training Program";
    }
}

class StrengthTrainingProgram implements TrainingProgram {
    @Override
    public String execute() {
        return "Strength Training Program";
    }
}

// Видове трен програми - по задание 
//основна тренировъчна програма, която може да бъде 
//  персонализирана с различни допълнителни упражнения като кардио и силови тренировки.