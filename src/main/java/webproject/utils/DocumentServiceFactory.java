package webproject.utils;

import webproject.services.DocumentService;
import webproject.services.DocumentServiceImplDao;
import webproject.services.StatisticServiceImplDao;

public class DocumentServiceFactory {
    private static final DocumentService documentService = buildDocumentServiceFactory();

    protected static DocumentService buildDocumentServiceFactory() {
        try {
            return new DocumentServiceImplDao();
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Initial DocumentServiceFactory failed" + e);
        }
    }

    public static DocumentService getDocumentService() {
        return documentService;
    }
}
