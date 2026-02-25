// Diseño correcto: Separamos las aves según su capacidad
abstract class Ave {
    public abstract String mover();
}

class AveVoladora extends Ave {
    @Override
    public String mover() {
        return "Volando por el cielo";
    }
}

class AveNoVoladora extends Ave {
    @Override
    public String mover() {
        return "Caminando en el suelo";
    }
}

class Pinguino extends AveNoVoladora {
    @Override
    public String mover() {
        return "Nadando en el agua";
    }
}

// Uso correcto
public class Main {
    public static void moverAve(Ave ave) {
        System.out.println(ave.mover());
    }

    public static void main(String[] args) {
        moverAve(new AveVoladora()); // Funciona correctamente
        moverAve(new Pinguino()); // Funciona correctamente
    }
}