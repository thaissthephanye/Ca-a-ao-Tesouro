import java.util.Set;
import java.util.HashSet;
import java.util.Random;

public class Tabuleiro {
    private ElementoTabuleiro[][] grade;  // Matriz 6x6 de elementos
    private Set<String> posicoesVisitadas; // Guarda "x,y" das posições visitadas
    private Set<String> posicoesTesouros;  // Guarda "x,y" dos tesouros
    private int tesourosRestantes;         // Quantos tesouros faltam achar

    public Tabuleiro() {
        grade = new ElementoTabuleiro[6][6];
        posicoesVisitadas = new HashSet<>();
        posicoesTesouros = new HashSet<>();
        tesourosRestantes = 3;
        inicializarTabuleiro();
    }

    // Preenche o tabuleiro com tesouros, armadilhas e vazios
    private void inicializarTabuleiro() {
        // Primeiro, tudo é Vazio
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                grade[i][j] = new Vazio();
            }
        }

        // Coloca 3 tesouros aleatórios
        colocarElementos(new Tesouro(), 3);
        // Coloca 3 armadilhas aleatórias
        colocarElementos(new Armadilha(), 3);
    }

    // Coloca elementos (tesouros/armadilhas) aleatoriamente
    private void colocarElementos(ElementoTabuleiro elemento, int quantidade) {
        Random random = new Random();
        int colocados = 0;

        while (colocados < quantidade) {
            int x = random.nextInt(6);  // Número aleatório de 0 a 5
            int y = random.nextInt(6);
            String chave = x + "," + y;  // Ex: "2,3"

            // Não coloca em (0,0) e só onde estiver Vazio
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

        if (!posicoesVisitadas.contains(chave)) {  // Se NÃO foi visitada
            posicoesVisitadas.add(chave);          // Marca como visitada

            if (posicoesTesouros.contains(chave)) {  // Se era um tesouro
                tesourosRestantes--;                // Diminui contador
                posicoesTesouros.remove(chave);     // Remove do Set
            }

            return grade[x][y];  // Retorna o elemento (💰, 💣, ou ⬜)
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
                    System.out.print("😀 ");  // Mostra o jogador
                } else if (posicoesVisitadas.contains(chave)) {
                    System.out.print(grade[x][y].simbolo() + " ");  // Mostra elemento revelado
                } else {
                    System.out.print("🟦 ");  // Célula não revelada
                }
            }
            System.out.println();  // Pula linha
        }
    }

    // Retorna quantos tesouros faltam
    public int getTesourosRestantes() {
        return tesourosRestantes;
    }
}