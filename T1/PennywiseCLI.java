import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue; //Comparator
import java.util.Scanner;

public class PennywiseCLI {

    // Classe interna para representar a Criança
    static class Crianca implements Comparable<Crianca> {
        String nome;
        int escore;

        public Crianca(String nome, int escore) {
            this.nome = nome;
            this.escore = escore;
        }

        // Ordem natural: Menor escore primeiro. Se empatar, ordem alfabética(Política determinística citada no 2.3).
        //Comparable
        @Override
        public int compareTo(Crianca outra) {
            if (this.escore != outra.escore) {
            return Integer.compare(this.escore, outra.escore);
            }
            return this.nome.compareTo(outra.nome);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        //Por padrão, heapsort vêm como min-heap, necessário a inversão para fazer o que precisamos.
        PriorityQueue<Crianca> top10 = new PriorityQueue<>(10, (c1, c2) -> c2.compareTo(c1));

        System.out.println("Digite 'ajuda' para comandos.");
        while (true) {
            System.out.print("Pennywise> ");
            String entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) continue;

            String[] partes = entrada.split("\\s+");
            String comando = partes[0].toLowerCase(); //facilitar

            switch (comando) {
                case "consultar":
                    if (partes.length <2) {
                        System.out.println("Uso: consultar <arquivo>");
                    } else {
                        consultarArquivo(partes[1], top10);
                    }
                    break;
                case "mostrar":
                    mostrarTop10(top10);
                    break;
                case "limpar":
                    top10.clear();
                    System.out.println("Lista esvaziada.");
                    break;
                case "ajuda":
                    System.out.println("Comandos disponíveis:");
                    System.out.println("  consultar <arquivo> - Lê o arquivo da região e atualiza o Top-10");
                    System.out.println("  mostrar             - Exibe as até 10 crianças mais covardes");
                    System.out.println("  limpar              - Esvazia a estrutura");
                    System.out.println("  ajuda               - Lista os comandos");
                    System.out.println("  sair");
                    break;
                case "sair":
                    sc.close();
                    return;

                default:
                    System.out.println("ERROR. Digite 'ajuda' para ver os comandos.");
            }
        }
    }


   //O problema 
    private static void consultarArquivo(String arquivo, PriorityQueue<Crianca> top10) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            //Leitura com Buffered, while e continue faz a leitura contínua dos arquivs.
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;


                String[] dados = linha.split("\\s+");
                //armazenado: nome e converte o segundo valor para inteiro(parseInt)
                if (dados.length == 2) {
                    String nome = dados[0];
                    int escore = Integer.parseInt(dados[1]);
                    Crianca novaCrianca = new Crianca(nome, escore);

                    // Lógica do K=10 na memória
                    if (top10.size() < 10) {
                        top10.add(novaCrianca);
                    } else {
                        if (novaCrianca.compareTo(top10.peek()) < 0) {
                            top10.poll(); // remove 
                            top10.add(novaCrianca);
                        }
                    }
                }
            }
            System.out.println("Região " + arquivo+ " lida. Crianças no Top-10: " +top10.size());
        } catch (IOException e) { //tratamento de erro ba´sico
            System.out.println("Arquivo não encontrado ou erro de leitura: " +arquivo);
        } catch (NumberFormatException e) {//tratamento novamente
            System.out.println("Erro no formato numérico dentro do arquivo " + arquivo);
        }
    }

    private static void mostrarTop10(PriorityQueue<Crianca> top10) {
        if (top10.isEmpty()) {
            System.out.println("Nenhuma criança na lista.");
            return;
        }

        int n = top10.size();
        // Regra do trabalho: Exceção única e temporária de um vetor de tamanho max 10
        Crianca[] vetorTemp = new Crianca[n];
//ordem crescente
        for (int i = n - 1; i >= 0; i--) {
            vetorTemp[i] = top10.poll();
        }
        //Lista formatada
        //exibe exatamente o nome e escore da criança
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + ". " + vetorTemp[i].nome + " " + vetorTemp[i].escore);
            top10.add(vetorTemp[i]);
        }
    }
}