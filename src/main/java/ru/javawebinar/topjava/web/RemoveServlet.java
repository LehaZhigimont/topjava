package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class RemoveServlet extends HttpServlet {
    private static final Logger log = getLogger(RemoveServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to Remove");
        request.setAttribute("meal", MealsUtil.createdListMeals().get(1));
        request.getRequestDispatcher("/remove.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //TO-DO
        response.sendRedirect(request.getContextPath() + "/meals");
    }
}
