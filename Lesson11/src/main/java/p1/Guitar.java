package p1;

public class Guitar implements IInstrument {
    private final int numberOfString;

    public Guitar(int numberOfString) {
        this.numberOfString = numberOfString;
    }

    @Override
    public void play() {
        System.out.println("Играет гитара, кол.струн = " + numberOfString);
    }
}
