// Tesouro: vale +3 pontos quando encontrado
public class Tesouro extends ElementoTabuleiro {
    @Override
    public int interagir() { 
        return 3;  // Tesouro dá 3 pontos
    }
    
    @Override
    public String simbolo() { 
        return "💰";  // Emoji do tesouro
    }
}