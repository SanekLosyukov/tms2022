package task1;

public class Person {
    private String name;
    private int age;
    private static final String SEX = "Male";
    private Address address;

    public Person(String name, int age, String SEX, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
        //this.SEX = SEX;           - Не разобрался с этим. Как передать константу в конструктор??????????
    }

    public static String getSEX() {
        return SEX;
    }

    public Address getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "task1.Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
