public final class CurrentMarketTracker {
    private static CurrentMarketTracker instance;
    public static CurrentMarketTracker getInstance(){
        if(instance == null) instance = new CurrentMarketTracker();
        return instance;
    }
    private CurrentMarketTracker(){}
    public static void updateMarket(String symbol, Price buyPrice, int buyVolume, Price sellPrice, int sellVolume) throws BadParameterException {
        Price marketWidth;
        if(buyPrice == null || sellPrice == null) marketWidth = new Price(0);
        marketWidth = sellPrice.subtract(buyPrice);
        CurrentMarketSide buySide = new CurrentMarketSide(buyPrice, buyVolume);
        CurrentMarketSide sellSide = new CurrentMarketSide(sellPrice, sellVolume);
        System.out.println("*********** Current Market ***********\n*" + symbol + "\t" + buyPrice.toString() + " x " + buyVolume + " - " +
                sellPrice.toString() + " x " + sellVolume + " [" + marketWidth.toString() + "]\n**************************************");
        CurrentMarketPublisher.getInstance().acceptCurrentMarket(symbol, buySide, sellSide);
    }
}
