// Interfaces pequeñas y específicas
interface Impresora {
    void imprimir(Documento documento);
}

interface Escáner {
    void escanear(Documento documento);
}

interface Fax {
    void enviarFax(Documento documento);
}

// Las clases implementan solo lo que necesitan
class ImpresoraSimple implements Impresora {
    @Override
    public void imprimir(Documento documento) {
        System.out.println("Imprimiendo documento");
    }
}

class Fotocopiadora implements Impresora, Escáner {
    @Override
    public void imprimir(Documento documento) {
        System.out.println("Imprimiendo");
    }

    @Override
    public void escanear(Documento documento) {
        System.out.println("Escaneando");
    }
}

class MultiFuncional implements Impresora, Escáner, Fax {
    @Override
    public void imprimir(Documento documento) {
        System.out.println("Imprimiendo");
    }

    @Override
    public void escanear(Documento documento) {
        System.out.println("Escaneando");
    }

    @Override
    public void enviarFax(Documento documento) {
        System.out.println("Enviando fax");
    }
}