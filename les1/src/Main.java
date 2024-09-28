import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Mammal> mammals = new ArrayList<>();
        mammals.add(new Mammal("Lion", 3, "Orange", 10, 14, true));
        mammals.add(new Mammal("Cat", 7, "ThreeColor", 3, 5, false));
        mammals.get(0).feeding();
        int amountMammals = Mammal.countMammals(mammals);
        System.out.println("All mammals: " + amountMammals);

        Bird pinguin = new Bird("Penguin",2, "White-black");
        pinguin.setCanFly(false);
        pinguin.flying();

        Bird titmouse = new Bird("Titmouse", 3, "ThreeColor");
        titmouse.setCanFly(true);
        titmouse.setSpeedMax(30);
        System.out.println("Write the speed less than "+ titmouse.getSpeedMax() + ": " );
        titmouse.setSpeed(sc.nextInt());
        System.out.println(titmouse);
    }
}
