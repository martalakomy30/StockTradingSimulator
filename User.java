import java.lang.reflect.Array;
import java.util.*;

public class User implements CurrentMarketObserver {
    private final String userId;
    private HashMap<String, OrderDTO> orders;
    private HashMap<String, CurrentMarketSide[]> currentMarkets;
    public User(String userId) throws BadParameterException{

        this.userId = this.setUserId(userId);
        this.orders = new HashMap<String, OrderDTO>();
        this.currentMarkets = new HashMap<String, CurrentMarketSide[]>();
    }
    public String getUserId() {
        return userId;
    }
    private String setUserId(String userId) throws BadParameterException{
        if(userId == null || userId.length() != 3) throw new BadParameterException("User ID must be exactly 3 letters long.");
        for (int i = 0; i < 3; i++) {
            if (!(Character.isLetter(userId.charAt(i)))) throw new BadParameterException("User ID can only contain letters.");
        }
        return userId;
    }
    public void addOrder(OrderDTO o)throws BadParameterException{
        if(o == null) throw new BadParameterException("added OrderDTO cannot be null");
        this.orders.put(o.id, o);
    }
    public boolean hasOrderWithRemainingQty(){
        Iterator<OrderDTO> i = this.orders.values().iterator();
        while (i.hasNext()){
            OrderDTO res = i.next();
            if(res.remainingVolume > 0) return true;
        }
        return false;
    }
    public OrderDTO getOrderWithRemainingQty(){
        Iterator<OrderDTO> i = this.orders.values().iterator();
        while (i.hasNext()){
            OrderDTO res = i.next();
            if(res.remainingVolume > 0) return res;
        }
        return null;
    }
    @Override
    public void updateCurrentMarket(String symbol, CurrentMarketSide buySide, CurrentMarketSide sellSide) {
        currentMarkets.put(symbol, new CurrentMarketSide[]{buySide, sellSide});
    }
    public String getCurrentMarkets(){
        Iterator i = this.currentMarkets.keySet().iterator();
        String res = "";
        while(i.hasNext()){
            String sym = (String) i.next();
            res += "\n" + sym + "\t" + currentMarkets.get(sym)[0].toString() + " - " + currentMarkets.get(sym)[1].toString();
        }
        return res;
    }

    @Override
    public String toString() {
        String s =  "UserId: '" + userId + "\n";;
        Iterator<OrderDTO> i = this.orders.values().iterator();
        while (i.hasNext()){
            OrderDTO res = i.next();
            s = s.concat("\n \t Product: " + res.product + ", Price: " + res.price+ ", OriginalVolume: "
                    + res.originalVolume + ", RemainingVolume: " + res.remainingVolume
                    + ", CancelledVolume: " + res.cancelledVolume + ", FilledVolume: "
                    + res.filledVolume + ", User: " + res.user + ", Side: " + res.side + ", Id: " + res.id);
        }
        return s;
    }


}
