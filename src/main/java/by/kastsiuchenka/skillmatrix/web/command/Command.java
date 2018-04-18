package by.kastsiuchenka.skillmatrix.web.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    String SOURCE_XLSX_FILE = "SkillMatrix.xlsx";
    String OUTPUT_JSON_FILE = "skills.json";
    String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;
}
