import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Mammal<String>> mammals = new ArrayList<>();
        mammals.add(new Mammal<>("Собака","12 лет","5 кг"));
        mammals.add(new Mammal<>("Кот","5 месяцев","0.7 кг"));
        mammals.add(new Mammal<>("Тигр","6 лет","185 кг"));

        for(Mammal<String> m:mammals){
            System.out.println(m.fullInfo());
        }

        System.out.print("Введите число: ");
        int numb = sc.nextInt();
        CalcRemainder<Integer> calc = x -> x % 13 == 0;

        boolean f = calc.isDivisible(numb);
        String mes;

        if(f){
            mes = " делиться на 13 без остатка";
        }else{
            mes = " делиться на 13 c остатком";
        }
        System.out.println("Число " + numb + mes);

        System.out.print("Введите коэффициенты a, b, c: ");
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double c = sc.nextDouble();

        SquadEquation<Double> disc = (x, y, z) -> (y * y) - (4 * x * z);
        System.out.println("Дискриминант = " + disc.calcDiscriminant(a, b, c));
    }
}