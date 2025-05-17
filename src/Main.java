import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConsultaCep consultaCep = new ConsultaCep();
        ConsultaEndereco consultaEndereco = new ConsultaEndereco();

        Scanner scanner = new Scanner(System.in);
        int opcao =0;
        while(opcao != 3) {
            System.out.println(
                    "=== MENU ===\n" +
                            "1 - Buscar endereço pelo CEP\n" +
                            "2 - Buscar CEP pelo endereço\n" +
                            "3 - Sair\n" +
                            "Escolha uma opção: "
            );
            opcao = Integer.parseInt(scanner.nextLine());
            if(opcao ==1) {
                System.out.println("Digite o CEP para descobrir o endereço: ");
                var cep = scanner.nextLine();
                try {
                    Endereco endereco = consultaCep.buscaEndereco(cep);
                    System.out.println(endereco);
                    GeradorDeArquivo geradorDeArquivo = new GeradorDeArquivo();
                    geradorDeArquivo.GeraJson(endereco);
                } catch (RuntimeException | IOException e) {
                    System.out.println(e.getMessage());
                }
            }else if (opcao ==2){
                System.out.println("Digite o UF, cidade e logradouro, nessa ordem, para descobrir o CEP: ");
                System.out.println("UF: ");
                var uf = scanner.nextLine();
                System.out.println("Cidade: ");
                var cidade = scanner.nextLine();
                System.out.println("Logradouro: ");
                var logradouro = scanner.nextLine();

                try {
                    Endereco cep = consultaEndereco.buscaCep(uf, cidade, logradouro);
                    System.out.println(cep);
                }catch (RuntimeException e){
                    System.out.println(e.getMessage());
                }
            }else if (opcao!=3){
                System.out.println("Opcão digitada inválida.");
            }
        }
    }
}