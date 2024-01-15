import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public final class CurrentMarketPublisher {
    private static CurrentMarketPublisher instance;
    private HashMap<String, ArrayList<CurrentMarketObserver>> filters;
    public static CurrentMarketPublisher getInstance(){
        if(instance == null) instance = new CurrentMarketPublisher();
        return instance;
    }
    private CurrentMarketPublisher(){
        this.filters = new HashMap<String, ArrayList<CurrentMarketObserver>>();
    }
    public void subscribeCurrentMarket(String symbol, CurrentMarketObserver cmo){
        if(!(filters.containsKey(symbol))) filters.put(symbol, new ArrayList<>());
        filters.get(symbol).add(cmo);
    }
    public void unSubscribeCurrentMarket(String symbol, CurrentMarketObserver cmo){
        if(!(filters.containsKey(symbol))) return;
        filters.get(symbol).remove(cmo);
    }
    public void acceptCurrentMarket(String symbol, CurrentMarketSide buySide, CurrentMarketSide sellSide){
        if(!(filters.containsKey(symbol))) return;
        Iterator<CurrentMarketObserver> i = filters.get(symbol).listIterator();
        while(i.hasNext()){
            CurrentMarketObserver cmo = i.next();
            cmo.updateCurrentMarket(symbol, buySide, sellSide);
        }
    }


}
