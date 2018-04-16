package by.kastsiuchenka.skillmatrix.web.controller;


import by.kastsiuchenka.skillmatrix.web.command.Command;
import by.kastsiuchenka.skillmatrix.web.command.CommandType;
import by.kastsiuchenka.skillmatrix.web.handler.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/frontController")
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CommandType commandType = RequestHandler.getCommand(req);
        Command command = commandType.getCommand();
        String page = command.execute(req, resp);
        if (commandType.getPageName().equals("start")) {
            getServletContext().getRequestDispatcher("/jsp/start.jsp").forward(req, resp);
        }else{

        }

//
    }

}
