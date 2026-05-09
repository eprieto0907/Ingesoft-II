package tres;

public class PagoPayPal implements MetodoPago {

    @Override
    public void pagar(int cantidad) {
        System.out.println(
                "Pagando $" + cantidad + " con PayPal");
    }
}