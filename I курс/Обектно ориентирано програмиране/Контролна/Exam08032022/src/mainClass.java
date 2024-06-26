import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class mainClass {

    public static void main(String[] args) {

        Students s1 = new Students("Georgi", "0248264367", "asd");
        Teachers t1 = new Teachers("Ivan", "7711090509", "FMI");
        Teachers t2 = new Teachers("Petko", "7711090509", "FMI");
        Students s2 = new Students("Zlatko", "0248264367", "asd");
        s1.setFn("12345678823432");
        s2.setFn("125256");


        ArrayList<Person> humans = new ArrayList<>();
        humans.add(s1);
        humans.add(t1);
        humans.add(t2);
        humans.add(s2);

        ArrayList<Person> SortedHumans = new ArrayList<>();


        for (Person element : humans){
            if (element instanceof Teachers){
                SortedHumans.add(element);
            }
        }
        for (Person element : humans){
            if (element instanceof Students){
                SortedHumans.add(element);
            }
        }


        for (int i = 0; i < SortedHumans.size(); i++){
            SortedHumans.get(i).printInfo();
        }

    }



}
