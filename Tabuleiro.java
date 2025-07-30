public class Tabuleiro {
    private ElementoTabuleiro[][] grade;  // matriz 6x6 para guardar todos os elementos
    private boolean[][] revelado;         // matriz 6x6 para marcar por onde já passou
    private int tesourosRestantes;        // tesouros não encontrados
    
    public Tabuleiro() {
        grade = new ElementoTabuleiro[6][6];  // cria uma matriz
        revelado = new boolean[6][6];         // Cria matriz de revelação
        tesourosRestantes = 3;                // 3 tesouros no total
        inicializarTabuleiro();               // chamada do metodo pra iniciar
    }
    
    private void inicializarTabuleiro() {
        // tudo vazio
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                grade[i][j] = new Vazio();    // Célula vazia
                revelado[i][j] = false;       // Não revelada
            }
        }
        
        // coloca os tesouros
        colocarElementos(new Tesouro(), 3);
        // coloca as armadilhas
        colocarElementos(new Armadilha(), 3);
    }
    
    // pra colocar os tesouros e armadilhas de forma aleatoria em um 6x6
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

    //qunado o jogador chega na posição
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
    System.out.println("\nTabuleiro (Posição Atual: [" + jogador.getX() + "," + jogador.getY() + "])");
    for (int y = 0; y < 6; y++) {
        for (int x = 0; x < 6; x++) {
            if (x == jogador.getX() && y == jogador.getY()) {
                System.out.print("😀 ");
            } else if (revelado[x][y]) {
                System.out.print(grade[x][y].simbolo() + " ");
            } else {
                System.out.print("⬜ ");
            }
        }
        System.out.println();
    }
}
    
    public int getTesourosRestantes() {
        return tesourosRestantes;            // Retorna tesouros faltantes
    }
}