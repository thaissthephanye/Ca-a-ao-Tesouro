import java.util.HashSet;
import java.util.Random;
import java.util.Set;

// Representa o tabuleiro do jogo
public class Tabuleiro {
    private ElementoTabuleiro[][] grade;  // Matriz 6x6
    private Set<String> posicoesVisitadas; // Guarda "x,y" das visitadas
    private Set<String> posicoesTesouros;  // Guarda onde estão os tesouros
    private int tesourosRestantes;         // Quantos faltam achar

    public Tabuleiro() {
        grade = new ElementoTabuleiro[6][6];
        posicoesVisitadas = new HashSet<>();
        posicoesTesouros = new HashSet<>();
        tesourosRestantes = 3;
        inicializarTabuleiro();
    }

    // Preenche o tabuleiro com tesouros e armadilhas
    private void inicializarTabuleiro() {
        // Primeiro coloca tudo como Vazio
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                grade[i][j] = new Vazio();
            }
        }

        // Coloca os tesouros
        colocarElementos(new Tesouro(), 3);
        // Coloca as armadilhas
        colocarElementos(new Armadilha(), 3);
    }

    // Coloca elementos aleatoriamente no tabuleiro
    private void colocarElementos(ElementoTabuleiro elemento, int quantidade) {
        Random random = new Random();
        int colocados = 0;

        while (colocados < quantidade) {
            int x = random.nextInt(6);  // Posição aleatória
            int y = random.nextInt(6);
            String chave = x + "," + y;

            // Não coloca em (0,0) e só onde estiver vazio
            if ((x != 0 || y != 0) && grade[x][y] instanceof Vazio) {
                grade[x][y] = elemento;
                if (elemento instanceof Tesouro) {
                    posicoesTesouros.add(chave);  // Guarda posição do tesouro
                }
                colocados++;
            }
        }
    }

    // Quando o jogador pisa em uma célula
    public ElementoTabuleiro interagir(int x, int y) {
        String chave = x + "," + y;

        // Se não foi visitada ainda
        if (!posicoesVisitadas.contains(chave)) {
            posicoesVisitadas.add(chave);  // Marca como visitada

            // Se era um tesouro, diminui o contador
            if (posicoesTesouros.contains(chave)) {
                tesourosRestantes--;
                posicoesTesouros.remove(chave);
            }

            return grade[x][y];  // Retorna o que tem na célula
        }
        return null;  // Já foi visitada
    }

    // Mostra o tabuleiro no console
    public void imprimir(Jogador jogador) {
        System.out.println("\nTabuleiro (Posição Atual: [" + jogador.getX() + "," + jogador.getY() + "])");
        
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 6; x++) {
                String chave = x + "," + y;
                
                if (x == jogador.getX() && y == jogador.getY()) {
                    System.out.print("🤖 ");  // Mostra o jogador
                } else if (posicoesVisitadas.contains(chave)) {
                    System.out.print(grade[x][y].simbolo() + " ");  // Mostra elemento revelado
                } else {
                    System.out.print("🏴 ");  // Célula não revelada
                }
            }
            System.out.println();  // Pula linha
        }
    }

    // Retorna as posições já visitadas
    public Set<String> getPosicoesVisitadas() {
        return posicoesVisitadas;
    }

    // Retorna quantos tesouros faltam
    public int getTesourosRestantes() {
        return tesourosRestantes;
    }
}