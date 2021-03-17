import Pyro4
import PySimpleGUI as sg

server = Pyro4.Proxy('PYRONAME:agenda.server')

data = [ ['nombre', 'asdad', 'asdadsaweq'], 
        ['wea'], 
        ['otrawea'] ]

#data = ['aaa', 'bbb', 'ccc']

# Contenido de las ventanas 
layout_login = [
    [sg.Text('Iniciar sesion')],
    [sg.Text("Nombre de usuario")],
    [sg.Input(key='-USUARIO-')],
    [sg.Text('Contrasena')],
    [sg.Input(password_char='*', key='-CONTRASENA-')],
    [sg.Text(size=(40,1), key='-OUTPUT-')],
    [sg.Button('Iniciar sesion'), sg.Button('Registrarme'), sg.Button('Salir')]
]

layout_registro = [
    [sg.Text('heyyy this is a new window')]
]

headings = ['Nombre', 'Compania', 'Titulo', 'Movil', 'Trabajo']
layout_agenda = [
    [sg.Button('Mostrar'), sg.Button('Agregar'), sg.Button('Eliminar')],
    [sg.Table(values=[[]], auto_size_columns=False, def_col_width=30, enable_events=False, key='-CONTACTOS-')]
]

# nombre, compania, titulo, tel movil, ttel rabajo, 
layout_agregar = [
    [sg.Text('Nombre')],
    [sg.Input(key='-NOMBRE-')],
    [sg.Text('Compania')],
    [sg.Input(key='-COMPANIA-')],
    [sg.Text('Titulo')],
    [sg.Input(key='-TITULO-')],
    [sg.Text('Telefono Movil')],
    [sg.Input(key='-MOVIL-')],
    [sg.Text('Telefono Trabajo')],
    [sg.Input(key='-TRABAJO-')],
    [sg.Button('Agregar')]
]

# Crear las ventanas
window_login = sg.Window('Iniciar sesion', layout_login)
window_registro = sg.Window('Registro', layout_registro)
window_agenda = sg.Window('Agenda de contactos', layout_agenda)
window_agregar = sg.Window('Agregar contacto', layout_agregar)

def crear_ventana(title, opts):
    return sg.Window(title=title, layout=list(opts))

def display():
    #
    # Mostrar ventana de inicio de sesion
    #
    r = 0
    usuario = ''
    while True:
        event, values = window_login.read()
        # Verificar si el usuario cierra la ventana o da clic en el botton de Salir
        if event == sg.WINDOW_CLOSED or event == 'Salir':
            r = 0
            break

        # Verificar si el usuario hizo clic en el boton de Registrarme
        if event == 'Registrarme':
            r = 1
            break
       
       # Verificar datos de inicio de sesion
        if values['-USUARIO-'] == 'saul' and values['-CONTRASENA-'] == '':
            r = 2
            usuario = values['-USUARIO-']
            break
        # Output a message to the window
        # window['-OUTPUT-'].update('Hello ' + values['-INPUT-'] + '! Thanks for trying PySImpleGUI')
    
    #window_login.close()

    if r == 1:
        # Mostrar ventana de registro
        while True:
            event, values = window_registro.read()

            if event == sg.WINDOW_CLOSED:
                break

        display()
    elif r == 2:
        window_login.close()
        # Mostrar ventana de agenda
        while True:
            event, values = window_agenda.read()

            if event == sg.WINDOW_CLOSED:
                break

            if event == 'Mostrar':
                contactos = server.listar_contactos(usuario)
                tabla = window_agenda['-CONTACTOS-']
                tabla.update(values=contactos, visible=True)
                print(tabla.get())

            if event == 'Agregar':
                n_event, n_values = window_agregar.read()

                if event == sg.WINDOW_CLOSED:
                    break

                if event == 'Agregar':
                    # print(n_values)
                    datos = {
                        'nombre': n_values['-NOMBRE-'], 'compania': n_values['-COMPANIA-'],
                        'titulo': n_values['-TITULO-'], 'movil': n_values['-MOVIL-'],
                        'trabajo': n_values['-TRABAJO-'], 'usuario': usuario
                    }
                    resultado = server.crear_contacto(datos)
                    if resultado:
                        sg.popup('Agregado con exito')

            # if event == 'Eliminar':
            #     #seleccion = values['-CONTACTOS-']
            #     elemento = window_agenda['-CONTACTOS-'].get()
            #     seleccion = values['-CONTACTOS-'][0]
            #     print(elemento[seleccion])

if __name__ == '__main__':
    display()
    
window_login.close()
window_registro.close()
window_agenda.close()
window_agregar.close()
exit
