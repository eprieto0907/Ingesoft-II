package factory;

public class Carro implements Vehiculo {

    @Override
    public void mostrarTipo() {
        System.out.println("Soy un carro");
    }
}