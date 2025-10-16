package repository.dao;

import config.JPAUtil;
import entities.Libro;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LibroDao {
    public void guardar(Libro libro) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    /**
     * Busca un libro por su ID.
     */
    public Libro buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Libro.class, id);
        } finally {
            if (em != null) em.close();
        }
    }

    /**
     * Devuelve una lista con todos los libros.
     * El JOIN FETCH asegura que los datos del autor se carguen en la misma consulta.
     * @return Lista de libros.
     */
    // Cambiamos el tipo de retorno a Set<Libro>
    public Set<Libro> obtenerTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT l FROM Libro l JOIN FETCH l.autor JOIN FETCH l.categorias";
            List<Libro> listaConDuplicados = em.createQuery(jpql, Libro.class).getResultList();

            // Convertimos la lista a un Set para eliminar automáticamente los duplicados
            return listaConDuplicados.stream().collect(Collectors.toSet());
        } finally {
            if (em != null) em.close();
        }
    }
}