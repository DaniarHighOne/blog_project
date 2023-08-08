package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

import java.io.IOException;


@WebServlet(value="/sign-in")
public class SignInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        var user = (User) req.getSession().getAttribute("currentUser");
        if (user == null) {
            req.getRequestDispatcher("sign-in.jsp").forward(req,resp);
        }
        resp.sendRedirect("/");

    }
}

