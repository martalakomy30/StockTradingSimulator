import java.util.*;


public class ProductBookSide {
    private final Bookside side;
    private final HashMap<Price, ArrayList<Order>> bookEntries;

    public ProductBookSide(Bookside side, HashMap<Price, ArrayList<Order>> bookEntries) {
        this.side = side;
        this.bookEntries = bookEntries;
    }
    public ProductBookSide(Bookside side) {
        this.side = side;
        this.bookEntries = new HashMap<Price, ArrayList<Order>>();
    }
    public OrderDTO add(Order o){
        if(bookEntries.containsKey(o.getPrice())) {bookEntries.get(o.getPrice()).add(o);}
        ArrayList<Order> res = new ArrayList<Order>();
        res.add(o);
        bookEntries.put(o.getPrice(), res);
        return new OrderDTO(o);
    }
    public OrderDTO cancel(String orderId) throws BadParameterException {
        for(Map.Entry<Price, ArrayList<Order>> entry: bookEntries.entrySet()){
            for (Order o: entry.getValue()) {
                if(o.getId().equals(orderId)){
                    o.setCancelledVolume(o.getCancelledVolume() + o.getRemainingVolume());
                    o.setRemainingVolume(0);
                    bookEntries.remove(entry.getKey(), o);
                    if(bookEntries.get(entry.getKey()).isEmpty()) {bookEntries.remove(entry.getKey(), entry.getValue());}
                    return new OrderDTO(o);
                }
            }
        }
        return null;
    }
    public Price topOfBookPrice() throws BadParameterException {
        Set<Price> keys = bookEntries.keySet();
        if(keys.isEmpty()) return null;
        Iterator<Price> i = keys.iterator();
        Price comp = i.next();

        if(side.equals(Bookside.BUY)){
            while(i.hasNext()){
                Price p =  i.next();
               if (( p.greaterOrEqual(comp))){ comp = p; }
               //if(i.hasNext())i.next();
            }
        } else if(side.equals(Bookside.SELL)){
            while(i.hasNext()){
                Price p = i.next();
                if (p.lessOrEqual(comp)) {comp = p;}
                //if(i.hasNext())i.next();
            }
        }
        return comp;
    }
    public int topOfBookVolume() throws BadParameterException {
        int res = 0;
        ArrayList<Order> a = null;
        Price p = null;
        if (topOfBookPrice() == null){
            return 0;
        }
        p = topOfBookPrice();
        a = bookEntries.get(p);
        if(a != null) {
            Iterator<Order> i = a.iterator();
            while (i.hasNext()) {
                res += i.next().getRemainingVolume();
                //if(i.hasNext())i.next();
            } //ASK: Should add orig or remaining vols?
        }
        return res;
    }

    //Trade out any orders at or better than the Price passed in, up to the volume value passed in
    public void tradeOut(Price price, int vol) throws BadParameterException {
        int remainingVol = vol;
        ArrayList<Order> a = bookEntries.get(price);
        Iterator<Order> i = a.iterator();
        while(remainingVol > 0){
            Order o = i.next();
            if(o.getRemainingVolume() <= remainingVol){
                a.remove(o);
                o.setFilledVolume(o.getFilledVolume() + o.getRemainingVolume());
                remainingVol = remainingVol - o.getRemainingVolume();
                o.setRemainingVolume(0);
                System.out.println("FILL");
            }else{
                o.setFilledVolume(o.getFilledVolume() + remainingVol);
                o.setRemainingVolume(o.getRemainingVolume() - remainingVol);
                System.out.println(" PARTIAL FILL");
                remainingVol = 0;
            }
        }
        if(a.isEmpty()){
            bookEntries.remove(price);
            return;
        }

    }
    @Override
    public String toString(){
        String s =  "SIDE: " + side;
        Set<Price> keys = bookEntries.keySet();
        for (Price p: keys) {
            s = s.concat("\n Price: " + p);
            for (Order o: bookEntries.get(p)) {
                s =  s.concat("\n \t" + o.toString());
            }
        }
        return s;
    }

}
