import java.util.Scanner;

public class Jogo {
    private Jogador jogador;        // Objeto jogador
    private Tabuleiro tabuleiro;    // Objeto tabuleiro
    private Scanner scanner;       // Para entrada do usuário
    
    public Jogo() {
        jogador = new Jogador();    // Cria novo jogador
        tabuleiro = new Tabuleiro(); // Cria novo tabuleiro
        scanner = new Scanner(System.in); // Prepara scanner
    }
    
    public void iniciar() {
        // Mensagem inicial
        System.out.println("Bem-vindo ao Caça ao Tesouro!");
        System.out.println("Use W (cima), S (baixo), A (esquerda), D (direita) para se mover.");
        
        // Loop principal do jogo
        while (jogador.getMovimentosRestantes() > 0 && tabuleiro.getTesourosRestantes() > 0) {
            tabuleiro.imprimir(jogador); // Mostra tabuleiro
            
            // Mostra status
            System.out.println("\nPontos: " + jogador.getPontos());
            System.out.println("Movimentos restantes: " + jogador.getMovimentosRestantes());
            System.out.println("Tesouros restantes: " + tabuleiro.getTesourosRestantes());
            System.out.print("Próximo movimento (W/A/S/D): ");
            
            // Lê movimento (apenas primeiro caractere, convertido para maiúsculo)
            char movimento = scanner.next().toUpperCase().charAt(0);
            
            // Valida movimento
            if ("WASD".indexOf(movimento) == -1) {
                System.out.println("Movimento inválido! Use W, A, S ou D.");
                continue; // Volta ao início do loop
            }
            
            // Executa movimento
            jogador.mover(movimento);
            
            // Interage com a célula
            ElementoTabuleiro elemento = tabuleiro.interagir(jogador.getX(), jogador.getY());
            
            if (elemento != null) { // Se havia elemento não revelado
                int pontos = elemento.interagir(); // Pega pontos
                jogador.adicionarPontos(pontos);  // Adiciona ao total
                
                // Mostra resultado
                System.out.println("Você encontrou: " + elemento.simbolo());
                System.out.println("Pontos ganhos: " + pontos);
            }
        }
        
        // Fim do jogo
        tabuleiro.imprimir(jogador); // Mostra tabuleiro final
        System.out.println("\nFim do jogo!");
        System.out.println("Pontuação final: " + jogador.getPontos());
        System.out.println("Tesouros encontrados: " + (3 - tabuleiro.getTesourosRestantes()));
        
        scanner.close(); // Fecha scanner
    }
}