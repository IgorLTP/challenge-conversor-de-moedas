import com.google.gson.Gson;
import java.net.http.HttpResponse;
import java.util.Map;

public class ConversorJson {
    public static double extrairTaxa(HttpResponse<String> resposta) {
        Gson gson = new Gson();
        Map<String, Object> mapa = gson.fromJson(resposta.body(), Map.class);
        return ((Number) mapa.get("conversion_rate")).doubleValue();
    }
}
