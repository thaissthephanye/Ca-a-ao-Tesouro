import java.util.Set;

// Controla a posição e pontuação do jogador
public class Jogador {
    private int x, y;               // Posição atual (0-5)
    private int pontos;             // Pontos acumulados
    private int movimentosRestantes; // Movimentos que faltam

    public Jogador() {
        this.x = 0;      // Começa na posição (0,0)
        this.y = 0;
        this.pontos = 0;
        this.movimentosRestantes = 10;  // Total de movimentos
    }

    // Tenta mover o jogador (WASD) e verifica se a posição é válida
    public boolean mover(char direcao, Set<String> posicoesVisitadas) {
        if (movimentosRestantes <= 0) return false;  // Se acabaram os movimentos

        int novoX = x;
        int novoY = y;

        // Calcula nova posição conforme a direção
        switch (direcao) {
            case 'W': if (y > 0) novoY--; break;   // Cima
            case 'S': if (y < 5) novoY++; break;   // Baixo
            case 'A': if (x > 0) novoX--; break;   // Esquerda
            case 'D': if (x < 5) novoX++; break;   // Direita
        }

        String novaPosicao = novoX + "," + novoY;
        
        // Verifica se já visitou essa posição
        if (posicoesVisitadas.contains(novaPosicao)) {
            return false;  // Não pode mover para lá
        }

        // Atualiza posição e diminui movimentos
        x = novoX;
        y = novoY;
        movimentosRestantes--;
        return true;
    }

    // Métodos para acessar informações (getters)
    public int getX() { return x; }
    public int getY() { return y; }
    public int getPontos() { return pontos; }
    public int getMovimentosRestantes() { return movimentosRestantes; }

    // Adiciona pontos ao total
    public void adicionarPontos(int pontos) {
        this.pontos += pontos;
    }
}