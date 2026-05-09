package buildfacade;

public class Main {

    public static void main(String[] args) {

        TiendaFacade tienda = new TiendaFacade();

        Computadora pc = tienda.comprarPCGamer();

        pc.mostrarInfo();
    }
}