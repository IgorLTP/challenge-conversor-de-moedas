import java.net.http.HttpClient;

public class ClienteHttp {
    public static HttpClient criar() {
        return HttpClient.newHttpClient();
    }
}
