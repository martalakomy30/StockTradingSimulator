import java.util.ArrayList;

public class IDFactory {
    private static ArrayList<String> ids = new ArrayList<String>();



    public static String checkID(String val) throws BadParameterException{
        if (ids.contains(val)) throw new BadParameterException("Error: ID already exists");
        ids.add(val);
        return val;
    }



}
