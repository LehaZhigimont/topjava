package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    private UserRepository repositoryUser;


    public void init() {
        repositoryUser = new InMemoryUserRepository();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPOST");
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        switch (action == null ? "all" : action) {
            case "login":
                System.out.println("case login");
                if (repositoryUser.getByEmail(getByEmail(request)) != null) {
                    final User user = repositoryUser.getByEmail(getByEmail(request));
                    SecurityUtil.loginUser(user.getId());
                    request.setAttribute("users", repositoryUser.getAll());
                    response.sendRedirect("meals");
                    break;
                }
            case "logout":
                System.out.println("case logout");
                SecurityUtil.loginUser(-1);
                response.sendRedirect("index.html");
                break;
            case "all":
            default:
                System.out.println("case default");
                User user = new User(id.isEmpty() ? null : Integer.valueOf(id), (request.getParameter("name")),
                        request.getParameter("email"), request.getParameter("password"),
                        Integer.parseInt(request.getParameter("calories")), true, roles);
                log.info(user.isNew() ? "Create {}" : "Update {}", user);
                repositoryUser.save(user);
                response.sendRedirect("users");
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to users");
        String action = request.getParameter("action");
        System.out.println("This doPost action - " + action);
        switch (action == null ? "all" : action) {
            case "login":
                System.out.println("doGET - login");
                if (repositoryUser.getByEmail(getByEmail(request)) != null) {
                    request.setAttribute("users", repositoryUser.getAll());
                    request.getRequestDispatcher("/users.jsp").forward(request, response);
                    break;
                }
                request.setAttribute("users", repositoryUser.getAll());
                request.getRequestDispatcher("/users.jsp").forward(request, response);
            case "delete":
                int id = getId(request);
                log.info("Delete id={}", id);
                repositoryUser.delete(id);
                response.sendRedirect("users");
                break;
            case "create":
            case "update":
                final User user = "create".equals(action) ?
                        new User(null, "Name", "Email", "pass", 2000, true, null) :
                        repositoryUser.get(getId(request));
                request.setAttribute("user", user);
                request.getRequestDispatcher("/userForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("users", repositoryUser.getAll());
                request.getRequestDispatcher("/users.jsp").forward(request, response);
                break;
        }

    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    private String getByEmail(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("email"));
        return paramId;
    }

}