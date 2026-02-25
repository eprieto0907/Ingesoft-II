// Clase que solo representa un usuario
class Usuario {
    private String nombre;
    private String email;

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
}

// Clase que solo maneja la persistencia
class UsuarioRepository {
    public void guardar(Usuario usuario) {
        // Lógica para guardar en base de datos
        System.out.println("Guardando usuario en BD: " + usuario.getNombre());
    }
}

// Clase que solo maneja emails
class EmailService {
    public void enviarEmail(Usuario usuario, String mensaje) {
        // Lógica para enviar email
        System.out.println("Enviando email a: " + usuario.getEmail());
    }
}