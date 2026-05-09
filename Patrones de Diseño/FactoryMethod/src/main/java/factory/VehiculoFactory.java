package factory;

public class VehiculoFactory {

    public static Vehiculo crearVehiculo(String tipo) {

        if (tipo.equalsIgnoreCase("carro")) {
            return new Carro();
        }

        if (tipo.equalsIgnoreCase("moto")) {
            return new Moto();
        }

        return null;
    }
}