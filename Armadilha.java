// Armadilha: tira -2 pontos quando pisada
public class Armadilha extends ElementoTabuleiro {
    @Override
    public int interagir() { 
        return -2;  // Armadilha tira 2 pontos
    }
    
    @Override
    public String simbolo() { 
        return "💣";  // Emoji da armadilha
    }
}