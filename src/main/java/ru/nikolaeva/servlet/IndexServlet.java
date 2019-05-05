package ru.nikolaeva.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;

public class IndexServlet extends HttpServlet {
    @Override
    protected void service (HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
        resp.getOutputStream().write("ok".getBytes());

    }
}
