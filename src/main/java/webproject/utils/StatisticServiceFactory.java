package webproject.utils;

import webproject.services.StatisticService;
import webproject.services.StatisticServiceImplDao;

public class StatisticServiceFactory {
    private static final StatisticService statisticService = buildStatisticServiceFactory();

    protected static StatisticService buildStatisticServiceFactory() {
        try {
            return new StatisticServiceImplDao();
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Initial StatisticServiceFactory failed" + e);
        }
    }

    public static StatisticService getStatisticService() {
        return statisticService;
    }
}
