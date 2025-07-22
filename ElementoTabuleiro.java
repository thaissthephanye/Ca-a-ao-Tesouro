// Classe abstrata base
public abstract class ElementoTabuleiro {
    public abstract int interagir();
    public abstract String simbolo(); // Único método para símbolo
}

// Subclasses concretas
public class Tesouro extends ElementoTabuleiro {
    public int interagir() { return 3; }
    public String simbolo() { return "💰"; }
}

public class Armadilha extends ElementoTabuleiro {
    public int interagir() { return -2; }
    public String simbolo() { return "💣"; }
}

public class Vazio extends ElementoTabuleiro {
    public int interagir() { return 0; }
    public String simbolo() { return "⬜"; }
}
