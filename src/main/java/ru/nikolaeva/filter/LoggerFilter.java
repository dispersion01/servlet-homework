package ru.nikolaeva.filter;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoggerFilter extends HttpFilter{
@Override

    protected void doFilter(HttpServletRequest req, HttpServletResponse res, Filter chain) throws IOException, ServletException {
    System.out.println("request started");
    super.doFilter(req, res, chain);
    System.out.println("request finished");
}

}
