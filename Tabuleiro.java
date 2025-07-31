import java.util.HashSet;
import java.util.Random;
import java.util.Set;

//tabuleiro do jogo
public class Tabuleiro {
    private ElementoTabuleiro[][] grade;  //matriz 6x6
    private Set<String> posicoesVisitadas; //"x,y" das visitadas
    private Set<String> posicoesTesouros;  //guarda onde estão os tesouros
    private int tesourosRestantes;         //quantos faltam achar

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

    //coloca elementos aleatoriamente no tabuleiro
    private void colocarElementos(ElementoTabuleiro elemento, int quantidade) {
        Random random = new Random();
        int colocados = 0;

        while (colocados < quantidade) {
            int x = random.nextInt(6);  
            int y = random.nextInt(6);
            String chave = x + "," + y;

            //não coloca em (0,0) e só onde estiver vazio
            if ((x != 0 || y != 0) && grade[x][y] instanceof Vazio) {
                grade[x][y] = elemento;
                if (elemento instanceof Tesouro) {
                    posicoesTesouros.add(chave);  //guarda posição do tesouro
                }
                colocados++;
            }
        }
    }

    //quando o jogador pisa em uma casa
    public ElementoTabuleiro interagir(int x, int y) {
        String chave = x + "," + y;

        //se não foi visitada ainda
        if (!posicoesVisitadas.contains(chave)) {
            posicoesVisitadas.add(chave);  //marca como visitada

            //se era tesouro diminui o contador de tesouros
            if (posicoesTesouros.contains(chave)) {
                tesourosRestantes--;
                posicoesTesouros.remove(chave);
            }

            return grade[x][y];  // retorna o que tem na casa (vazio armadilha ou tesouro)
        }
        return null;  // já foi visitada
    }

    //mostra o tabuleiro no console
    public void imprimir(Jogador jogador) {
        System.out.println("\nTabuleiro (Posição Atual: [" + jogador.getX() + "," + jogador.getY() + "])");
        
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 6; x++) {
                String chave = x + "," + y;
                
                if (x == jogador.getX() && y == jogador.getY()) {
                    System.out.print("🤖 ");  // jogador
                } else if (posicoesVisitadas.contains(chave)) {
                    System.out.print(grade[x][y].simbolo() + " ");  //mostra elemento revelado
                } else {
                    System.out.print("🏴 ");  //não revelada
                }
            }
            System.out.println();
        }
    }

    //as posições já visitadas
    public Set<String> getPosicoesVisitadas() {
        return posicoesVisitadas;
    }

    // quantos tesouros faltam
    public int getTesourosRestantes() {
        return tesourosRestantes;
    }
}