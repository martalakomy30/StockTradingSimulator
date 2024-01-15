public class PriceFactoryPreFactor {
    public static Price makePrice(int value){
        return new Price(value);
    }

    //string passed in, converted to cents, price object created
    public static Price makePrice(String stringValueIn) throws BadParameterException { // "$12,345.67"
        stringValueIn = stringValueIn.replace("$", ""); // "12,345.67"
        stringValueIn = stringValueIn.replace(",", ""); // "12345.67"
        try{ double x = Double.parseDouble(stringValueIn); }
        catch(NumberFormatException nfe){ throw new BadParameterException(nfe.getMessage()); }
        //At this point, we know the string has no strange characters
        //split string into before decimal and after
        String[] parts = stringValueIn.split("\\."); // ["12345", "56"]
        int a = 0;
        int b = 0;
        int c;
        if(parts.length > 1){b = Integer.parseInt(parts[1]);} //handles no split
        if(parts[0].endsWith("-")){ b = b * -1;} //handles "$-.89" case
        else { a = Integer.parseInt(parts[0]); }
        if (a < 0 ) c = (a * 100) - b; //handles adding negative balance
        else c = (a * 100) + b;


        return new Price(c);
    }

}
