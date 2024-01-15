public class ProductBook {
    private final String product;
    private ProductBookSide buySide;
    private ProductBookSide sellSide;

    public ProductBook(String product, ProductBookSide buySide, ProductBookSide sellSide) {
        this.product = product;
        this.buySide = buySide;
        this.sellSide = sellSide;
    }
    public OrderDTO add(Order o) throws BadParameterException {
        OrderDTO odto = null;
        if(o.getSide() == Bookside.BUY) odto = buySide.add(o);
        else if(o.getSide() == Bookside.SELL) odto = sellSide.add(o);
        tryTrade();
     //   updateMarket();
        return odto;
    }
    public OrderDTO cancel(Bookside side, String orderId) throws BadParameterException {
        OrderDTO res = null;
        if(side == Bookside.BUY) res = buySide.cancel(orderId);
        if(side == Bookside.SELL) res = sellSide.cancel(orderId);
        updateMarket();
        return res;
    }
    public void tryTrade() throws BadParameterException {
        Price topB = buySide.topOfBookPrice();
        Price topS = sellSide.topOfBookPrice();
        int vol = 0;
        while(topB != null && topS != null && topB.greaterOrEqual(topS)){
            if( buySide.topOfBookVolume() >= sellSide.topOfBookVolume() ){
                vol = buySide.topOfBookVolume();
            }
            else{
                vol = sellSide.topOfBookVolume();
            }
            if(vol == 0){
                break;
            }
            buySide.tradeOut(topB, vol);
            sellSide.tradeOut(topS, vol);
            topB = buySide.topOfBookPrice();
            topS = sellSide.topOfBookPrice();
        }
       return;
    }
    private void updateMarket() throws BadParameterException {
        Price bPrice = this.buySide.topOfBookPrice();
        int bVol = this.buySide.topOfBookVolume();
        Price sPrice = this.sellSide.topOfBookPrice();
        int sVol = this.sellSide.topOfBookVolume();
        if(bPrice.equals(null)) bPrice = new Price(0);
        if(sPrice.equals(null)) sPrice = new Price(0);
        CurrentMarketTracker.updateMarket(this.product, bPrice, bVol, sPrice, sVol);

    }

    @Override
    public String toString() {
        return "ProductBook{" +
                "\n product='" + product  +
                ", \n" + buySide.toString() +
                ", \n" + sellSide.toString() +
                "\n }";
    }
}
