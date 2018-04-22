package by.kastsiuchenka.skillmatrix.web.command.impl;


import by.kastsiuchenka.skillmatrix.service.ServiceException;
import by.kastsiuchenka.skillmatrix.service.SkillService;
import by.kastsiuchenka.skillmatrix.service.impl.SkillServiceImpl;
import by.kastsiuchenka.skillmatrix.web.command.Command;
import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateCommand implements Command {
    private static org.apache.logging.log4j.Logger Logger = LogManager.getLogger(UpdateCommand.class);
    private SkillService skillService = SkillServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            System.out.println(req.getParameter("adr"));
            System.out.println(req.getParameter("data"));
            skillService.update(req.getParameter("adr"),req.getParameter("data"), SOURCE_XLSX_FILE);
            String outputJSONPath = req.getServletContext().getRealPath("/")+OUTPUT_JSON_FILE;
            skillService.createJSON(SOURCE_XLSX_FILE, outputJSONPath);

        } catch (ServiceException e) {
            Logger.info(e.getMessage(), e);
            req.getSession().setAttribute("errorMsg", e);
        }
        return null;
    }

}
