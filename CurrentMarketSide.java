public class CurrentMarketSide {
    private final Price price;
    private final int volume;
    public CurrentMarketSide(Price price, int volume) {
        this.price = price;
        this.volume = volume;
    }
    public String toString(){
        return price.toString() + "x" + volume;
    }
}
