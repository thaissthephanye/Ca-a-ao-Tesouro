import java.util.Scanner;

// Classe principal que inicia o jogo
public class Principal {
    public static void main(String[] args) {
        Jogo jogo = new Jogo();  // Cria o jogo
        jogo.iniciar();          // Começa a jogatina!
    }
}

// Controla o fluxo do jogo
class Jogo {
    private Jogador jogador;    // O jogador
    private Tabuleiro tabuleiro; // O tabuleiro
    private Scanner scanner;     // Para ler input

    public Jogo() {
        jogador = new Jogador();    // Cria jogador
        tabuleiro = new Tabuleiro(); // Cria tabuleiro
        scanner = new Scanner(System.in); // Prepara para ler teclado
    }

    // Método principal que roda o jogo
    public void iniciar() {
        System.out.println("🏴‍☠️ Bem-vindo ao Caça ao Tesouro! 🏴‍☠️");
        System.out.println("Use W (cima), S (baixo), A (esquerda), D (direita) para se mover.");
        System.out.println("Encontre os tesouros (💰) e evite as armadilhas (💣)!");

        // Loop principal do jogo
        while (jogador.getMovimentosRestantes() > 0 && tabuleiro.getTesourosRestantes() > 0) {
            tabuleiro.imprimir(jogador);  // Mostra o tabuleiro
            
            // Mostra status atual
            System.out.println("\nPontos: " + jogador.getPontos());
            System.out.println("Movimentos restantes: " + jogador.getMovimentosRestantes());
            System.out.println("Tesouros restantes: " + tabuleiro.getTesourosRestantes());
            System.out.print("Próximo movimento (W/A/S/D): ");

            // Lê o movimento (apenas primeiro caractere, em maiúsculo)
            char movimento = scanner.next().toUpperCase().charAt(0);
            
            // Verifica se é movimento válido
            if ("WASD".indexOf(movimento) == -1) {
                System.out.println("❌ Movimento inválido! Use W, A, S ou D.");
                continue;
            }
            
            // Tenta mover o jogador
            boolean moveu = jogador.mover(movimento, tabuleiro.getPosicoesVisitadas());
            
            if (!moveu) {
                System.out.println("🚫 Você já visitou esta casa! Escolha outra direção.");
                continue;
            }

            // Interage com a célula atual
            ElementoTabuleiro elemento = tabuleiro.interagir(jogador.getX(), jogador.getY());
            
            if (elemento != null) {  // Se encontrou algo novo
                int pontos = elemento.interagir();
                jogador.adicionarPontos(pontos);
                
                System.out.println("✅ Você encontrou: " + elemento.simbolo());
                System.out.println("📊 Pontos ganhos: " + pontos);
            }
        }

        // Fim do jogo
        tabuleiro.imprimir(jogador);
        System.out.println("\n🎮 Fim do jogo!");
        System.out.println("🏆 Pontuação final: " + jogador.getPontos());
        System.out.println("💰 Tesouros encontrados: " + (3 - tabuleiro.getTesourosRestantes()));
        
        scanner.close();  // Fecha o scanner
    }
}