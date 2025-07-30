public class Jogador {
    private int x, y;               // Posição atual (0 a 5)
    private int pontos;             // Pontuação total
    private int movimentosRestantes; // Movimentos restantes (começa com 10)

    public Jogador() {
        this.x = 0;      // Começa em (0, 0)
        this.y = 0;
        this.pontos = 0;
        this.movimentosRestantes = 10;
    }

    // Move o jogador (WASD)
    public void mover(char direcao) {
        if (movimentosRestantes <= 0) return;  // Se acabaram os movimentos, não faz nada

        switch (direcao) {
            case 'W': if (y > 0) y--; break;   // Cima
            case 'S': if (y < 5) y++; break;   // Baixo
            case 'A': if (x > 0) x--; break;   // Esquerda
            case 'D': if (x < 5) x++; break;   // Direita
        }
        movimentosRestantes--;  // Gasta um movimento
    }

    // Getters (para outras classes acessarem os dados)
    public int getX() { return x; }
    public int getY() { return y; }
    public int getPontos() { return pontos; }
    public int getMovimentosRestantes() { return movimentosRestantes; }

    // Adiciona pontos ao total
    public void adicionarPontos(int pontos) {
        this.pontos += pontos;
    }
}