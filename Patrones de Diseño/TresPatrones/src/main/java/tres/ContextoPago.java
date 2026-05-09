package tres;

public class ContextoPago {

    private MetodoPago metodoPago;

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void ejecutarPago(int cantidad) {

        metodoPago.pagar(cantidad);
    }
}