public class Jogador {
    private int x;                   // colunas
    private int y;                   // linhas
    private int pontos;              // Pontuação acumulada
    private int movimentosRestantes; // Movimentos que faltam
    
    public Jogador() {               
        this.x = 0;                  
        this.y = 0;
        this.pontos = 0;             // tudo zerado pra inicio do jogo
        this.movimentosRestantes = 10; // os 10 movimentos
    }

    //usando x e y estilo plano cartesiano
    
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
    
    // getters
    public int getX() { 
        return x; 
    }
    public int getY() { 
        return y; 
    }
    public int getPontos() { 
        return pontos; 
    }
    public int getMovimentosRestantes() { 
        return movimentosRestantes; 
    }
    
    public void adicionarPontos(int pontos) {
        this.pontos += pontos;      // Adiciona pontos ao total
    }
}