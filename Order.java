import java.util.Objects;
import java.util.Random;

/*should have a public constructor that accepts and sets the parameters where:
--String user: A 3-letter user code
--String product: The stock symbol for the order (1 to 5 characters)

 */
public class Order {

    private final String user;
     //A 3-letter user code – must be 3 letters, no spaces, no numbers, no special characters.
    //Cannot be null, cannot be changed once set.

    private final String product;
    // must from 1 to 5 letters/numbers, they can also have a period “.” In the symbol.
    //Cannot be null, cannot be changed once set.
   private final Price price;
   //Cannot be null, cannot be changed once set.
    private final Bookside side;
    //Cannot be null, cannot be changed once set.

    private final String id;
    //Cannot be null, must be unique, cannot be changed once set.

    private final int originalVolume;
    //final, Must be greater than 0, and less than 10,000.
    private int remainingVolume;
    // initialized to the originalVolume value. This value is changeable as the order is filled.
    private int cancelledVolume;
    //  initialized to zero. This value is changeable when any part of the order is cancelled.
    private int filledVolume;
    //  initialized to zero. This value is changeable when any part of the order is cancelled.


    public Order(String user, String product, Price price, Bookside side,
                 String id, int originalVolume, int remainingVolume, int cancelledVolume,
                 int filledVolume)  throws BadParameterException {
        this.side = side;
        if(Objects.equals(user, null) || Objects.equals(product, null) ||Objects.equals(side, null) || Objects.equals(price, null) || Objects.equals(id, null) || Objects.equals(originalVolume, null) )
            throw new BadParameterException("Error: Parameters cannot be null.");
        if(originalVolume < 0 || originalVolume > 10000)
            throw new BadParameterException("Error: original volume must be > 0, and < 10,000.");
        this.user = user;
        this.price = price;
        this.product = product;
        this.id = IDFactory.checkID(id);
        this.originalVolume = originalVolume;
        this.remainingVolume = remainingVolume;
        this.cancelledVolume = cancelledVolume;
        this.filledVolume = filledVolume;
    }
    public Order(String user, String product, Price price, Bookside side,
                 String id, int originalVolume)  throws BadParameterException {
        this.side = side;
        if(Objects.equals(user, null) || Objects.equals(product, null) ||Objects.equals(side, null) || Objects.equals(price, null) || Objects.equals(id, null) || Objects.equals(originalVolume, null) )
            throw new BadParameterException("Error: Parameters cannot be null.");
        if(originalVolume < 0 || originalVolume > 10000)
            throw new BadParameterException("Error: original volume must be > 0, and < 10,000.");
        this.user = user;
        this.price = price;
        this.product = product;
        this.id = IDFactory.checkID(id);
        this.originalVolume = originalVolume;
        this.remainingVolume = 0;
        this.cancelledVolume = 0;
        this.filledVolume = 0;
    }
    public Order(String user, String product, Price price, Bookside side,
                  int originalVolume)  throws BadParameterException {
        this.side = side;
        if(Objects.equals(user, null) || Objects.equals(product, null) ||Objects.equals(side, null) || Objects.equals(price, null) ||  Objects.equals(originalVolume, null) )
            throw new BadParameterException("Error: Parameters cannot be null.");
        if(originalVolume < 0 || originalVolume > 10000)
            throw new BadParameterException("Error: original volume must be > 0, and < 10,000.");
        this.user = user;
        this.price = price;
        this.product = product;
        Random rand = new Random();
        this.id = Integer.toString(rand.nextInt(900)+100);
        this.originalVolume = originalVolume;
        this.remainingVolume = 0;
        this.cancelledVolume = 0;
        this.filledVolume = 0;
    }

    //modifiers
    public void setRemainingVolume(int val) throws BadParameterException {
        if(Objects.equals(val, null))
        if(Objects.equals(val, null) || val < 0 || val > 10000)
            throw new BadParameterException("Error: volume must be > 0, and < 10,000.");
        this.remainingVolume = val;
    }
    public void setCancelledVolume(int val) throws BadParameterException {
        if(Objects.equals(val, null) || val < 0 || val > 10000)
            throw new BadParameterException("Error: volume must be > 0, and < 10,000.");
        this.cancelledVolume = val;
    }
    public void setFilledVolume(int val) throws BadParameterException {
        if(Objects.equals(val, null) || val < 0 || val > 10000)
            throw new BadParameterException("Error: volume must be > 0, and < 10,000.");
        this.filledVolume = val;
    }

    //accessors
    public String getUser() {
        String res = this.user;
        return res;
    }
    public Price getPrice() {
        Price res = this.price;
        return res;
    }
    public String getProduct() {
        String res = this.product;
        return res;
    }
    public Bookside getSide() {
        Bookside res = this.side;
        return res;
    }
    public String getId() {
        String res = this.id;
        return res;
    }
    public int getOriginalVolume() {
        int res = this.originalVolume;
        return res;
    }
    public int getFilledVolume() {
        int res = this.filledVolume;
        return res;
    }
    public int getRemainingVolume() {
        int res = this.remainingVolume;
        return res;
    }
    public int getCancelledVolume() {
        int res = this.cancelledVolume;
        return res;
    }

    public OrderDTO makeTradableDTO(){
        return null;
    }

    @Override
    public String toString() {
        return user + " order: "
                + side + " at " + price
                + ", Orig Vol: " + originalVolume
                + ", Rem Vol: " + remainingVolume
                + ", Fill Vol: " + filledVolume
                + ", CXL Vol: " + cancelledVolume
                + ". ID: " + id;
    }


}
