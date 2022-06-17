package webproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webproject.model.Classes.DismissalOrder;
import webproject.model.Classes.EmploymentOrder;
import webproject.model.Classes.Mail;
import webproject.model.Interfaces.Document;
import webproject.model.Interfaces.Status;
import webproject.services.DocumentService;
import webproject.services.StatisticService;
import webproject.utils.DocumentServiceFactory;
import webproject.utils.StatisticServiceFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Controller
public class DocumentsController {
    private final StatisticService statisticService = StatisticServiceFactory.getStatisticService();
    private final DocumentService documentService = DocumentServiceFactory.getDocumentService();

    @GetMapping
    public String getStartPage(Model model) {
        try {
            int countDocuments = statisticService.getCount(Document.class).intValue();
            Map<String, Long> statistic = statisticService.getFullStatistic();
            model.addAttribute("count", countDocuments);
            model.addAttribute("statistic", statistic);
            return "index";
        } catch (Exception e) {
            return getErrorPage(e.getMessage(), model);
        }
    }

    @GetMapping("/mails")
    public String getMailsPage(Model model) {
        try {
            int countMails = statisticService.getCount(Mail.class).intValue();
            List<Mail> mails = documentService.getAllDocuments(Mail.class);
            model.addAttribute("count", countMails);
            model.addAttribute("mail", new Mail(null, null, null, null));
            model.addAttribute("mails", mails);
            return "mails";
        } catch (Exception e) {
            return getErrorPage(e.getMessage(), model);
        }
    }

    @PostMapping("/mails")
    public String addMail(@ModelAttribute Mail mail, Model model) {
        try {
            documentService.save(mail);
            return getMailsPage(model);
        } catch (Exception e) {
            return getErrorPage(e.getMessage(), model);
        }
    }

    @GetMapping("/employments")
    public String getEmploymentsPage(Model model) {
        try {
            int countEmployments = statisticService.getCount(EmploymentOrder.class).intValue();
            List<EmploymentOrder> employmentOrders = documentService.getAllDocuments(EmploymentOrder.class);
            Map<Integer, Long> statistic = statisticService.getStatusStatistic(EmploymentOrder.class);
            model.addAttribute("count", countEmployments);
            model.addAttribute("statistic", statistic);
            model.addAttribute("employment", new EmploymentOrder(null, null, null));
            model.addAttribute("employments", employmentOrders);
            return "employments";
        } catch (Exception e) {
            return getErrorPage(e.getMessage(), model);
        }
    }

    @PostMapping("/employments")
    public String addEmployment(@ModelAttribute EmploymentOrder employment, Model model) {
        try {
            documentService.save(employment);
            return getEmploymentsPage(model);
        } catch (Exception e) {
            return getErrorPage(e.getMessage(), model);
        }
    }

    @PostMapping("/employments-exec")
    public String addEmployment(@RequestParam(value = "number") Integer number, Model model) {
        try {
            EmploymentOrder employmentOrder = documentService.getByNumber(number, EmploymentOrder.class);
            employmentOrder.execute();
            documentService.save(employmentOrder);
            Field field = EmploymentOrder.class.getDeclaredField("count");
            field.setAccessible(true);
            field.set(null, (int)field.get(null) - 1);
            return getEmploymentsPage(model);
        } catch (Exception e) {
            return getErrorPage(e.getMessage(), model);
        }
    }

    @GetMapping("/dismissals")
    public String getDismissalPage(Model model) {
        try {
            int countDismissals = statisticService.getCount(EmploymentOrder.class).intValue();
            List<DismissalOrder> dismissalOrders = documentService.getAllDocuments(DismissalOrder.class);
            Map<Integer, Long> statistic = statisticService.getStatusStatistic(DismissalOrder.class);
            model.addAttribute("count", countDismissals);
            model.addAttribute("statistic", statistic);
            model.addAttribute("dismissal", new DismissalOrder(null, null, null, null));
            model.addAttribute("dismissals", dismissalOrders);
            return "dismissals";
        } catch (Exception e) {
            return getErrorPage(e.getMessage(), model);
        }
    }

    @PostMapping("/dismissals")
    public String addDismissal(@ModelAttribute DismissalOrder dismissal, Model model) {
        try {
            documentService.save(dismissal);
            return getDismissalPage(model);
        } catch (Exception e) {
            return getErrorPage(e.getMessage(), model);
        }
    }

    @PostMapping("/dismissals-exec")
    public String addDismissal(@RequestParam(value = "number") Integer number, Model model) {
        try {
            DismissalOrder dismissalOrder = documentService.getByNumber(number, DismissalOrder.class);
            dismissalOrder.execute();
            documentService.save(dismissalOrder);
            Field field = DismissalOrder.class.getDeclaredField("count");
            field.setAccessible(true);
            field.set(null, (int)field.get(null) - 1);
            return getDismissalPage(model);
        } catch (Exception e) {
            return getErrorPage(e.getMessage(), model);
        }
    }

    @GetMapping("/error")
    public String getErrorPage(String message, Model model) {
        model.addAttribute("message", message);
        return "error";
    }
}