package factory;

public class MainFactory {

    public static void main(String[] args) {

        Vehiculo vehiculo1 =
                VehiculoFactory.crearVehiculo("carro");

        Vehiculo vehiculo2 =
                VehiculoFactory.crearVehiculo("moto");

        vehiculo1.mostrarTipo();
        vehiculo2.mostrarTipo();
    }
}