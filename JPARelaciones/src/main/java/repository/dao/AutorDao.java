package repository.dao;

import config.JPAUtil;
import entities.Autor;
import jakarta.persistence.EntityManager;
import java.util.List;

public class AutorDao {
    public void guardar(Autor autor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    /**
     * Busca un autor por su ID.
     */
    public Autor buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // em.find() es la forma más directa de buscar por clave primaria.
            return em.find(Autor.class, id);
        } finally {
            if (em != null) em.close();
        }
    }

    /**
     * Devuelve una lista con todos los autores de la base de datos.
     * @return Lista de autores.
     */
    public List<Autor> obtenerTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT a FROM Autor a", Autor.class).getResultList();
        } finally {
            if (em != null) em.close();
        }
    }
}