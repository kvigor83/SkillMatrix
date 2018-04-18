package by.kastsiuchenka.skillmatrix.web.command.impl;



import by.kastsiuchenka.skillmatrix.service.ServiceException;
import by.kastsiuchenka.skillmatrix.service.SkillService;
import by.kastsiuchenka.skillmatrix.service.impl.SkillServiceImpl;
import by.kastsiuchenka.skillmatrix.web.command.Command;
import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertCommand implements Command {
    private static org.apache.logging.log4j.Logger Logger = LogManager.getLogger(UpdateCommand.class);
    private SkillService skillService = SkillServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            skillService.create(req.getParameter("adr") ,req.getParameter("data") ,SOURCE_XLSX_FILE);


        } catch (ServiceException e) {
            Logger.info(e.getMessage(), e);
            req.getSession().setAttribute("errorMsg", e);
        }
        return null;
    }
}
