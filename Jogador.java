public class Jogador {
    private int x;                   // Posição X no tabuleiro
    private int y;                   // Posição Y no tabuleiro
    private int pontos;              // Pontuação acumulada
    private int movimentosRestantes; // Movimentos que faltam
    
    public Jogador() {               // Construtor
        this.x = 0;                  // Começa na posição (0,0)
        this.y = 0;
        this.pontos = 0;             // Pontos inicial zero
        this.movimentosRestantes = 10; // 10 movimentos totais
    }
    
    public void mover(char direcao) {
        if (movimentosRestantes <= 0) return; // Se acabaram movimentos, não faz nada
        
        switch (direcao) {           // Verifica direção
            case 'W': if (y > 0) y--; break;   // Cima (não passa do topo)
            case 'S': if (y < 5) y++; break;   // Baixo (não passa da base)
            case 'A': if (x > 0) x--; break;   // Esquerda
            case 'D': if (x < 5) x++; break;   // Direita
        }
        movimentosRestantes--;      // Gasta um movimento
    }
    
    // Métodos de acesso (getters)
    public int getX() { return x; }
    public int getY() { return y; }
    public int getPontos() { return pontos; }
    public int getMovimentosRestantes() { return movimentosRestantes; }
    
    public void adicionarPontos(int pontos) {
        this.pontos += pontos;      // Adiciona pontos ao total
    }
}
