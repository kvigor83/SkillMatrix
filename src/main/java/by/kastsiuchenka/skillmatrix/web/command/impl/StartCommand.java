package by.kastsiuchenka.skillmatrix.web.command.impl;


import by.kastsiuchenka.skillmatrix.parser.XLSParser;
import by.kastsiuchenka.skillmatrix.service.ServiceException;
import by.kastsiuchenka.skillmatrix.service.SkillService;
import by.kastsiuchenka.skillmatrix.service.impl.SkillServiceImpl;
import by.kastsiuchenka.skillmatrix.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StartCommand implements Command {
    private static Logger Logger = LogManager.getLogger(StartCommand.class);
    private SkillService skillService = SkillServiceImpl.getInstance();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String outputJSONPath = req.getServletContext().getRealPath("/")+OUTPUT_JSON_FILE;
        try {
            skillService.createJSON(SOURCE_XLSX_FILE, outputJSONPath);
        } catch (ServiceException e) {
            Logger.info(e.getMessage(), e);
//            req.getSession().setAttribute("errorMsg",e);
        }

        return null;
    }
}
