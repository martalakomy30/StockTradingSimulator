/*
public class MainHW1 {

    public static void main(String[] args) throws BadParameterException {
       Price i1 = PriceFactory.makePrice(1285);
       Price s1 = PriceFactory.makePrice("12.85");
       System.out.println("Should print '$12.85' twice: ");
       System.out.println(i1.toString());
       System.out.println(s1.toString());
       Price i2 = PriceFactory.makePrice(-75);
       Price s2 = PriceFactory.makePrice("-.75");
       System.out.println("Should print '$-0.75' twice: ");
       System.out.println(i2.toString());
       System.out.println(s2.toString());
       Price i3 = PriceFactory.makePrice(1234567);
       Price s3 = PriceFactory.makePrice("$12,345.67");
       System.out.println("Should print '$12345.67' twice: ");
       System.out.println(i3.toString());
       System.out.println(s3.toString());
       Price i4 = PriceFactory.makePrice(-123456789);
       Price s4 = PriceFactory.makePrice("$-1,234,567.89");
       System.out.println("Should print '$-1234567.89' twice: ");
       System.out.println(i4.toString());
       System.out.println(s4.toString());

       //isNegative()
       Price i5 = PriceFactory.makePrice(0);
       Price s5 = PriceFactory.makePrice("0");
       System.out.println("Should be false: " + i1.isNegative());
       System.out.println("Should be false: " + s1.isNegative());
       System.out.println("Should be true: " + i2.isNegative());
       System.out.println("Should be true: " + s2.isNegative());
       System.out.println("Should be false: " + i3.isNegative());
       System.out.println("Should be false: " + s3.isNegative());
       System.out.println("Should be true: " + i4.isNegative());
       System.out.println("Should be true: " + s4.isNegative());
       System.out.println("Should be false: " + i5.isNegative());
       System.out.println("Should be false: " + s5.isNegative());

       //add()
       System.out.println("Should be 12.85: " + s5.add(i1).toString());
       System.out.println("Should be 12.10: " + s2.add(i1).toString());
       System.out.println("Should be -1.50: " + i2.add(s2).toString());

       //subtract()
       System.out.println("Should be -12.85: " + s5.subtract(i1).toString());
       System.out.println("Should be -13.60: " + s2.subtract(i1).toString());
       System.out.println("Should be 0: " + i2.subtract(s2).toString());

       //multiply()
       i1 = PriceFactory.makePrice(500);
       s1 = PriceFactory.makePrice("5.00");
       i2 = PriceFactory.makePrice(-600);
       s2 = PriceFactory.makePrice("-6.00");
       System.out.println("Should be 0: " + s5.multiply(i1).toString());
       System.out.println("Should be -30.00: " + s2.multiply(i1).toString());
       System.out.println("Should be 36.00: " + i2.multiply(s2).toString());

       //greaterOrEqual()
       System.out.println("Should be true: " + i1.greaterOrEqual(s1));
       System.out.println("Should be true: " + i1.greaterOrEqual(s2));
       System.out.println("Should be true: " + i5.greaterOrEqual(s5));
       System.out.println("Should be true: " + i5.greaterOrEqual(s2));
       System.out.println("Should be false: " + i2.greaterOrEqual(s1));
       System.out.println("Should be false: " + i2.greaterOrEqual(s5));

       //lessOrEqual()
       System.out.println("Should be true: " + i1.lessOrEqual(s1));
       System.out.println("Should be false: " + i1.lessOrEqual(s2));
       System.out.println("Should be true: " + i5.lessOrEqual(s5));
       System.out.println("Should be false: " + i5.lessOrEqual(s2));
       System.out.println("Should be true: " + i2.lessOrEqual(s1));
       System.out.println("Should be true: " + i2.lessOrEqual(s5));

       //greaterThan()
       System.out.println("Should be false: " + i1.greaterThan(s1));
       System.out.println("Should be true: " + i1.greaterThan(s2));
       System.out.println("Should be false: " + i5.greaterThan(s5));
       System.out.println("Should be true: " + i5.greaterThan(s2));
       System.out.println("Should be false: " + i2.greaterThan(s1));
       System.out.println("Should be false: " + i2.greaterThan(s5));

       //lessThan()
       System.out.println("Should be false: " + i1.lessThan(s1));
       System.out.println("Should be false: " + i1.lessThan(s2));
       System.out.println("Should be false: " + i5.lessThan(s5));
       System.out.println("Should be false: " + i5.lessThan(s2));
       System.out.println("Should be true: " + i2.lessThan(s1));
       System.out.println("Should be true: " + i5.lessThan(s1));
       System.out.println("Should be true: " + i2.lessThan(s5));

       //equals
       System.out.println("Should be true: " + i1.equals(s1));
       System.out.println("Should be true: " + i2.equals(s2));
       System.out.println("Should be true: " + i3.equals(s3));
       System.out.println("Should be true: " + i5.equals(s5));
       System.out.println("Should be false: " + i2.equals(s1));
       System.out.println("Should be false: " + s3.equals(s1));
       System.out.println("Should be false: " + s4.equals(i1));
       System.out.println("Should be false: " + i3.equals(s5));

       //compareTo
       System.out.println("Should be 1100: " + i1.compareTo(s2));
       System.out.println("Should be 600: " + i5.compareTo(s2));
       System.out.println("Should be 500: " + i5.compareTo(s1));
       System.out.println("Should be 0: " + i5.compareTo(s5));
       System.out.println("Should be 0: " + i2.compareTo(s2));

       //hashCode()

       System.out.print("Should be true: " );
       if(i1.hashCode() == s1.hashCode()) System.out.println("true");
       System.out.print("Should be true: " );
       if(i2.hashCode() == s2.hashCode()) System.out.println("true");
       System.out.print("Should be true: " );
       if(i3.hashCode() == s3.hashCode()) System.out.println("true");
       System.out.print("Should be true: " );
       if(i5.hashCode() == s5.hashCode()) System.out.println("true");
       System.out.print("Should be false: ");
       if(i2.hashCode() != s1.hashCode()) System.out.println("false");
       System.out.print("Should be false: ");
       if(i5.hashCode() != s1.hashCode()) System.out.println("false");
       System.out.print("Should be false: ");
       if(i3.hashCode() != s2.hashCode()) System.out.println("false");
       System.out.print("Should be false: ");
       if(i4.hashCode() != s5.hashCode()) System.out.println("false");

    }
}
*/