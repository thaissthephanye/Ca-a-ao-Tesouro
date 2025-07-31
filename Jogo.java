import java.util.Scanner;

public class Jogo {
    private Jogador jogador;
    private Tabuleiro tabuleiro;
    private Scanner scanner;

    public Jogo() {
        jogador = new Jogador();
        tabuleiro = new Tabuleiro();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("🏴‍☠️ Bem-vindo ao Caça ao Tesouro! 🏴‍☠️");
        System.out.println("Use W (cima), S (baixo), A (esquerda), D (direita) para se mover.");
        System.out.println("Encontre os tesouros (💰) e evite as armadilhas (💣)!");

        while (jogador.getMovimentosRestantes() > 0 && tabuleiro.getTesourosRestantes() > 0) {
            tabuleiro.imprimir(jogador);
            
            System.out.println("\nPontos: " + jogador.getPontos());
            System.out.println("Movimentos restantes: " + jogador.getMovimentosRestantes());
            System.out.println("Tesouros restantes: " + tabuleiro.getTesourosRestantes());
            System.out.print("Próximo movimento (W/A/S/D): ");

            char movimento = scanner.next().toUpperCase().charAt(0);
            
            if ("WASD".indexOf(movimento) == -1) {
                System.out.println("❌ Movimento inválido! Use W, A, S ou D.");
                continue;
            }
            
            boolean moveu = jogador.mover(movimento, tabuleiro.getPosicoesVisitadas());
            
            if (!moveu) {
                System.out.println("🚫 Você já visitou esta casa! Escolha outra direção.");
                continue;
            }

            ElementoTabuleiro elemento = tabuleiro.interagir(jogador.getX(), jogador.getY());
            
            if (elemento != null) {
                int pontos = elemento.interagir();
                jogador.adicionarPontos(pontos);
                
                System.out.println("✅ Você encontrou: " + elemento.simbolo());
                System.out.println("📊 Pontos ganhos: " + pontos);
            }
        }

        tabuleiro.imprimir(jogador);
        System.out.println("\n🎮 Fim do jogo!");
        System.out.println("🏆 Pontuação final: " + jogador.getPontos());
        System.out.println("💰 Tesouros encontrados: " + (3 - tabuleiro.getTesourosRestantes()));
        
    }
}