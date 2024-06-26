public class Teachers extends Person{
    private String katedra;

    public Teachers(String name, String egn, String katedra){
        super(name, egn);
        this.katedra = katedra;
    }

    @Override
    public void printInfo() {
        System.out.println("Name:" + super.getName() + " egn:" + super.getEgn() + " " + getKatedra());
    }

    public String getKatedra() {
        return katedra;
    }

    public void setKatedra(String katedra) {
        this.katedra = katedra;
    }
}
