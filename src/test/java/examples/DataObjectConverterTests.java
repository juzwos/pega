package examples;

import example.DataObject;
import example.DataObjectConverter;
import org.junit.Assert;
import org.junit.Test;

public class DataObjectConverterTests {
    @Test(expected = NullPointerException.class)
    public void When_ArgumentIsNull_Then_ExceptionIsThrown() {
        DataObjectConverter dataObjectConverter = new DataObjectConverter();
        dataObjectConverter.Convert(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void When_ArgumentIsEmpty_Then_ExceptionIsThrown() {
        DataObjectConverter dataObjectConverter = new DataObjectConverter();
        dataObjectConverter.Convert("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void When_LineDoeasNotHaveAProperDelimiter_Then_ExceptionIsThrown() {
        DataObjectConverter dataObjectConverter = new DataObjectConverter();
        dataObjectConverter.Convert("1;2;3;4;5;6;7;8;9;10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void When_LineHasToFewColumns_Then_ExceptionIsThrown() {
        DataObjectConverter dataObjectConverter = new DataObjectConverter();
        dataObjectConverter.Convert("1,2,3,4,5");
    }

    @Test(expected = IllegalArgumentException.class)
    public void When_LineHasToMuchColumns_Then_ExceptionIsThrown() {
        DataObjectConverter dataObjectConverter = new DataObjectConverter();
        dataObjectConverter.Convert("1,2,3,4,5,6,7,8,9,10,11");
    }

    @Test
    public void When_LineHasProperNumberOfColumns_Then_DataIsParsed() {

        final String permalink = "permalink";
        final String company = "company";
        final String numEmps = "numEmps";
        final String category = "category";
        final String city = "city";
        final String state = "state";
        final String fundedDate = "fundedDate";
        final String raisedAmt = "raisedAmt";
        final String raisedCurrency = "raisedCurrency";
        final String round = "round";

        final String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", permalink, company, numEmps, category, city, state, fundedDate, raisedAmt,raisedCurrency, round);

        DataObjectConverter dataObjectConverter = new DataObjectConverter();
        DataObject dataObject = dataObjectConverter.Convert(line);

        Assert.assertEquals(permalink, dataObject.permalink);
        Assert.assertEquals(company, dataObject.company);
        Assert.assertEquals(numEmps, dataObject.numEmps);
        Assert.assertEquals(category, dataObject.category);
        Assert.assertEquals(city, dataObject.city);
        Assert.assertEquals(state, dataObject.state);
        Assert.assertEquals(fundedDate, dataObject.fundedDate);
        Assert.assertEquals(raisedAmt, dataObject.raisedAmt);
        Assert.assertEquals(raisedCurrency, dataObject.raisedCurrency);
        Assert.assertEquals(round, dataObject.round);
    }

    @Test
    public void When_LineElementHasEmptySpaceBeginEnd_Then_ElementIsTrimmed() {

        final String permalink = "permalink";
        final String company = "company";
        final String numEmps = "numEmps";
        final String category = "category";
        final String city = "city";
        final String state = "state";
        final String fundedDate = " fundedDate ";
        final String raisedAmt = "raisedAmt ";
        final String raisedCurrency = " raisedCurrency";
        final String round = "round";

        final String expectedFundedDate = "fundedDate";
        final String expectedRaisedAmt = "raisedAmt";
        final String expectedRaisedCurrency = "raisedCurrency";

        final String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", permalink, company, numEmps, category, city, state, fundedDate, raisedAmt,raisedCurrency, round);

        DataObjectConverter dataObjectConverter = new DataObjectConverter();
        DataObject dataObject = dataObjectConverter.Convert(line);

        Assert.assertEquals(expectedFundedDate, dataObject.fundedDate);
        Assert.assertEquals(expectedRaisedAmt, dataObject.raisedAmt);
        Assert.assertEquals(expectedRaisedCurrency, dataObject.raisedCurrency);
    }
}