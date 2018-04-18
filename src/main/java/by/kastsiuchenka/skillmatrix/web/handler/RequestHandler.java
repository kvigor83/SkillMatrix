package by.kastsiuchenka.skillmatrix.web.handler;

import by.kastsiuchenka.skillmatrix.web.command.CommandType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//Command Factory (client)
public class RequestHandler {
    public static CommandType getCommand(HttpServletRequest req) {
        String param = req.getParameter("command");
        if (param == null && "".equals(param)) {
            param = "start";
        }
        CommandType type = CommandType.getByCommandName(param);

        return type;
    }
}
