// Interfaz base
interface Figura {
    double calcularArea();
}

class Circulo implements Figura {
    private double radio;

    public Circulo(double radio) {
        this.radio = radio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * radio * radio;
    }
}

class Rectangulo implements Figura {
    private double ancho;
    private double alto;

    public Rectangulo(double ancho, double alto) {
        this.ancho = ancho;
        this.alto = alto;
    }

    @Override
    public double calcularArea() {
        return ancho * alto;
    }
}

class Triangulo implements Figura {
    private double base;
    private double altura;

    public Triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return (base * altura) / 2;
    }
}

// No necesitamos modificar esta clase para agregar nuevas figuras
class CalculadoraArea {
    public double calcularAreaTotal(List<Figura> figuras) {
        double total = 0;
        for (Figura figura : figuras) {
            total += figura.calcularArea();
        }
        return total;
    }
}