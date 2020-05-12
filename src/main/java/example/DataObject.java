package example;

public class DataObject {
    public String permalink;
    public String company;
    public String numEmps;
    public String category;
    public String city;
    public String state;
    public String fundedDate;
    public String raisedAmt;
    public String raisedCurrency;
    public String round;

    public static int compareByNameThenAge(DataObject lhs, DataObject rhs) {
        return lhs.city.compareTo(rhs.city);
    }
}
