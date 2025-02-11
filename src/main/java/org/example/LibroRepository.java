package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class LibroRepository {

    private final SessionFactory sessionFactory;

    public LibroRepository() {
        // Inicializar la SessionFactory una sola vez
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Libro.class)
                .buildSessionFactory();
    }

    public void agregarLibro() {
        try (Session session = sessionFactory.openSession()) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("### AÑADIR LIBRO ###");
            System.out.println("Introduzca el NOMBRE del libro: ");
            String titulo = scanner.nextLine();

            System.out.println("Introduzca el AUTOR del libro: ");
            String autor = scanner.nextLine();

            System.out.println("Introduzca el GENERO del libro: ");
            String genero = scanner.nextLine();

            System.out.println("Introduzca el ISBN del libro: ");
            String isbn = scanner.nextLine();

            System.out.println("Introduzca el AÑO del libro: ");
            int anoPublicacion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            // Validar que el ISBN no esté duplicado
            if (existeLibroPorIsbn(isbn)) {
                System.out.println("Error: Ya existe un libro con el mismo ISBN.");
                return;
            }

            // Crear un objeto Libro
            Libro nuevoLibro = new Libro(titulo, autor, genero, isbn, anoPublicacion);

            // Guardar el libro en la base de datos
            session.beginTransaction();
            session.save(nuevoLibro);
            session.getTransaction().commit();

            System.out.println("Libro guardado con éxito: " + nuevoLibro);
        } catch (Exception e) {
            System.out.println("Error al agregar el libro: " + e.getMessage());
        }
    }

    public void modificarLibro() {
        try (Session session = sessionFactory.openSession()) {
            Scanner scanner = new Scanner(System.in);

            // Mostrar todos los libros
            List<Libro> libros = listarLibros();
            libros.forEach(libro -> System.out.println("-------------------\n" + libro.getTitulo()));

            // Buscar el libro a modificar
            System.out.println("### MODIFICAR LIBRO ###");
            System.out.println("Introduzca el NOMBRE del libro que desea modificar: ");
            String titulo = scanner.nextLine();

            Libro libro = buscarLibroPorTitulo(titulo);
            if (libro == null) {
                System.out.println("Libro no encontrado.");
                return;
            }

            System.out.println("Introduzca el NUEVO NOMBRE del libro: ");
            libro.setTitulo(scanner.nextLine());

            System.out.println("Introduzca el NUEVO AUTOR del libro: ");
            libro.setAutor(scanner.nextLine());

            System.out.println("Introduzca el NUEVO GENERO del libro: ");
            libro.setGenero(scanner.nextLine());

            System.out.println("Introduzca el NUEVO AÑO del libro: ");
            libro.setAnioPublicacion(scanner.nextInt());
            scanner.nextLine(); // Consumir el salto de línea

            // Actualizar el libro en la base de datos
            session.beginTransaction();
            session.merge(libro);
            session.getTransaction().commit();

            System.out.println("Libro modificado con éxito: " + libro);
        } catch (Exception e) {
            System.out.println("Error al modificar el libro: " + e.getMessage());
        }
    }

    public void eliminarLibro() {
        try (Session session = sessionFactory.openSession()) {
            Scanner scanner = new Scanner(System.in);

            // Mostrar todos los libros
            List<Libro> libros = listarLibros();
            libros.forEach(libro -> System.out.println("-------------------\n" + libro.getTitulo()));

            // Buscar el libro a eliminar
            System.out.println("### ELIMINAR LIBRO ###");
            System.out.println("Introduzca el NOMBRE del libro que desea eliminar: ");
            String titulo = scanner.nextLine();

            Libro libro = buscarLibroPorTitulo(titulo);
            if (libro == null) {
                System.out.println("Libro no encontrado.");
                return;
            }

            // Eliminar el libro
            session.beginTransaction();
            session.delete(libro);
            session.getTransaction().commit();

            System.out.println("Libro eliminado con éxito: " + libro.getTitulo());
        } catch (Exception e) {
            System.out.println("Error al eliminar el libro: " + e.getMessage());
        }
    }

    public void buscarLibro() {
        try (Session session = sessionFactory.openSession()) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("### BUSCAR LIBRO ###");
            System.out.println("Introduzca el NOMBRE del libro que desea buscar: ");
            String titulo = scanner.nextLine();

            List<Libro> libros = session.createQuery(
                            "FROM Libro WHERE titulo LIKE :titulo", Libro.class)
                    .setParameter("titulo", "%" + titulo + "%")
                    .getResultList();

            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros con ese título.");
            } else {
                libros.forEach(libro -> System.out.println("-------------------\n" + libro));
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el libro: " + e.getMessage());
        }
    }

    public void guardarCatalogo(String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            List<Libro> libros = listarLibros();
            oos.writeObject(libros);
            System.out.println("Catálogo guardado en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar el catálogo: " + e.getMessage());
        }
    }

    public void cargarCatalogo(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            List<Libro> libros = (List<Libro>) ois.readObject();

            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                for (Libro libro : libros) {
                    // Verificar si el libro ya existe por su clave primaria
                    Libro libroExistente = session.get(Libro.class, libro.getIsbn());

                    if (libroExistente == null) {
                        // Si no existe, lo guardamos
                        session.save(libro);
                    } else {
                        // Si ya existe, puedes decidir actualizarlo o no hacer nada
                        System.out.println("El libro con ID" + libro.getIsbn()  + " ya existe, se omite.");
                        // Si deseas actualizar el libro en lugar de omitirlo, usa session.update(libro);
                    }
                }

                session.getTransaction().commit();
            }

            System.out.println("Catálogo cargado desde " + nombreArchivo);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el catálogo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }


    public List<Libro> listarLibros() {
        try (Session session = sessionFactory.openSession()) {
            List<Libro> libros = session.createQuery("FROM Libro", Libro.class).getResultList();
            if (libros.isEmpty()) {
                System.out.println("No hay libros en el catálogo.");
            } else {
                libros.forEach(libro -> System.out.println("-------------------\n" + libro));
            }
            return libros;
        }
    }

    public List<Libro> filtrarPorGenero(String genero) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Libro WHERE genero = :genero", Libro.class)
                    .setParameter("genero", genero)
                    .getResultList();
        }
    }

    private Libro buscarLibroPorTitulo(String titulo) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Libro WHERE titulo = :titulo", Libro.class)
                    .setParameter("titulo", titulo)
                    .uniqueResult();
        }
    }

    private boolean existeLibroPorIsbn(String isbn) {
        try (Session session = sessionFactory.openSession()) {
            Long count = session.createQuery("SELECT COUNT(*) FROM Libro WHERE isbn = :isbn", Long.class)
                    .setParameter("isbn", isbn)
                    .uniqueResult();
            return count != null && count > 0;
        }
    }

    public void cerrar() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}