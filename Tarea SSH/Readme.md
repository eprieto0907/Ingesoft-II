# Laboratorio SHH-Ingesoft II

## Esteban Prieto Lugo

### Paso a Paso del Laboratorio

1.  Nos ubicamos en la carpeta donde están los archivos y creamos un servidor.

![](Imagen1.png)

2.  Abrimos una segunda pestaña de Powershell y nos dirigimos de nuevo a la misma carpeta donde están los archivos, luego hacemos el registro del usuario.

![](Imagen2.png)

3.  Al hacer el login nos devuelve nuestro token.

![](Imagen3.png)

4.  Guardamos el token y verificamos que se halla guardado con éxito.

![](Imagen4.png)

5.  Intentamos hacer un GET sin token y nos debe salir error 401 al intentarlo.

![](Imagen5.png)

6.  Ahora realizamos el GET con el token.

![](Imagen6.png)

7.  Creamos una nueva tarea.

![](Imagen7.png)

8.  Y ahora creamos otra tarea para luego hacer PUT y DELETE.

![](Imagen8.png)

9.  Actualizamos con PUT.

![](Imagen9.png)

10. Ahora lo borramos con DELETE

![](Imagen10.png)

11. Ahora en Windows vamos a **"Configuración\>Sistema\>Características Opcionales\>Ver características\>"**. Buscamos en características disponibles por Servidor OpenSSH.

![](Imagen11.png)

12. Abrimos otro Powershell en Admin para iniciar SSH

![](Imagen12.png)

13. Abrimos Firewall SSH

![](Imagen13.png)

14. Verificamos que esté corriendo

![](Imagen14.png)

15. Creamos un usuario local y le damos permisos

![](Imagen15.png)

16. Desde una segunda PC nos conectamos a la PC1 con el usuario y la IP de esta y usamos la contraseña

![](Imagen16.png)

![](Imagen17.png)

17. Desde la PC1 copiamos el token y lo guardamos en la PC2, y usamos el GET con token.

![](Imagen18.png)
