import java.util.Scanner;
import java.net.http.*;

public class Principal {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        HttpClient cliente = ClienteHttp.criar();

        while (true) {
            System.out.println("************************************");
            System.out.println("Bem-vindo/a ao Conversor de Moeda =]");
            System.out.println("************************************");

            System.out.println("Moedas suportadas: USD, BRL, JPY, RUB, CAD, AUD, EUR, SYP, TWD");
            System.out.print("Digite a moeda de ORIGEM (ex: USD): ");
            String moedaDe = scanner.next().toUpperCase();

            System.out.print("Digite a moeda de DESTINO (ex: BRL): ");
            String moedaPara = scanner.next().toUpperCase();

            System.out.print("Digite o valor a converter: ");
            double valor = scanner.nextDouble();

            HttpRequest requisicao = RequisicaoConversao.criar(moedaDe, moedaPara);
            HttpResponse<String> resposta = cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());

            if (resposta.statusCode() == 200) {
                double taxa = ConversorJson.extrairTaxa(resposta);
                double convertido = CalculadoraConversao.converter(valor, taxa);

                System.out.printf("Taxa de conversão: 1 %s = %.4f %s%n", moedaDe, taxa, moedaPara);
                System.out.printf("Valor convertido: %.2f %s%n", convertido, moedaPara);
            } else {
                System.out.println("Erro na requisição! Código: " + resposta.statusCode());
            }

            System.out.print("Deseja fazer outra conversão? (s/n): ");
            String opcao = scanner.next();
            if (!opcao.equalsIgnoreCase("s")) {
                break;
            }
        }

        scanner.close();
        System.out.println("Obrigado por usar o conversor!");
    }
}
