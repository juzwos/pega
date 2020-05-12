package example;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class DataExtender {
    public static void SetRandomNumEmps(List<DataObject> dataObjectList){
        final Predicate<String> valueNotNullOrEmpty = e -> (e != null) && !(e.trim().length() > 0);

        dataObjectList
                .stream()
                .filter(x->valueNotNullOrEmpty.test(x.numEmps))
                .forEach(x->x.numEmps = RandomString());
    }

    private static String RandomString() {
        final int leftLimit = 97;
        final int rightLimit = 122;
        final int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random
                .ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
