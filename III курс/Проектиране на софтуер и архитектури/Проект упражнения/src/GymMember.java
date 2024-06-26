public class GymMember {
    private String name;

    public GymMember(String name) {
        this.name = name;
    }

    public void update(TrainingProgram trainingProgram) {
        System.out.println(name + " has been notified of the new training program: " + trainingProgram.execute());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GymMember trainee = (GymMember) obj;
        return name.equals(trainee.name);
    }
}
