import Pyro4
from pymongo import MongoClient
from pprint import pprint
import PySimpleGUI as sg

# cliente = ''
db = ''

# Servidor para una agenda de contactos usando MongoDB

@Pyro4.expose
class Agenda(object):
    def iniciar_sesion(self, usuario, contrasena):
        db_usuario = db.usuarios.find_one({'nombre': str(usuario)})
        if (db_usuario != None):
            return db.usuario
        else:
            return 'Datos incorrectos'
       
    def crear_contacto(self, contacto):
        # print(contacto)
        try:
            db.agendas.insert_one(contacto)
            return True
        except:
            return False
        
    def listar_contactos(self, usuario):
        # nombre, compania, titulo, tel movil, ttel rabajo, 
        contactos = []
        for contacto in db.agendas.find({"usuario": usuario}):
            dato = [ contacto['nombre'], contacto['compania'], contacto['titulo'], contacto['movil'],
                contacto['trabajo']
            ]
            contactos.append(dato)

        return contactos

def iniciar_servidor():
    daemon = Pyro4.Daemon()
    ns = Pyro4.locateNS()
    uri = daemon.register(Agenda)
    ns.register('agenda.server', str(uri))
    print('Servidor listo...')
    daemon.requestLoop()

def conectar_bd():
    cliente = MongoClient("mongodb+srv://saul-rw:aBqPZS4ZuMkxPGc3@cluster0.0aw1j.mongodb.net/agenda?retryWrites=true&w=majority")
    global db
    db = cliente.agenda
    #result = db.usuarios.find_one({'nombre': 'saul'})
    #print(result)

if __name__ == '__main__':
    # print('hola')
    # display()
    conectar_bd()
    iniciar_servidor()

# window_login.close()
exit
