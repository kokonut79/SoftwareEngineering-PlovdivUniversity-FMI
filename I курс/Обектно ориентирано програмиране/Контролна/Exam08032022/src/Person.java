public abstract class Person {
    private String name;
    private String egn;

    public Person(String name, String egn){
        this.name = name;
        this.egn = egn;
    }

    public String getName() {
        return name;
    }

    public String getEgn() {
        return egn;
    }
    public abstract void printInfo();
}
