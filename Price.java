final class Price {
    private int priceValue; //value in cents
    Price(int value) {
        priceValue = value;
    }
    public boolean isNegative() {
        return this.priceValue < 0;
    }
    public Price add(Price p) throws BadParameterException{
        if(p == null) throw new BadParameterException("Cannot add null price.");
        int res = this.priceValue + p.priceValue;
        return new Price(res);
    }
    public Price subtract(Price p) throws BadParameterException{
        if(p == null) throw new BadParameterException("Cannot subtract null price.");
        int res = this.priceValue - p.priceValue;
        return new Price(res);
    }
    public Price multiply(Price p) throws BadParameterException{
        if(p == null) throw new BadParameterException("Cannot multiply null price.");
        int res = (this.priceValue * p.priceValue)/100;
        return new Price(res);
    }
    public boolean lessOrEqual(Price p) throws BadParameterException{
        if(p == null) throw new BadParameterException("Cannot compare null price");
        return (this.priceValue <= p.priceValue);
    }
    public boolean greaterOrEqual(Price p) throws BadParameterException {
        if(p == null) throw new BadParameterException("Cannot compare null price");
        return (this.priceValue >= p.priceValue);
    }
    public boolean greaterThan(Price p)  throws BadParameterException{
        if(p == null) throw new BadParameterException("Cannot compare null price");
        return this.priceValue > p.priceValue;
    }
    public boolean lessThan(Price p)  throws BadParameterException{
        if(p == null) throw new BadParameterException("Cannot compare null price");
        return this.priceValue < p.priceValue;
    }

    @Override
    public boolean equals(Object p) {
        if(p instanceof Price) {
            if (this == null && p == null) return true;
            if (this != null && p != null) {
            Price pPrice = (Price) p;
            return (this.priceValue == pPrice.priceValue);
            }
        }
        return false;
    }
    public int compareTo(Price p)  throws BadParameterException{
        if(p == null) throw new BadParameterException("Cannot compare null price");
        int res = this.priceValue - p.priceValue;
        if(res < 0 ) return res*-1;
        return res;
    }

    @Override
    public String toString(){
        if(this.priceValue >= 0 ){ //positive values
            if(this.priceValue % 100 != 0){
                return "$" + (this.priceValue - (this.priceValue % 100))/100
                        + "." + (this.priceValue % 100);
            } else return "$" + (this.priceValue - (this.priceValue % 100))/100 + ".00";
        }else{
            if(this.priceValue % 100 != 0){ //negative values
                return "$-" + (-1*(this.priceValue - (this.priceValue % 100))/100)
                        + "." + (-1*(this.priceValue % 100));
            } else return "$-" + (this.priceValue - (this.priceValue % 100))/-100 + ".00";
        }
    }
    @Override
    public int hashCode(){
        return this.priceValue*31;
    }
}
