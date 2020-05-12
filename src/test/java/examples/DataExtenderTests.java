package examples;

import example.DataExtender;
import example.DataObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DataExtenderTests {
    @Test
    public void When_NumTestIsEmpty_ThenSetRandomData(){
        List<DataObject> data = new ArrayList<>();
        DataObject dataObject = CreateDataObject(true);
        data.add(dataObject);

        DataExtender.SetRandomNumEmps(data);

        Assert.assertTrue(data.get(0).numEmps.length() > 0);
    }

    @Test
    public void When_NumTestIsNotEmpty_ThenKeepValue(){
        List<DataObject> data = new ArrayList<>();
        DataObject dataObject = CreateDataObject(false);
        data.add(dataObject);

        DataExtender.SetRandomNumEmps(data);

        final String expectedNumEmps = "numEmps";
        Assert.assertEquals(expectedNumEmps, data.get(0).numEmps);
    }

    private static DataObject CreateDataObject(boolean numEmpsIsEmpty) {
        DataObject dataObject = new DataObject();
        dataObject.permalink = "permalink";
        dataObject.company = "company";
        dataObject.numEmps = numEmpsIsEmpty ? "" : "numEmps";
        dataObject.category = "category";
        dataObject.city = "city";
        dataObject.state = "state";
        dataObject.fundedDate = " fundedDate ";
        dataObject.raisedAmt = "raisedAmt ";
        dataObject.raisedCurrency = " raisedCurrency";
        dataObject.round = "round";

        return dataObject;
    }
}
