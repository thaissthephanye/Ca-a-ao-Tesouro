public class Tabuleiro {
    private ElementoTabuleiro[][] grade;  // Matriz 6x6 de elementos
    private boolean[][] revelado;         // Matriz 6x6 de células reveladas
    private int tesourosRestantes;        // Tesouros não encontrados
    
    public Tabuleiro() {
        grade = new ElementoTabuleiro[6][6];  // Cria matriz 6x6
        revelado = new boolean[6][6];         // Cria matriz de revelação
        tesourosRestantes = 3;                // 3 tesouros no total
        inicializarTabuleiro();               // Preenche o tabuleiro
    }
    
    private void inicializarTabuleiro() {
        // Preenche tudo com células vazias inicialmente
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                grade[i][j] = new Vazio();    // Célula vazia
                revelado[i][j] = false;       // Não revelada
            }
        }
        
        // Coloca os tesouros em posições aleatórias
        colocarElementos(new Tesouro(), 3);
        // Coloca as armadilhas em posições aleatórias
        colocarElementos(new Armadilha(), 3);
    }
    
    private void colocarElementos(ElementoTabuleiro elemento, int quantidade) {
        int colocados = 0;
        while (colocados < quantidade) {
            int x = (int)(Math.random() * 6);  // Posição X aleatória (0-5)
            int y = (int)(Math.random() * 6);  // Posição Y aleatória
            
            // Verifica se não é a posição inicial (0,0) e se está vazia
            if ((x != 0 || y != 0) && grade[x][y] instanceof Vazio) {
                grade[x][y] = elemento;  // Coloca o elemento
                colocados++;             // Incrementa contador
            }
        }
    }
    
    public ElementoTabuleiro interagir(int x, int y) {
        if (!revelado[x][y]) {               // Se célula não foi revelada
            revelado[x][y] = true;           // Marca como revelada
            ElementoTabuleiro elemento = grade[x][y]; // Pega o elemento
            
            if (elemento instanceof Tesouro) {
                tesourosRestantes--;         // Decrementa tesouros restantes
            }
            return elemento;                 // Retorna o elemento
        }
        return null;                         // Já foi revelado
    }
    
    public void imprimir(Jogador jogador) {
        System.out.println("\nTabuleiro:");
        for (int y = 0; y < 6; y++) {       // Linhas
            for (int x = 0; x < 6; x++) {   // Colunas
                if (x == jogador.getX() && y == jogador.getY()) {
                    System.out.print("😀 ");  // Mostra jogador
                } else if (revelado[x][y]) {
                    System.out.print(grade[x][y].simbolo() + " "); // Elemento revelado
                } else {
                    System.out.print("🟦 ");  // Célula não revelada
                }
            }
            System.out.println();           // Nova linha
        }
    }
    
    public int getTesourosRestantes() {
        return tesourosRestantes;            // Retorna tesouros faltantes
    }
}