package webproject.services;

import webproject.model.Classes.Mail;
import webproject.model.Interfaces.Document;
import webproject.model.Interfaces.PersonnelDocument;
import webproject.model.Interfaces.Status;

import java.util.List;

public interface DocumentService {
    /**
     * Returns all documents
     *
     * @return List of documents
     * @throws Exception Data access error
     */
    List<Document> getAllDocuments() throws Exception;

    /**
     * Returns all documents of the specified type
     *
     * @param tClass Type of documents to be received
     * @param <T>    Generic type extends Document class
     * @return List of documents
     * @throws Exception Data access error
     */
    <T extends Document> List<T> getAllDocuments(Class<T> tClass) throws Exception;

    /**
     * Returns the document by ID
     *
     * @param id Document ID
     * @return Document
     * @throws Exception Data access error
     */
    Document getById(Long id) throws Exception;

    /**
     * Returns personnel documents related to the specified employee
     *
     * @param employee Employee name
     * @param tClass   Type of documents to be received
     * @param <T>      Generic type extends PersonnelDocument class
     * @return List of personnel documents
     * @throws Exception Data access error
     */
    <T extends PersonnelDocument> List<T> getAllByEmployee(String employee, Class<T> tClass) throws Exception;

    /**
     * Returns personnel documents with the specified status
     *
     * @param status Status of document
     * @param tClass Type of documents to be received
     * @param <T>    Generic type extends PersonnelDocument class
     * @return List of personnel documents
     * @throws Exception Data access error
     */
    <T extends PersonnelDocument> List<T> getAllByStatus(Status status, Class<T> tClass) throws Exception;

    /**
     * Returns document by number
     *
     * @param number Document number
     * @param tClass Type of documents to be received
     * @param <T>    Generic type extends Document class
     * @return Document
     * @throws Exception Data access error
     */
    <T extends Document> T getByNumber(int number, Class<T> tClass) throws Exception;

    /**
     * Returns mails by email
     *
     * @param email Email sender or receiver
     * @return List of mails
     * @throws Exception Data access error
     */
    List<Mail> getAllByEmail(String email) throws Exception;

    /**
     * Saving or updating a document
     *
     * @param document New or modified document
     * @param <T>      Generic type extends Document class
     * @return Document
     * @throws Exception Data access error
     */
    <T extends Document> T save(T document) throws Exception;

    /**
     * Saving or updating documents
     *
     * @param documents List of new or modified documents
     * @param <T>       Generic type extends Document class
     * @return List of document
     * @throws Exception Data access error
     */
    <T extends Document> List<T> save(List<T> documents) throws Exception;

    /**
     * Delete the document
     *
     * @param <T> Generic type extends Document class
     * @throws Exception Data access error
     */
    <T extends Document> void delete(T document) throws Exception;
}
