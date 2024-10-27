public class Mammal<T> extends Animal<String> implements Callable<T>{
    private final T weight;

    public Mammal(String name, String age, T weight){
        super(name, age);
        this.weight = weight;
    }

    public T getWeight() {
        return weight;
    }

    @Override
    public T fullInfo() {
        return (T) ("Вид животоного: " + getName() + ", Возраст: " + getAge() + ", Вес: " + getWeight());
    }
}
