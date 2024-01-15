import java.util.HashMap;
import java.util.Iterator;

public class ProductManager {
    private static ProductManager pm_instance;
    private HashMap<String, ProductBook> books;
    private ProductManager(){
        this.books = new HashMap<String, ProductBook>();
    }
    public static ProductManager getInstance() {
        if (pm_instance == null) pm_instance = new ProductManager();
    return pm_instance;
    }
    public void addProduct(String symbol){
        this.books.put(symbol, new ProductBook(symbol, new ProductBookSide(Bookside.BUY), new ProductBookSide(Bookside.SELL)));
    }
    public String getRandomProduct(){
        if(books.isEmpty()) return null;
        int rand = (int) Math.floor(Math.random() * (books.keySet().size()-1));
        if (books.keySet().toArray()[rand] == null) {return this.getRandomProduct();}
        else{return (String) books.keySet().toArray()[rand];}
    }
    public OrderDTO addOrder(Order o) throws BadParameterException {
        if (o.equals(null))return null;
        return books.get(o.getProduct()).add(o);
    }
    public OrderDTO cancel(OrderDTO o) throws BadParameterException {
        OrderDTO res = books.get(o.product).cancel(o.side, o.id);
        if(res == null ) System.out.println(new String("Cancel attempt failed"));
        return res;
    }

    @Override
    public String toString() {
        String s = "";
        Iterator<ProductBook> i = this.books.values().iterator();
        while (i.hasNext()){
            ProductBook res = i.next();
            s = s.concat("\n" + res.toString());
        }
        return s;
    }
}
