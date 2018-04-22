package by.kastsiuchenka.skillmatrix.web.controller;


import by.kastsiuchenka.skillmatrix.web.command.Command;
import by.kastsiuchenka.skillmatrix.web.command.CommandType;
import by.kastsiuchenka.skillmatrix.web.handler.RequestHandler;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@WebServlet(urlPatterns = "/frontController")
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommandType commandType = RequestHandler.getCommand(req);
        Command command = commandType.getCommand();
        command.execute(req, resp);
        if (commandType.getPageName().equals("start")) {
            getServletContext().getRequestDispatcher("/jsp/start.jsp").forward(req, resp);
        }
//        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommandType commandType = CommandType.getByCommandName("updateData");
        Command command = commandType.getCommand();
        command.execute(req, resp);
    }

}
