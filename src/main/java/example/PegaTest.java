package example;

import java.util.List;

public class PegaTest {
    public static void main(String[] args) throws Exception {
        URLReader urlReader = new URLReader("http://samplecsvs.s3.amazonaws.com/TechCrunchcontinentalUSA.csv");
        Parser parser = new Parser(urlReader, new DataObjectConverter());
        List<DataObject> data = parser.Parse(DataObject::compareByNameThenAge, 20, true);
        DataExtender.SetRandomNumEmps(data);
    }
}
