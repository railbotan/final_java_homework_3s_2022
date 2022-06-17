package webproject.dao;

import webproject.model.Classes.DismissalOrder;
import webproject.model.Classes.EmploymentOrder;
import webproject.model.Classes.Mail;
import webproject.model.Interfaces.Document;
import webproject.model.Interfaces.PersonnelDocument;
import webproject.model.Interfaces.Status;
import org.hibernate.Session;

import java.lang.reflect.Field;
import java.util.List;

public class DocumentsDAOImplHQL implements DocumentsDAO {
    private final Session session;

    private <T extends Document> void setPrivateStaticCount(Class<T> tClass) throws Exception {
        Field field = tClass.getDeclaredField("count");
        field.setAccessible(true);
        int count = getCountAll(tClass).intValue();
        field.set(null, count);
    }

    public DocumentsDAOImplHQL(Session session) throws Exception {
        this.session = session;
        this.setPrivateStaticCount(DismissalOrder.class);
        this.setPrivateStaticCount(EmploymentOrder.class);
        this.setPrivateStaticCount(Mail.class);
    }

    @Override
    public List<Document> findAll() throws Exception {
        return findAll(Document.class);
    }

    @Override
    public <T extends Document> List<T> findAll(Class<T> tClass) throws Exception {
        session.beginTransaction();
        try {
            String query = "SELECT d FROM " + tClass.getName() + " d";
            List<T> documents = session
                    .createQuery(query, tClass)
                    .getResultList();
            session.getTransaction().commit();
            return documents;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new Exception("Error when getting a data from the database. " +
                    e.getMessage());
        }
    }

    @Override
    public Long getCountAll() throws Exception {
        return getCountAll(Document.class);
    }

    @Override
    public <T extends Document> Long getCountAll(Class<T> tClass) throws Exception {
        session.beginTransaction();
        try {
            String query = "SELECT COUNT(*) FROM " + tClass.getName() + " d";
            Long countDocuments = session
                    .createQuery(query, Long.class)
                    .getSingleResult();
            session.getTransaction().commit();
            return countDocuments;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new Exception("Error when getting a data from the database. " +
                    e.getMessage());
        }
    }

    @Override
    public Document findById(Long id) throws Exception {
        session.beginTransaction();
        try {
            Document document = session
                    .createQuery("SELECT d FROM Document d WHERE d.id = :id", Document.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.getTransaction().commit();
            return document;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new Exception("Error when getting a data from the database by ID = " + id + ". " +
                    e.getMessage());
        }
    }

    @Override
    public <T extends PersonnelDocument> List<T> findAllByEmployee(String employee, Class<T> tClass) throws Exception {
        session.beginTransaction();
        try {
            String query = "SELECT d FROM " + tClass.getName() + " d WHERE d.employee LIKE :employee";
            List<T> documents = session
                    .createQuery(query, tClass)
                    .setParameter("employee", "%" + employee + "%")
                    .getResultList();
            session.getTransaction().commit();
            return documents;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new Exception("Error when getting a data from the database by " + employee + ". " +
                    e.getMessage());
        }
    }

    @Override
    public <T extends PersonnelDocument> List<T> findAllByStatus(Status status, Class<T> tClass) throws Exception {
        session.beginTransaction();
        try {
            String query = "SELECT d FROM " + tClass.getName() + " d WHERE d.status = :status";
            List<T> documents = session
                    .createQuery(query, tClass)
                    .setParameter("status", status)
                    .getResultList();
            session.getTransaction().commit();
            return documents;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new Exception("Error when getting a data from the database by " + status.getValue() + ". " +
                    e.getMessage());
        }
    }

    @Override
    public <T extends PersonnelDocument> Long getCountByStatus(Status status, Class<T> tClass) throws Exception {
        session.beginTransaction();
        try {
            String query = "SELECT COUNT(*) FROM " + tClass.getName() + " d WHERE d.status = :status";
            Long countDocuments = session
                    .createQuery(query, Long.class)
                    .setParameter("status", status)
                    .getSingleResult();
            session.getTransaction().commit();
            return countDocuments;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new Exception("Error when getting a data from the database by " + status.getValue() + ". " +
                    e.getMessage());
        }
    }

    @Override
    public <T extends Document> T findByNumber(int number, Class<T> tClass) throws Exception {
        session.beginTransaction();
        try {
            String query = "SELECT d FROM " + tClass.getName() + " d WHERE d.number = :number";
            T document = session
                    .createQuery(query, tClass)
                    .setParameter("number", number)
                    .getSingleResult();
            session.getTransaction().commit();
            return document;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new Exception("Error when getting a data from the database by number = " +
                    number + ". " + e.getMessage());
        }
    }

    @Override
    public List<Mail> findAllByEmail(String email) throws Exception {
        session.beginTransaction();
        try {
            List<Mail> documents = session
                    .createQuery("SELECT m FROM Mail m " +
                            "WHERE m.from LIKE :email OR m.to LIKE :email", Mail.class)
                    .setParameter("email", "%" + email + "%")
                    .getResultList();
            session.getTransaction().commit();
            return documents;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new Exception("Error when getting a data from the database by " + email + ". " +
                    e.getMessage());
        }
    }

    @Override
    public <T extends Document> T save(T document) throws Exception {
        session.beginTransaction();
        try {
            session.save(document);
            session.getTransaction().commit();
            return document;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new Exception("Error when saving the data into the database. " +
                    e.getMessage());
        }
    }

    @Override
    public <T extends Document> List<T> save(List<T> documents) throws Exception {
        session.beginTransaction();
        try {
            for (Document document : documents) {
                session.save(document);
            }
            session.getTransaction().commit();
            return documents;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new Exception("Error when saving the data into the database. " +
                    e.getMessage());
        }
    }

    @Override
    public <T extends Document> void delete(T document) throws Exception {
        session.beginTransaction();
        try {
            session.delete(document);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new Exception("Error when deleting the data from the database. " +
                    e.getMessage());
        }
    }
}