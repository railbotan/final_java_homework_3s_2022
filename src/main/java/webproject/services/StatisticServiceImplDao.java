package webproject.services;

import webproject.dao.DocumentsDAO;
import webproject.model.Classes.DismissalOrder;
import webproject.model.Classes.EmploymentOrder;
import webproject.model.Classes.Mail;
import webproject.model.Interfaces.Document;
import webproject.model.Interfaces.PersonnelDocument;
import webproject.model.Interfaces.Status;
import webproject.utils.EntityManagerFactory;

import java.util.HashMap;
import java.util.Map;

public class StatisticServiceImplDao implements StatisticService {
    private final DocumentsDAO entityManager = EntityManagerFactory.getEntityManager();

    @Override
    public Map<String, Long> getFullStatistic() throws Exception {
        Map<String, Long> statistic = new HashMap<String, Long>();
        statistic.put("mails", entityManager.getCountAll(Mail.class));
        statistic.put("employments", entityManager.getCountAll(EmploymentOrder.class));
        statistic.put("dismissals", entityManager.getCountAll(DismissalOrder.class));
        return statistic;
    }

    @Override
    public <T extends PersonnelDocument> Map<Integer, Long> getStatusStatistic(Class<T> tClass) throws Exception {
        Map<Integer, Long> statistic = new HashMap<Integer, Long>();
        statistic.put(Status.CREATED.ordinal(), entityManager.getCountByStatus(Status.CREATED, tClass));
        statistic.put(Status.EXECUTED.ordinal(), entityManager.getCountByStatus(Status.EXECUTED, tClass));
        return statistic;
    }

    @Override
    public <T extends Document> Long getCount(Class<T> tClass) throws Exception {
        return entityManager.getCountAll(tClass);
    }

    @Override
    public <T extends PersonnelDocument> Long getCountByStatus(Status status, Class<T> tClass) throws Exception {
        return entityManager.getCountByStatus(status, tClass);
    }
}
