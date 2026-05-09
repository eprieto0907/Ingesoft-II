package buildfacade;

public interface ComputadoraBuilder {

    void buildProcesador();

    void buildRAM();

    void buildTarjetaGrafica();

    Computadora getComputadora();
}