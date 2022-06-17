package webproject.services;

import webproject.dao.DocumentsDAO;
import webproject.model.Classes.Mail;
import webproject.model.Interfaces.Document;
import webproject.model.Interfaces.PersonnelDocument;
import webproject.model.Interfaces.Status;
import webproject.utils.EntityManagerFactory;

import java.util.List;

public class DocumentServiceImplDao implements DocumentService {
    private final DocumentsDAO entityManager = EntityManagerFactory.getEntityManager();

    @Override
    public List<Document> getAllDocuments() throws Exception {
        return entityManager.findAll();
    }

    @Override
    public <T extends Document> List<T> getAllDocuments(Class<T> tClass) throws Exception {
        return entityManager.findAll(tClass);
    }

    @Override
    public Document getById(Long id) throws Exception {
        return entityManager.findById(id);
    }

    @Override
    public <T extends PersonnelDocument> List<T> getAllByEmployee(String employee, Class<T> tClass)
            throws Exception {
        return entityManager.findAllByEmployee(employee, tClass);
    }

    @Override
    public <T extends PersonnelDocument> List<T> getAllByStatus(Status status, Class<T> tClass) throws Exception {
        return entityManager.findAllByStatus(status, tClass);
    }

    @Override
    public <T extends Document> T getByNumber(int number, Class<T> tClass) throws Exception {
        return entityManager.findByNumber(number, tClass);
    }

    @Override
    public List<Mail> getAllByEmail(String email) throws Exception {
        return entityManager.findAllByEmail(email);
    }

    @Override
    public <T extends Document> T save(T document) throws Exception {
        return entityManager.save(document);
    }

    @Override
    public <T extends Document> List<T> save(List<T> documents) throws Exception {
        return entityManager.save(documents);
    }

    @Override
    public <T extends Document> void delete(T document) throws Exception {
        entityManager.delete(document);
    }
}
