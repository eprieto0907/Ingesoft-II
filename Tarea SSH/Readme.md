# Laboratorio SHH-Ingesoft II

## Esteban Prieto Lugo

### Paso a Paso del Laboratorio

1.  Instalamos las dependencias Express y JWT.

![](media/image1.png){width="5.808836395450569in" height="6.100528215223097in"}

2.  Nos ubicamos en la carpeta donde están los archivos y creamos un servidor.

![](media/image2.png){width="5.683825459317585in" height="1.7918219597550307in"}

3.  Abrimos una segunda pestaña de Powershell y nos dirigimos de nuevo a la misma carpeta donde están los archivos, luego hacemos el registro del usuario.

4.  Al hacer el login nos devuelve nuestro token.

![](media/image3.png){width="6.6930555555555555in" height="0.6388888888888888in"}

5.  Guardamos el token y verificamos que se halla guardado con éxito.

![](media/image4.png){width="6.6930555555555555in" height="0.25in"}

6.  Intentamos hacer un GET sin token y nos debe salir error 401 al intentarlo.

![](media/image5.png){width="6.6930555555555555in" height="0.8770833333333333in"}

7.  Ahora realizamos el GET con el token.

![](media/image6.png){width="6.6930555555555555in" height="0.6965277777777777in"}

8.  Creamos una nueva tarea.

![](media/image7.png){width="6.6930555555555555in" height="0.725in"}

9.  Y ahora creamos otra tarea para luego hacer PUT y DELETE.

![](media/image8.png){width="6.6930555555555555in" height="0.8444444444444444in"}

10. Actualizamos con PUT.

![](media/image9.png){width="6.6930555555555555in" height="0.7395833333333334in"}

11. Ahora lo borramos con DELETE

![](media/image10.png){width="6.6930555555555555in" height="0.2013888888888889in"}

12. Modificamos el servidor para que escuche, de esta forma.

![](media/image11.png){width="6.6930555555555555in" height="2.6555555555555554in"}

13. ![](media/image12.png){width="6.75in" height="2.970873797025372in"}Utilizando el usuario y la IP del PC1, abrímos el PC2 y nos conectamos por ssh, luego registramos el usuario en la PC2.

14. ![](media/image13.png){width="7.199305555555555in" height="0.225in"}Guardamos el token en nuestra otra PC.

15. Creamos una tarea y la actualizamos.

![](media/image14.png){width="8.183333333333334in" height="2.0407185039370077in"}

16. ![](media/image15.png){width="8.14988845144357in" height="1.278513779527559in"}Para finalizar, borramos la actualización.
