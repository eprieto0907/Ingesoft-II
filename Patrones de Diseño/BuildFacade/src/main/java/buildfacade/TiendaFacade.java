package buildfacade;

public class TiendaFacade {

    public Computadora comprarPCGamer() {

        PCBuilder builder = new PCBuilder();

        builder.buildProcesador();
        builder.buildRAM();
        builder.buildTarjetaGrafica();

        return builder.getComputadora();
    }
}