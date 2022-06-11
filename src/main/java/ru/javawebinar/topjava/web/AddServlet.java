package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class AddServlet extends HttpServlet {
    private static final Logger log = getLogger(AddServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to add");
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if ("submit".equals(action)) {
            if (request.getParameter("datetime") != null
                    && !request.getParameter("description").isEmpty()
                    && !request.getParameter("calories").isEmpty()) {
                LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("datetime"));
                Meal meal = new Meal(dateTime, request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));
            }
        }
        request.setAttribute("mealsTo", MealsUtil.createdListMeals());
        response.sendRedirect(request.getContextPath() + "/meals");
    }
}
