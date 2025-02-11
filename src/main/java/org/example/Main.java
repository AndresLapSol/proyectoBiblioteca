package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public  static void main(String[] args) {
        LibroRepository repository = new LibroRepository();
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("####-MENU BIBLIOTECA-####");
            System.out.println("1| Agregar libro");
            System.out.println("2| Modificar libro");
            System.out.println("3| Eliminar libro");
            System.out.println("4| Buscar libro");
            System.out.println("5| Consultar catálogo");
            System.out.println("6| Guardar catálogo");
            System.out.println("7| Cargar catálogo");
            System.out.println("8| Filtrar por genero");
            System.out.println("0| Salir");
            System.out.print("Seleccione una opción: ");

            // Validar que la entrada es un entero
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                sc.nextLine(); // Consumir el salto de línea después de nextInt()

                switch (opcion) {
                    case 1:
                        repository.agregarLibro();
                        break;
                    case 2:
                        repository.modificarLibro();
                        break;
                    case 3:
                        repository.eliminarLibro();
                        break;
                    case 4:
                        repository.buscarLibro();
                        break;
                    case 5:
                        repository.listarLibros();
                        break;
                    case 6:
                        String archivoGuardar = "catalogo.dat";
                        repository.guardarCatalogo(archivoGuardar);
                        break;

                    case 7:
                        System.out.print("Introduzca el nombre del archivo para cargar el catálogo: ");
                        String archivoCargar = sc.nextLine();
                        repository.cargarCatalogo(archivoCargar);
                        break;

                    case 8:
                        System.out.print("Introduzca el género para filtrar: ");
                        String generoFiltrar = sc.nextLine();
                        List<Libro> librosFiltrados = repository.filtrarPorGenero(generoFiltrar);
                        librosFiltrados.forEach(System.out::println);
                        break;
                    case 0:
                        System.out.println("Saliendo del programa. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                sc.next(); // Consumir entrada no válida
            }
        } while (opcion != 0);

        sc.close(); // Cerrar el Scanner al final del programa
    }
}