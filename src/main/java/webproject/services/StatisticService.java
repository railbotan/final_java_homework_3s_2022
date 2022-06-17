package webproject.services;

import webproject.model.Interfaces.Document;
import webproject.model.Interfaces.PersonnelDocument;
import webproject.model.Interfaces.Status;

import java.util.Map;

public interface StatisticService {
    /**
     * Returns statistics on the count of instances of different types of documents
     *
     * @return Types of documents with the count of instance
     * @throws Exception Data access error
     */
    Map<String, Long> getFullStatistic() throws Exception;

    /**
     * Returns statistics on the count of status of personnel documents
     *
     * @param tClass Type of documents to be received
     * @param <T>    Generic type extends PersonnelDocument class
     * @return Status of documents with the count of instance
     * @throws Exception Data access error
     */
    <T extends PersonnelDocument> Map<Integer, Long> getStatusStatistic(Class<T> tClass) throws Exception;

    /**
     * Returns count of all documents of the specified type
     *
     * @param tClass Type of documents to be received
     * @param <T>    Generic type extends Document class
     * @return Count of documents
     * @throws Exception Data access error
     */
    <T extends Document> Long getCount(Class<T> tClass) throws Exception;

    /**
     * Returns count of personnel documents with the specified status
     *
     * @param status Status of document
     * @param tClass Type of documents to be received
     * @param <T>    Generic type extends PersonnelDocument class
     * @return Count of personnel documents
     * @throws Exception Data access error
     */
    <T extends PersonnelDocument> Long getCountByStatus(Status status, Class<T> tClass) throws Exception;
}
