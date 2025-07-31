// Espaço vazio: não altera pontos
public class Vazio extends ElementoTabuleiro {
    @Override
    public int interagir() { 
        return 0;  // Não muda a pontuação
    }
    
    @Override
    public String simbolo() { 
        return "🔲";  // Quadrado branco
    }
}