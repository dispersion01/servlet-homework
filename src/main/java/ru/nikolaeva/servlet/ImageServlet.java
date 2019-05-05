package ru.nikolaeva.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() != null) {
            String[] split = req.getPathInfo().split("/");
            if (split.length != 2) {
                throw new RuntimeException("are you kidding me?");
            }
            var id = split[1];

            var path = Paths.get("C:/Users/Anyta/IdeaProjects/04 Servlet Servlet Nikolaeva/upload").resolve(id);
            if (!Files.exists(path)) {
                throw new RuntimeException("404");
            }

//      resp.setHeader("Content-Type", "text/plain");
            Files.copy(path, resp.getOutputStream());

//      resp.setHeader("Content-Type", "application/octet-stream");
//      resp.setHeader("Content-Disposition", "Attachment; filename=exported.csv");
//      Files.copy(path, resp.getOutputStream());
        }
    }
}
