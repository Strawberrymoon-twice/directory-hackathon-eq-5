package org.generation;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

//import javax.swing.plaf.synth.SynthOptionPaneUI;


//Inicialización de atributos
public class Directory {
	public String nombre = " ";
	public String telefono = " ";
	Scanner sc = new Scanner(System.in);
	
	HashMap<String, String> directory = new HashMap<>();
	
	//1. Método para añadir contacto
	public HashMap  añadirContacto(String nombre, String telefono) {
		if (!(directory.containsKey(nombre))){
			System.out.println("Se guardo el contacto exitosamente.");
			directory.put(nombre,telefono);
			return directory;
		}else
			System.out.println("El contacto ya existe, por lo que no fue agregado.");
			return directory;
		
	}
	
	//2.1Método para buscar contacto
	public String buscarContacto(String nombre) {
		if (nombre == null) {
			System.out.println("El contacto no existe");
		}
		return directory.get(nombre);
	}
	
	//2. Solicitar el nombre de un contacto a buscar
	public void solicitarNombre() {
		System.out.print("Ingrese el nombre: ");
		String nombreABuscar = sc.nextLine();
		String numeroEncontrado = buscarContacto(nombreABuscar);
		System.out.println("El numero es: "+ numeroEncontrado);
	}
	
	//3.Modificar el contacto
		public boolean modificarContacto(String nombre, String nuevoNumero) {
			if (directory.containsKey(nombre)) {
				directory.put(nombre, nuevoNumero);
				return true;
			}
			return false;
		}
		
	//3.Solicitar modificación de contacto
		public void solicitarModificacion() {
			System.out.println("Ingrese el nombre del contacto a modificar");
			String nombreModificar =sc.nextLine();
			System.out.println("Ingrese el nuevo número: ");
			String nuevoNumero = sc.nextLine();
			
			if (modificarContacto(nombreModificar, nuevoNumero)) {
				System.out.println("El contacto se ha modificado");
				System.out.println(" ");
			} else {
				System.out.println("El contacto no existe");
			}
		}
	
	
	
	//4 Solicitar eliminación
	public void solicitarEliminación() {
		System.out.println("Ingrese el nombre del contacto a eliminar");
		String nombreEliminar = sc.nextLine();
		if(!eliminarContacto(nombreEliminar)) {
			System.out.println("El contacto no existe");
		}
		
	}
	//4.1Eliminar contacto
	public boolean eliminarContacto(String nombre) {
		if (directory.containsKey(nombre)) {
			directory.remove(nombre);
			return true;
		}
		return false;
	}
	
	//5. Ver todos los contactos
		public void mostrarContactos() {
			if (directory.isEmpty()) {
				System.out.println("El directorio no tiene contactos");
				return;
			}
			else System.out.println("AGENDA TELEFÓNICA");
			for (String nombre : directory.keySet()) {
				System.out.println("NOMBRE: " + nombre + " TÉLEFONO: " + directory.get(nombre));
			}
		}
		//6 Verificar espacios libres

		public void espaciosLibres(){
			int espaciosLibres = 10 - directory.size();
			System.out.println("Los espacios restantes en el directorio son: "+ espaciosLibres);
			
		}
	
	//1.Método para recibir las entradas del usuario.
	public HashMap obtenerContacto() {
		System.out.println("Ingresar el nombre del contacto: ");
		String nombre = sc.nextLine();
		System.out.println("Ingrese el número telefóno: ");
		String numero = sc.nextLine();
		return añadirContacto(nombre, numero);
	}
		
	//Ejecución del menú
	
	public void ejecutarMenu() {
		int menuOpcion;
		
		
		do {
			System.out.println(" ");
			System.out.println("AGENDA TELEFÓNICA");
			System.out.println(" ");			
			System.out.println("Elige una opción del menú: ");
			System.out.println(" ");
			System.out.println("1. Añadir contacto");
			System.out.println("2. Ver existencia del contacto");
			System.out.println("3. Modificar número de contacto");
			System.out.println("4. Eliminar contacto");
			System.out.println("5. Ver lista de contactos");
			System.out.println("6. Verificar espacios libres restantes");
			System.out.println("7. Salir de la agenda");
			
			menuOpcion = sc.nextInt();
			sc.nextLine();
			
			switch (menuOpcion) {
			case 1:
				if (directory.size()<=10) {
					obtenerContacto();
				} else{
					System.out.println("La agenda ha alcanzado el máximo de contactos guardados.");
				}
				
				break;
			case 2:
				solicitarNombre();
				break;
			case 3:
				solicitarModificacion();
				break;
			case 4:
				solicitarEliminación();
				break;
			case 5:
				mostrarContactos();
				break;
			case 6:
				espaciosLibres();
				break;
			case 7:
				System.out.println("Se ha cerrado la agenda");
		
			default:
				System.out.println("La opción ingresada no es válida");
				
			}
			
		}while(menuOpcion != 7);
		sc.close();
	}
	
}
