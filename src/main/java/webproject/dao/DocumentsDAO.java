package webproject.dao;

import webproject.model.Classes.Mail;
import webproject.model.Interfaces.Document;
import webproject.model.Interfaces.PersonnelDocument;
import webproject.model.Interfaces.Status;

import java.util.List;

public interface DocumentsDAO {
    /**
     * Returns all documents from storage
     *
     * @return List of documents
     * @throws Exception Error accessing the repository
     */
    List<Document> findAll() throws Exception;

    /**
     * Returns all documents of the specified type from storage
     *
     * @param tClass Type of documents to be received
     * @param <T>    Generic type extends Document class
     * @return List of documents
     * @throws Exception Error accessing the repository
     */
    <T extends Document> List<T> findAll(Class<T> tClass) throws Exception;

    /**
     * Returns count of all documents from storage
     *
     * @return Count of documents
     * @throws Exception Error accessing the repository
     */
    Long getCountAll() throws Exception;

    /**
     * Returns count of all documents of the specified type from storage
     *
     * @param tClass Type of documents to be received
     * @param <T>    Generic type extends Document class
     * @return Count of documents
     * @throws Exception Error accessing the repository
     */
    <T extends Document> Long getCountAll(Class<T> tClass) throws Exception;

    /**
     * Returns the document by ID from storage
     *
     * @param id Document ID
     * @return Document
     * @throws Exception Error accessing the repository
     */
    Document findById(Long id) throws Exception;

    /**
     * Returns personnel documents related to the specified employee from storage
     *
     * @param employee Employee name
     * @param tClass   Type of documents to be received
     * @param <T>      Generic type extends PersonnelDocument class
     * @return List of personnel documents
     * @throws Exception Error accessing the repository
     */
    <T extends PersonnelDocument> List<T> findAllByEmployee(String employee, Class<T> tClass) throws Exception;

    /**
     * Returns personnel documents with the specified status from storage
     *
     * @param status Status of document
     * @param tClass Type of documents to be received
     * @param <T>    Generic type extends PersonnelDocument class
     * @return List of personnel documents
     * @throws Exception Error accessing the repository
     */
    <T extends PersonnelDocument> List<T> findAllByStatus(Status status, Class<T> tClass) throws Exception;

    /**
     * Returns count of personnel documents with the specified status from storage
     *
     * @param status Status of document
     * @param tClass Type of documents to be received
     * @param <T>    Generic type extends PersonnelDocument class
     * @return Count of personnel documents
     * @throws Exception Error accessing the repository
     */
    <T extends PersonnelDocument> Long getCountByStatus(Status status, Class<T> tClass) throws Exception;

    /**
     * Returns document by number from storage
     *
     * @param number Document number
     * @param tClass Type of documents to be received
     * @param <T>    Generic type extends Document class
     * @return Document
     * @throws Exception Error accessing the repository
     */
    <T extends Document> T findByNumber(int number, Class<T> tClass) throws Exception;

    /**
     * Returns mails by email from storage
     *
     * @param email Email sender or receiver
     * @return List of mails
     * @throws Exception Error accessing the repository
     */
    List<Mail> findAllByEmail(String email) throws Exception;

    /**
     * Saving or updating a document in the repository
     *
     * @param document New or modified document
     * @param <T>      Generic type extends Document class
     * @return Document
     * @throws Exception Error accessing the repository
     */
    <T extends Document> T save(T document) throws Exception;

    /**
     * Saving or updating documents in the repository
     *
     * @param documents List of new or modified documents
     * @param <T>       Generic type extends Document class
     * @return List of document
     * @throws Exception Error accessing the repository
     */
    <T extends Document> List<T> save(List<T> documents) throws Exception;

    /**
     * Delete the document from storage
     *
     * @param <T> Generic type extends Document class
     * @throws Exception Error accessing the repository
     */
    <T extends Document> void delete(T document) throws Exception;
}