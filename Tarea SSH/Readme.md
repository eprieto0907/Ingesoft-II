# Laboratorio SHH-Ingesoft II

## Esteban Prieto Lugo

### Paso a Paso del Laboratorio

1.  Nos ubicamos en la carpeta donde están los archivos y creamos un servidor.

![](media/Imagen1.png){width="6.1375in" height="1.3958333333333333in"}

2.  Abrimos una segunda pestaña de Powershell y nos dirigimos de nuevo a la misma carpeta donde están los archivos, luego hacemos el registro del usuario.

![](media/Imagen2.png){width="6.1375in" height="0.9458333333333333in"}

3.  Al hacer el login nos devuelve nuestro token.

![](media/Imagen3.png){width="6.1375in" height="0.9270833333333334in"}

4.  Guardamos el token y verificamos que se halla guardado con éxito.

![](media/Imagen4.png){width="6.1375in" height="0.7006944444444444in"}

5.  Intentamos hacer un GET sin token y nos debe salir error 401 al intentarlo.

![](media/Imagen5.png){width="6.1375in" height="0.9166666666666666in"}

6.  Ahora realizamos el GET con el token.

![](media/Imagen6.png){width="6.1375in" height="0.7326388888888888in"}

7.  Creamos una nueva tarea.

![](media/Imagen7.png){width="6.1375in" height="1.3368055555555556in"}

8.  Y ahora creamos otra tarea para luego hacer PUT y DELETE.

![](media/Imagen8.png){width="6.1375in" height="0.5013888888888889in"}

9.  Actualizamos con PUT.

![](media/Imagen9.png){width="6.1375in" height="1.3229166666666667in"}

10. Ahora lo borramos con DELETE

![](media/Imagen10.png){width="6.1375in" height="1.5847222222222221in"}

11. Ahora en Windows vamos a **"Configuración\>Sistema\>Características Opcionales\>Ver características\>"**. Buscamos en características disponibles por Servidor OpenSSH.

![](media/Imagen11.png){width="6.1375in" height="8.07986111111111in"}

12. Abrimos otro Powershell en Admin para iniciar SSH

![](media/Imagen12.png){width="6.1375in" height="0.3388888888888889in"}

13. Abrimos Firewall SSH

![](media/Imagen13.png){width="6.1375in" height="3.7645833333333334in"}

14. Verificamos que esté corriendo

![](media/Imagen14.png){width="4.25036854768154in" height="0.9667508748906387in"}

15. Creamos un usuario local y le damos permisos

![](media/Imagen15.png){width="6.1375in" height="0.5715277777777777in"}

16. Desde una segunda PC nos conectamos a la PC1 con el usuario y la IP de esta y usamos la contraseña

![](media/Imagen16.png){width="6.452083333333333in" height="0.9497364391951006in"}

![](media/Imagen17.png){width="6.428985126859143in" height="0.6666666666666666in"}

17. Desde la PC1 copiamos el token y lo guardamos en la PC2, y usamos el GET con token.

![](media/Imagen18.png){width="6.504166666666666in" height="1.3448895450568679in"}
