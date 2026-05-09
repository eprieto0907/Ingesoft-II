package tres;

public class Main {

    public static void main(String[] args) {

        ContextoPago contexto = new ContextoPago();

        // Factory crea el método
        MetodoPago metodo1 =
                PagoFactory.crearPago("tarjeta");

        contexto.setMetodoPago(metodo1);

        contexto.ejecutarPago(100);

        MetodoPago metodo2 =
                PagoFactory.crearPago("paypal");

        contexto.setMetodoPago(metodo2);

        contexto.ejecutarPago(200);

        MetodoPago metodo3 =
                PagoFactory.crearPago("crypto");

        contexto.setMetodoPago(metodo3);

        contexto.ejecutarPago(300);
    }
}