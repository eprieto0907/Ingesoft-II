package buildfacade;

public class PCBuilder implements ComputadoraBuilder {

    private Computadora computadora;

    public PCBuilder() {
        computadora = new Computadora();
    }

    @Override
    public void buildProcesador() {
        computadora.procesador = "Intel i9";
    }

    @Override
    public void buildRAM() {
        computadora.ram = 32;
    }

    @Override
    public void buildTarjetaGrafica() {
        computadora.tarjetaGrafica = "RTX 4080";
    }

    @Override
    public Computadora getComputadora() {
        return computadora;
    }
}