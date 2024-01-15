import java.util.HashMap;

public class TradingSim {
    private static HashMap<String, Double> basePrices;
    public static void runSim() throws BadParameterException {
        UserManager.init(new String[]{"ANN", "BOB", "CAT", "DOG", "EGG"});
        User ann = UserManager.getUser("ANN");
        User bob = UserManager.getUser("BOB");
        User cat = UserManager.getUser("CAT");
        User dog = UserManager.getUser("DOG");
        User egg = UserManager.getUser("EGG");
        CurrentMarketPublisher.getInstance().subscribeCurrentMarket("WGT", ann);
        CurrentMarketPublisher.getInstance().subscribeCurrentMarket("TGT", ann);
        CurrentMarketPublisher.getInstance().subscribeCurrentMarket("TGT", bob);
        CurrentMarketPublisher.getInstance().subscribeCurrentMarket("TSLA", bob);
        CurrentMarketPublisher.getInstance().subscribeCurrentMarket("AMZM", cat);
        CurrentMarketPublisher.getInstance().subscribeCurrentMarket("TGT", cat);
        CurrentMarketPublisher.getInstance().subscribeCurrentMarket("WMT", cat);
        CurrentMarketPublisher.getInstance().subscribeCurrentMarket("TSLA", dog);
        CurrentMarketPublisher.getInstance().subscribeCurrentMarket("WMT", egg);
        CurrentMarketPublisher.getInstance().unSubscribeCurrentMarket("TGT", bob);
        ProductManager.getInstance().addProduct("WMT");
        ProductManager.getInstance().addProduct("TGT");
        ProductManager.getInstance().addProduct("AMZN");
        ProductManager.getInstance().addProduct("TSLA");

        basePrices = new HashMap<String, Double>();
        basePrices.put("WMT", 140.98);
        basePrices.put("TGT", 176.76);
        basePrices.put("AMZN", 102.11);
        basePrices  .put("TSLA", 196.81);
        for(int i = 0; i < 1000; i++){
            User u = UserManager.getInstance().getRandomUser();
            if(Math.random() < 0.9){
                String sym = ProductManager.getInstance().getRandomProduct();
                Bookside side = Bookside.SELL;
                if(Math.random() <= 0.5) side = Bookside.BUY;
                int vol = (int)(25+(Math.random()*300));
                vol = (int)(Math.round(vol/5.0))*5;
                Price p = getPrice(sym, side);
                Order o = new Order(u.getUserId(), sym, p, side, vol );
                OrderDTO odto = ProductManager.getInstance().addOrder(o);
                u.addOrder(odto);
            }
            if(u.hasOrderWithRemainingQty()){
                OrderDTO odto = u.getOrderWithRemainingQty();
                OrderDTO cancelled = ProductManager.getInstance().cancel(odto);
                if(!(cancelled ==null)){
                    u.addOrder(cancelled);
                }
            }
        }
        System.out.println(ann.getCurrentMarkets());
        System.out.println(bob.getCurrentMarkets());
        System.out.println(cat.getCurrentMarkets());
        System.out.println(dog.getCurrentMarkets());
        System.out.println(egg.getCurrentMarkets());

        CurrentMarketPublisher.getInstance().unSubscribeCurrentMarket("WGT", ann);
        CurrentMarketPublisher.getInstance().unSubscribeCurrentMarket("TGT", ann);
        CurrentMarketPublisher.getInstance().unSubscribeCurrentMarket("TSLA", bob);
        CurrentMarketPublisher.getInstance().unSubscribeCurrentMarket("AMZM", cat);
        CurrentMarketPublisher.getInstance().unSubscribeCurrentMarket("TGT", cat);
        CurrentMarketPublisher.getInstance().unSubscribeCurrentMarket("WMT", cat);
        CurrentMarketPublisher.getInstance().unSubscribeCurrentMarket("TSLA", dog);
        CurrentMarketPublisher.getInstance().unSubscribeCurrentMarket("WMT", egg);

        System.out.println(ProductManager.getInstance().toString());
        System.out.println(UserManager.getInstance().toString());


    }
    private static Price getPrice(String symbol, Bookside side) throws BadParameterException {
        if(symbol == null || side == null) throw new BadParameterException("Error in TradingSim getPrice(): Symbol and bookside cannot be null.");
        Double basePrice = basePrices.get(symbol);
        double priceWidth = 0.02;
        double startPoint = 0.01;
        double tickSize = 0.1;
        double gapFromBase = basePrice * priceWidth; // 5.0
        double priceVariance = gapFromBase * (Math.random());
        double priceToTick;
        double priceToUse;
        if(side == Bookside.BUY){
            priceToUse = basePrice * (1 - startPoint);
            priceToUse += priceVariance;
        }else{
            priceToUse = basePrice * (1 + startPoint);
            priceToUse -= priceVariance;
        }
        priceToTick = Math.round(priceToUse * 1/tickSize) / 20.0;
        return PriceFactory.makePrice(Double.toString(priceToTick));
    }
}
