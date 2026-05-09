package patrones;

public class Main {

    public static void main(String[] args) {

        Usuario usuario1 = new Usuario("Esteban");

        Sesion sesion1 = Sesion.getInstancia();

        sesion1.login(usuario1);

        Sesion sesion2 = Sesion.getInstancia();

        sesion2.mostrarSesion();

        // Verificar Singleton
        System.out.println(sesion1 == sesion2);
    }
}