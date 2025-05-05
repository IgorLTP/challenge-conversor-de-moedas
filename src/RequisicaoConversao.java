import java.net.URI;
import java.net.http.HttpRequest;

public class RequisicaoConversao {
    public static HttpRequest criar(String moedaDe, String moedaPara) {
        String url = "https://v6.exchangerate-api.com/v6/" + ChaveApi.CHAVE + "/pair/" + moedaDe + "/" + moedaPara;
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
    }
}
