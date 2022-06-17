package webproject.utils;

import webproject.dao.DocumentsDAO;
import webproject.dao.DocumentsDAOImplHQL;

public class EntityManagerFactory {
    private static final DocumentsDAO entityManager = buildEntityManagerFactory();

    protected static DocumentsDAO buildEntityManagerFactory() {
        try {
            return new DocumentsDAOImplHQL(
                    HibernateSessionFactory
                            .getSessionFactory()
                            .openSession()
            );
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Initial EntityManagerFactory failed" + e);
        }
    }

    public static DocumentsDAO getEntityManager() {
        return entityManager;
    }
}
