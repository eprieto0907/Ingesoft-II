package tres;

public class PagoTarjeta implements MetodoPago {

    @Override
    public void pagar(int cantidad) {
        System.out.println(
                "Pagando $" + cantidad + " con tarjeta");
    }
}