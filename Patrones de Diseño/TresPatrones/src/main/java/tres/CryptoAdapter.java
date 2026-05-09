package tres;

public class CryptoAdapter implements MetodoPago {

    private CryptoAPI cryptoAPI;

    public CryptoAdapter() {
        cryptoAPI = new CryptoAPI();
    }

    @Override
    public void pagar(int cantidad) {

        cryptoAPI.realizarPagoCrypto(cantidad);
    }
}