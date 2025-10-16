package run;

import config.JPAUtil;
import repository.dao.AutorDao;
import repository.dao.CategoriaDao;
import repository.dao.LibroDao;
import entities.Autor;
import entities.Categoria;
import entities.Libro;
import java.time.LocalDate;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        AutorDao autorDao = new AutorDao();
        LibroDao libroDao = new LibroDao();
        CategoriaDao categoriaDao = new CategoriaDao();

        // --- 1. INSERTAR DATOS ---
        System.out.println("--- Guardando Autores y Categorías ---");
        Autor autor1 = new Autor("Gabriel García Márquez", "Colombiano", LocalDate.of(1927, 3, 6));
        Autor autor2 = new Autor("Isabel Allende", "Chilena", LocalDate.of(1942, 8, 2));
        autorDao.guardar(autor1);
        autorDao.guardar(autor2);

        Categoria catFiccion = new Categoria("Ficción");
        Categoria catRealismo = new Categoria("Realismo Mágico");
        categoriaDao.guardar(catFiccion);
        categoriaDao.guardar(catRealismo);

        // --- 2. CREAR LIBROS Y RELACIONAR ---
        System.out.println("\n--- Guardando Libros y sus relaciones ---");
        Libro libro1 = new Libro("Cien Años de Soledad", 1967);
        libro1.setAutor(autor1);
        libro1.getCategorias().add(catFiccion);
        libro1.getCategorias().add(catRealismo);
        libroDao.guardar(libro1);

        Libro libro2 = new Libro("El amor en los tiempos del cólera", 1985);
        libro2.setAutor(autor1);
        libro2.getCategorias().add(catFiccion);
        libroDao.guardar(libro2);

        Libro libro3 = new Libro("La casa de los espíritus", 1982);
        libro3.setAutor(autor2);
        libro3.getCategorias().add(catFiccion);
        libro3.getCategorias().add(catRealismo);
        libroDao.guardar(libro3);

        // --- 3. CONSULTAR Y MOSTRAR ---
        System.out.println("\n--- LISTADO DE LIBROS EN LA BIBLIOTECA ---");

        // La variable ahora es un Set
        Set<Libro> librosUnicos = libroDao.obtenerTodos();

        for (Libro libro : librosUnicos) { // Iteramos sobre el Set
            System.out.println("--------------------");
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("  Autor: " + libro.getAutor().getNombre());
            System.out.print("  Categorías: ");
            libro.getCategorias().forEach(cat -> System.out.print(cat.getNombre() + " "));
            System.out.println();
        }
        System.out.println("--------------------");

        // --- 4. CERRAR CONEXIÓN ---
        System.out.println("\nCerrando la conexión...");
        JPAUtil.close();
        System.out.println("Conexión cerrada.");
    }
}