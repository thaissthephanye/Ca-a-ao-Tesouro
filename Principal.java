import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Jogo jogo = new Jogo();  // Cria o jogo
        jogo.iniciar();          // Começa!
    }
}

class Jogo {
    private Jogador jogador;
    private Tabuleiro tabuleiro;
    private Scanner scanner;

    public Jogo() {
        jogador = new Jogador();    // Cria jogador
        tabuleiro = new Tabuleiro(); // Cria tabuleiro
        scanner = new Scanner(System.in); // Para ler entrada
    }

    public void iniciar() {
        System.out.println("Bem-vindo ao Caça ao Tesouro! Use WASD para mover.");

        // Loop principal do jogo
        while (jogador.getMovimentosRestantes() > 0 && tabuleiro.getTesourosRestantes() > 0) {
            tabuleiro.imprimir(jogador);  // Mostra tabuleiro

            // Mostra status
            System.out.println("\nPontos: " + jogador.getPontos());
            System.out.println("Movimentos restantes: " + jogador.getMovimentosRestantes());
            System.out.println("Tesouros restantes: " + tabuleiro.getTesourosRestantes());
            System.out.print("Próximo movimento (W/A/S/D): ");

            // Lê movimento (W, A, S, D)
            char movimento = scanner.next().toUpperCase().charAt(0);

            // Verifica se é válido
            if ("WASD".indexOf(movimento) == -1) {
                System.out.println("Movimento inválido! Use W, A, S ou D.");
                continue;
            }

            // Move o jogador
            jogador.mover(movimento);

            // Interage com a célula
            ElementoTabuleiro elemento = tabuleiro.interagir(jogador.getX(), jogador.getY());

            if (elemento != null) {  // Se havia algo novo
                int pontos = elemento.interagir();  // Pega pontos
                jogador.adicionarPontos(pontos);    // Adiciona ao total

                System.out.println("Você encontrou: " + elemento.simbolo());
                System.out.println("Pontos ganhos: " + pontos);
            }
        }

        // Fim do jogo
        tabuleiro.imprimir(jogador);
        System.out.println("\nFim do jogo!");
        System.out.println("Pontuação final: " + jogador.getPontos());
        System.out.println("Tesouros encontrados: " + (3 - tabuleiro.getTesourosRestantes()));

        scanner.close();
    }
}