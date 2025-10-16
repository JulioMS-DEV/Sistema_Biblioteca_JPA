package repository.dao;

import config.JPAUtil;
import entities.Categoria;
import jakarta.persistence.EntityManager;
import java.util.List;

public class CategoriaDao {
    public void guardar(Categoria categoria) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(categoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    /**
     * Busca una categoría por su ID.
     */
    public Categoria buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Categoria.class, id);
        } finally {
            if (em != null) em.close();
        }
    }

    /**
     * Devuelve una lista con todas las categorías.
     * @return Lista de categorías.
     */
    public List<Categoria> obtenerTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
        } finally {
            if (em != null) em.close();
        }
    }
}