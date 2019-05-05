package ru.nikolaeva.servlet;

import ru.nikolaeva.service.AutoService;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CatalogServlet extends HttpServlet {
    private AutoService service;

    @Override
    public void init() throws SecurityException {
        try {
            var context = new InitialContext();
            service = (AutoService) context.lookup("java:/comp/env/bean/auto-service");

        } catch (NamingException e ) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException, ServletException {
        if (req.getPathInfo() != null) {
            String[] split = req.getPathInfo().split("/");
            if (split.length == 2) {
                var id = split[1];
                var auto = service.getById(id);
                req.setAttribute("item", auto);
                req.getRequestDispatcher("/WEB-INF/details.jsp").forward(req,resp);
                return;
            }
        }
        var q = req.getParameter("q");
        if (q != null) {
            var list = service.findByName(q);
            req.setAttribute("items", list);
            req.getRequestDispatcher("/WEB-INF/catalog.jsp").forward(req, resp);
            return;
        }
        var list = service.getAll();
        req.setAttribute("items", list);
        req.getRequestDispatcher("/WEB-INF/catalog.jsp").forward(req, resp);
    }
    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException, ServletException {
        var file = req.getPart("file");
        var name = req.getParameter("name");
        var description = req.getParameter("description");
        service.create(name,description,file);
    }

}
