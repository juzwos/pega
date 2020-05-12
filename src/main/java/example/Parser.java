package example;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    private final DataProvider _dataProvider;
    private final DataConverter _dataConverter;

    public Parser(DataProvider dataProvider, DataConverter dataConverter) throws Exception{
        if(dataProvider == null) throw new NullPointerException();
        if(dataConverter == null) throw new NullPointerException();
        _dataProvider = dataProvider;
        _dataConverter = dataConverter;
    }

    public <T> List<T> Parse(Comparator<T> comparator, long dataLimit, boolean firstLineHasColumnNames) throws Exception {
        if(comparator == null) throw new NullPointerException();
        if(dataLimit < 1) throw new IllegalArgumentException("Limit has to be a positive integer");

        final int toSkip = firstLineHasColumnNames ? 1 : 0;

        List<?> list = _dataProvider.GetData()
                 .stream()
                 .skip(toSkip)
                 .limit(dataLimit)
                 .map(x -> _dataConverter.Convert(x))
                 .sorted((o1, o2) -> comparator.compare((T)o1, (T)o2))
                 .collect(Collectors.toList());

         return (List<T>) list;
    }
}
