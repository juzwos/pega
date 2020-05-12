package example;

public class DataObjectConverter implements DataConverter {
    public DataObject Convert(String line) {
        final int expectedFields = 10;

        if(line == null)
            throw new NullPointerException();

        String[] parsedData = line.split(",");
        DataObject newData = new DataObject();

        if(parsedData.length != expectedFields)
            throw new IllegalArgumentException("Improper column number");

        newData.permalink = parsedData[0].trim();
        newData.company = parsedData[1].trim();
        newData.numEmps = parsedData[2].trim();
        newData.category = parsedData[3].trim();
        newData.city = parsedData[4].trim();
        newData.state = parsedData[5].trim();
        newData.fundedDate = parsedData[6].trim();
        newData.raisedAmt = parsedData[7].trim();
        newData.raisedCurrency = parsedData[8].trim();
        newData.round = parsedData[9].trim();

        return newData;
    }
}
