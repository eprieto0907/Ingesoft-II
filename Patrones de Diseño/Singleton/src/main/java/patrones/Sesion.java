package patrones;

public class Sesion {

    // Instancia única
    private static Sesion instancia;

    // Usuario actual
    private Usuario usuario;

    // Constructor privado
    private Sesion() {
        System.out.println("Sistema de sesión iniciado");
    }

    // Obtener la única instancia
    public static Sesion getInstancia() {

        if (instancia == null) {
            instancia = new Sesion();
        }

        return instancia;
    }

    // Login
    public void login(Usuario usuario) {
        this.usuario = usuario;
        System.out.println("Usuario logueado: " + usuario.nombre);
    }

    // Mostrar sesión
    public void mostrarSesion() {

        if (usuario != null) {
            System.out.println("Sesión activa: " + usuario.nombre);
        } else {
            System.out.println("No hay sesión activa");
        }
    }
}