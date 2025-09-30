package org.generation;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Directory {
	

	//Clase Contacto interna
    static class Contacto {
        private String nombre;
        private String telefono;
	
        //constructor de clase interna
        public Contacto(String nombre, String telefono) {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException(" \nEl nombre no puede estar vacío");
            }
            this.nombre = nombre;
            this.telefono = telefono;
        }
      
     // Getters
        public String getNombre() {
            return nombre;
        }
        
        public String getTelefono() {
            return telefono;
        }
        
        // Setter
        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }
    }
       
	//Declaración de atributos
	private HashMap<String, Contacto> directory;
	private int limiteMaximo =10;
	private Scanner sc;
	
	//Constructor
	public Directory() {
        this.directory = new HashMap<>();
        this.sc = new Scanner(System.in);
    }
	
	//0. Método para solicitar texto
	public String solicitarTexto(String mensaje) {
        System.out.print(mensaje + ": ");
        return sc.nextLine();
    }
	  
	//0.1 Método para medir la capacidad de la agenda
	public boolean agendaLlena() {
	    if (directory.size() >= limiteMaximo) {
	        System.out.println(" \nLa agenda está llena. No se puede añadir el contacto.");
	        return true;
	    }
	    return false;
	}
	//0.2 Evalúa la existencia de un contacto
	 public boolean existeContacto(String nombre) {
	        return directory.containsKey(nombre);
	    }
	
	//1. Método para añadir contacto
	public void  añadirContacto(Contacto c) {
		if (agendaLlena() || existeContacto(c.getNombre())) {
			 System.out.println(" \nEl contacto ya existe o la agenda está llena.");
	        return;
	    }
	    
	    directory.put(c.getNombre(), c);
	    System.out.println(" \n Contacto añadido exitosamente.");
	}
	
	//2. Método para buscar contacto
	public void buscarContacto(String nombre) {
		Contacto contacto = directory.get(nombre);
        if (contacto != null) {
            System.out.println("\nEl numero es: " + contacto.getTelefono());
        } else {
            System.out.println("\n El contacto no existe, por lo tanto no se puede modificar.");
        }
    }
	
	//3.Modificar el contacto
    public void modificarTelefono(String nombre, String nuevoTelefono) {
        Contacto contacto = directory.get(nombre);
        if (contacto != null) {
            contacto.setTelefono(nuevoTelefono);
            System.out.println("\n Se ha modificado el número de teléfono exitosamente.");
        } else {
            System.out.println("\nEl contacto no existe.");
        }
    }
		
	//4.Eliminar contacto
    public void eliminarContacto(String nombre) {
        if (directory.containsKey(nombre)) {
            directory.remove(nombre);
            System.out.println("\nContacto eliminado exitosamente.");
        } else {
            System.out.println("\n El contacto no existe.");
        }
    }
	
	//5. Ver todos los contactos
		public void listarContactos() {
			if (directory.isEmpty()) {
				System.out.println("\n La agenda no tiene contactos");
				return;
			}
			else System.out.println("AGENDA TELEFÓNICA");
			for (Contacto contacto : directory.values()) {
				System.out.println("NOMBRE: " + contacto.getNombre() + " TÉLEFONO: " + contacto.getTelefono());
			}
		}
		//6 Verificar espacios libres

		public void espaciosLibres(){
			int espaciosLibres = limiteMaximo - directory.size();
			System.out.println("\nLos espacios restantes en el directorio son: "+ espaciosLibres+ " espacios.");
			
		}
	
		// Métodos auxiliares
	    private Contacto solicitarDatosContacto() {
	        String nombre = solicitarTexto("\nIngrese el nombre del contacto");
	        String telefono = solicitarTexto("\nIngrese el teléfono");
	        return new Contacto(nombre, telefono);
	    }
	    
	    private void solicitarYAgregarContacto() {
	        Contacto nuevoContacto = solicitarDatosContacto();
	        añadirContacto(nuevoContacto);
	    }
	    
	    private void solicitarBusqueda() {
	        String nombre = solicitarTexto("\nIngrese el nombre del contacto a buscar");
	        buscarContacto(nombre);
	    }
	    
	    private void solicitarEliminacion() {
	        String nombre = solicitarTexto("\nIngrese el nombre del contacto a eliminar");
	        eliminarContacto(nombre);
	    }
	    
	    private void solicitarModificacion() {
	        String nombre = solicitarTexto("\nIngrese el nombre del contacto a modificar");
	        String nuevoTelefono = solicitarTexto("\nIngrese el nuevo teléfono");
	        modificarTelefono(nombre, nuevoTelefono);
	    }
	    
	//Ejecución del menú
	
	public void ejecutarMenu() {
		int menuOpcion;
		
		
		do {
			System.out.println("\n AGENDA TELEFÓNICA \n");			
			System.out.println("Elige una opción del menú: \n");
			System.out.println("1. Añadir contacto");
			System.out.println("2. Buscar un contacto");
			System.out.println("3. Modificar número de contacto");
			System.out.println("4. Eliminar contacto");
			System.out.println("5. Ver lista de contactos");
			System.out.println("6. Verificar espacios libres restantes");
			System.out.println("7. Salir de la agenda");
			
			try {
	            menuOpcion = sc.nextInt();
	            sc.nextLine(); // Limpiar buffer después de nextInt()
	            
	            switch (menuOpcion) {
	                case 1:
	                    solicitarYAgregarContacto();
	                    break;
	                case 2:
	                    solicitarBusqueda();
	                    break;
	                case 3:
	                    solicitarModificacion();
	                    break;
	                case 4:
	                    solicitarEliminacion();
	                    break;
	                case 5:
	                    listarContactos();
	                    break;
	                case 6:
	                    espaciosLibres();
	                    break;
	                case 7:
	                    System.out.println("Se ha cerrado la agenda");
	                    break;
	                default:
	                    System.out.println("La opción ingresada no es válida. Por favor ingresa un número del 1 al 7.");
	            }
	        } catch (InputMismatchException e) {
	            System.out.println("Error: Por favor ingrese solo números del 1 al 7.");
	            sc.nextLine(); // Limpiar el buffer del scanner
	            menuOpcion = 0; // Reiniciar opción
	        }
	        
	    } while(menuOpcion != 7);
	    sc.close();
	}
}