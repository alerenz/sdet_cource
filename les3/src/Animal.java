public abstract class Animal <T extends String>{
    private final T name;
    private final T age;

    public Animal(T name, T age) {
        this.name = name;
        this.age = age;
    }
    public T getName() {
        return name;
    }

    public T getAge() {
        return age;
    }
}
