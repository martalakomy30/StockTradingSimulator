
public class MainHW2 {

    public static void main(String[] args) throws BadParameterException {
        System.out.println("Hi!");
        ProductBook pb = new ProductBook("P1", new ProductBookSide(Bookside.BUY), new ProductBookSide(Bookside.SELL));
        pb.add(new Order("U1", "P1", new Price(1000), Bookside.BUY, "1", 50));
        System.out.println("1)   " + pb.toString());
        pb.add(new Order("U1", "P1", new Price(1000), Bookside.BUY, "2", 60));
        System.out.println("2)   " + pb.toString());
        pb.add(new Order("U1", "P1", new Price(995), Bookside.BUY, "3", 70));
        System.out.println("3)   " + pb.toString());
        pb.add(new Order("U1", "P1", new Price(990), Bookside.BUY, "4", 25));
        System.out.println("4)   " + pb.toString());
        pb.add(new Order("U1", "P1", new Price(1010), Bookside.SELL, "5", 120));
        System.out.println("5)   " + pb.toString());
        pb.add(new Order("U1", "P1", new Price(1020), Bookside.SELL, "6", 45));
        System.out.println("6)   " + pb.toString());
        pb.add(new Order("U1", "P1", new Price(1025), Bookside.SELL, "7", 90));
        System.out.println("7)   " + pb.toString());
        pb.add(new Order("U1", "P1", new Price(1025), Bookside.SELL, "15", 90));
        System.out.println("8)   " + pb.toString());
        pb.add(new Order("U1", "P1", new Price(1000), Bookside.SELL, "8", 200));
        System.out.println("9)   " + pb.toString());
        pb.add(new Order("U1", "P1", new Price(1010), Bookside.BUY, "9", 200));
        System.out.println("10)   " + pb.toString());
        pb.cancel(Bookside.SELL, "6");
        System.out.println("11)   " + pb.toString());
        pb.add(new Order("U1", "P1", new Price(990), Bookside.SELL, "11", 95));
        System.out.println("12)   " + pb.toString());
        pb.add(new Order("U1", "P1", new Price(1025), Bookside.BUY, "12", 100));
        System.out.println("13)   " + pb.toString());


    }
}
