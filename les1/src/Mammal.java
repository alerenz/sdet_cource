import java.util.ArrayList;
import java.util.Objects;

public class Mammal extends Animal implements Feedable{
    private int weigth;
    private int maxWeight;
    private boolean hungry;


    public Mammal(String name, int age, String color, int weigth, int maxWeight, boolean hungry){
        super(name, age, color);
        this.weigth = weigth;
        this.maxWeight = maxWeight;
        this.hungry = hungry;
    }


    public int getMaxWeight() {
        return maxWeight;
    }

    public int getWeigth() {
        return weigth;
    }

    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }

    @Override
    public void feeding() {
        if(hungry){
            int tmp = getWeigth();
            while(tmp != getMaxWeight()){
                tmp += 2;
                setWeigth(tmp);
            }
            System.out.println("Mammal is feeding");
        }else{
            System.out.println("Mammal not hungry");
        }
    }

    public static int countMammals(ArrayList<Mammal> mammals){
        int count = 0;
        for(Mammal i:mammals){
            if(i != null){
                count++;
            }else{
                return 0;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return "Mammal{" +
                "weight =" + weigth +
                ", max weight =" + maxWeight +
                ", hungry? =" + hungry +
                ", name ='" + name + '\'' +
                ", age =" + age +
                ",color = '" + color + '\'' +
                '}';
    }
}
