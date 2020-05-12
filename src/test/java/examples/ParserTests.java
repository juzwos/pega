package examples;

import example.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class ParserTests {
    @Mock DataProvider _dataProviderMock;
    @Mock DataConverter _dataConverterMock;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test(expected = NullPointerException.class)
    public void When_DataProvidertIsNull_Then_ExceptionIsThrown() throws Exception {
        Parser parser = new Parser(null, _dataConverterMock);
    }

    @Test(expected = NullPointerException.class)
    public void When_DataConvertertIsNull_Then_ExceptionIsThrown() throws Exception {
        Parser parser = new Parser(_dataProviderMock, null);
    }

    @Test(expected = NullPointerException.class)
    public void When_ComparatorIsNull_Then_ExceptionIsThrown() throws Exception {
        Parser parser = new Parser(_dataProviderMock, _dataConverterMock);
        parser.Parse(null,10,true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void When_DataLimitIslessThan1_Then_ExceptionIsThrown() throws Exception {
        Parser parser = new Parser(_dataProviderMock, _dataConverterMock);
        parser.Parse(DataObject::compareByNameThenAge,0,true);
    }
    @Test
    public void When_DataLimitIsSet_Then_DataCollectionHasProperNumberOfData() throws Exception {
        when(_dataProviderMock.GetData()).thenReturn(ReturnData());
        final int expectedElements = 7;

        Parser parser = new Parser(_dataProviderMock, new DataObjectConverter());
        List<DataObject> data = parser.Parse(DataObject::compareByNameThenAge,expectedElements,true);


        Assert.assertEquals(expectedElements, data.size());
    }

    @Test
    public void When_DataComparatorIsSet_Then_DataCollectionHasProperOrder() throws Exception {
        when(_dataProviderMock.GetData()).thenReturn(ReturnData());
        final int expectedElements = 7;
        List<String> expectedCities = new ArrayList<>();
        expectedCities.add("Gilbert");
        expectedCities.add("Palo Alto");
        expectedCities.add("Phoenix");
        expectedCities.add("San Francisco");
        expectedCities.add("Scottsdale");
        expectedCities.add("Scottsdale");
        expectedCities.add("Tempe");

        Parser parser = new Parser(_dataProviderMock, new DataObjectConverter());
        List<DataObject> data = parser.Parse(DataObject::compareByNameThenAge,expectedElements,true);

        int i = 0;
        for(DataObject dataObject : data) {
            Assert.assertEquals(expectedCities.get(i++), dataObject.city);
        }
    }
    
    private static List<String> ReturnData() {
        List<String> data = new ArrayList<>();
        data.add("permalink,company,numEmps,category,city,state,fundedDate,raisedAmt,raisedCurrency,round");
        data.add("lifelock,LifeLock,,web,Tempe,AZ,1-May-07,6850000,USD,b");
        data.add("mycityfaces,MyCityFaces,7,web,Scottsdale,AZ,1-Jan-08,50000,USD,seed");
        data.add("flypaper,Flypaper,,web,Phoenix,AZ,1-Feb-08,3000000,USD,a");
        data.add("infusionsoft,Infusionsoft,105,software,Gilbert,AZ,1-Oct-07,9000000,USD,a");
        data.add("chosenlist-com,ChosenList.com,5,web,Scottsdale,AZ,1-Oct-06,140000,USD,seed");
        data.add("digg,Digg,60,web,San Francisco,CA,1-Dec-06,8500000,USD,b");
        data.add("facebook,Facebook,450,web,Palo Alto,CA,1-Sep-04,500000,USD,angel");

        return data;
    }
}
