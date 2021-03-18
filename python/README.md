# agenda-rmi
Agenda de contactos creada con Python y una arquitectura RMI.

EL usuario (cliente) puede crear y listar contactos.

Esta aplicaci�n est� creada usando el m�dulo **Pyro4**. La documentaci�n oficial se puede encontrar [aqui](https://pyro4.readthedocs.io/en/stable/).

Antes de ejecutar la aplicación, debemos instalar algunas librerias:

```
pip install Pyro4
pip install pymongo
pip install PySimpleGUI
```

Una vez instalado esto, deberemos ejecutar el nombre del servidor, para lo que utilizaremos el comando:
```
python3 -m Pyro4.naming
```

Ahora podemos proceder a ejecutar el servidor y el cliente, para lo que ejecutaremos los siguientes comandos:
```
python server.py
python client.py
```

Dado que se est� usando una base de datos en la nube, hay completa libertad de agregar datos y leerlos en cualquier momento.

La explicaci�n en video se puede encontrar en el siguiente [enlace](https://www.youtube.com/watch?v=JZ-5qzYjrwM).
