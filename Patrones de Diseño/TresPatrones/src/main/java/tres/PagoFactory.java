/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tres;

public class PagoFactory {

    public static MetodoPago crearPago(String tipo) {

        if (tipo.equalsIgnoreCase("tarjeta")) {
            return new PagoTarjeta();
        }

        if (tipo.equalsIgnoreCase("paypal")) {
            return new PagoPayPal();
        }

        if (tipo.equalsIgnoreCase("crypto")) {
            return new CryptoAdapter();
        }

        return null;
    }
}
