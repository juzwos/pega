package example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class URLReader implements DataProvider {
    private final String _url;
    public URLReader(String url) {
        _url = url;
    }
    public List<String> GetData() throws Exception {
        URL data = new URL(_url);
        URLConnection connection = data.openConnection();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        List<String> datas = new ArrayList<>();

        String singeLine;
        while((singeLine = buffer.readLine()) != null){
            datas.add(singeLine);
        }

        return datas;
    }

}
