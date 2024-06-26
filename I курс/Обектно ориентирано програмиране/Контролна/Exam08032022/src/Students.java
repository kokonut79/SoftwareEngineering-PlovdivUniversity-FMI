public class Students  extends Person{
    private String fn;

    public Students(String name, String egn, String fn){
        super(name, egn);
        this.fn = fn;
    }

    @Override
    public void printInfo() {
        System.out.println("Name:" + super.getName() + " egn:" + super.getEgn() + " fn:" + getFn());
    }

    public String setFn(String fn){
        if (fn.length() <= 8){
            this.fn = fn;
        }
        else{
            this.fn = "10101010";
        }
        return "";
    }

    public String getFn() {
        return fn;
    }
}
