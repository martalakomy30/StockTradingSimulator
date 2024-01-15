public class OrderDTO {
    public final String user;

    public final String product;

    public final Price price;

    public final Bookside side;


    public final String id;

    public final int originalVolume;

    public int remainingVolume;
    public int cancelledVolume;
    public int filledVolume;

    public OrderDTO(String user, String product, Price price, Bookside side, String id,
                    int originalVolume, int remainingVolume, int cancelledVolume, int filledVolume){
        this.user = user;
        this.product = product;
        this.price = price;
        this.side = side;
        this.id = id;
        this.originalVolume = originalVolume;
        this.remainingVolume = remainingVolume;
        this.cancelledVolume = cancelledVolume;
        this.filledVolume = filledVolume;
    }
    public OrderDTO(Order order){
        this.user = order.getUser();
        this.product = order.getProduct();
        this.price = order.getPrice();
        this.side = order.getSide();
        this.id = order.getId();
        this.originalVolume = order.getOriginalVolume();
        this.remainingVolume = order.getRemainingVolume();
        this.cancelledVolume = order.getCancelledVolume();
        this.filledVolume = order.getFilledVolume();
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
