import java.util.Scanner;
class Homework{
    private static final Scanner input = new Scanner(System.in);

    static void cycleFibo(int end){

        int counter = 1; int now = 0; int next = 1; int res;

        if(end >= 1 && counter == 1) {
            System.out.println(0);
        }
        while(true){
            res = now + next;
            if (counter < end){
                System.out.println(res);
                counter++;
                now = next;
                next = res;
            }else{
                break;
            }
        }
    }

    static int recurFibo(int end) {
        if (end == 0){
            return 0;
        }
        if (end == 1 || end == 2){
            return 1;
        }
        return recurFibo(end-2) + recurFibo(end-1);

    }

    public static void main(String[] args) {

        System.out.print("How much nums from Fibbo sequence to be prnt: ");
        int num = input.nextInt();
        if (num == 0){
            System.out.println("Okay, bye..");
            System.exit(0);
        }
        System.out.print("Are we going to use for cycle or recursion? c/r: ");
        char ans = input.next().charAt(0);

        if (ans == 'c'){
            cycleFibo(num);
        }else if(ans == 'r') {
            for (int i = 0; i < num; i++){
                System.out.println(recurFibo(i));
            }
        }
    }


}