public class Bird extends Animal implements Flyable{
    private int speed;
    private boolean canFly;
    private int speedMax;

    public Bird(String name, int age, String color){
        super(name,age,color);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if(!isCanFly()){
            speed = 0;
        }
        this.speed = speed;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    public int getSpeedMax() {
        return speedMax;
    }

    public void setSpeedMax(int speedMax) {
        if(!isCanFly()){
            speedMax = 0;
        }
        this.speedMax = speedMax;
    }
    @Override
    public void flying(){
        int tmp;
        if(isCanFly()){
            tmp = getSpeed();
            for(int i = tmp; i <= getSpeedMax();i++){
                setSpeed(i);
            }
            System.out.println("Bird has the max speed");
        }else{
            System.out.println("Bird can't fly");
        }
    }

    @Override
    public String toString() {
        return "Bird {" +
                "speed = " + speed +
                ", can fly? = " + canFly +
                ", max speed = " + speedMax +
                ", name = '" + name + '\'' +
                ", age = " + age +
                ", color = '" + color + '\'' +
                '}';
    }
}
