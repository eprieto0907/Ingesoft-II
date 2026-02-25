// Abstracción (interfaz)
interface Almacenador {
    void guardar(String datos);
}

// Implementaciones de bajo nivel
class BaseDeDatos implements Almacenador {
    @Override
    public void guardar(String datos) {
        System.out.println("Guardando en MySQL: " + datos);
    }
}

class Archivo implements Almacenador {
    @Override
    public void guardar(String datos) {
        System.out.println("Guardando en archivo: " + datos);
    }
}

class CloudStorage implements Almacenador {
    @Override
    public void guardar(String datos) {
        System.out.println("Guardando en AWS S3: " + datos);
    }
}

// Módulo de alto nivel depende de abstracción
class Servicio {
    private Almacenador almacenador;

    // Inyección de dependencia
    public Servicio(Almacenador almacenador) {
        this.almacenador = almacenador;
    }

    public void procesar(String datos) {
        // Procesa datos
        System.out.println("Procesando datos...");
        almacenador.guardar(datos);
    }
}

// Uso
public class Main {
    public static void main(String[] args) {
        Servicio servicio1 = new Servicio(new BaseDeDatos());
        Servicio servicio2 = new Servicio(new Archivo());
        Servicio servicio3 = new Servicio(new CloudStorage());

        servicio1.procesar("Información importante");
    }
}