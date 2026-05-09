package buildfacade;

public class Computadora {

    String procesador;
    int ram;
    String tarjetaGrafica;

    public void mostrarInfo() {

        System.out.println("Procesador: " + procesador);
        System.out.println("RAM: " + ram + " GB");
        System.out.println("Tarjeta gráfica: " + tarjetaGrafica);
    }
}