package servlets;

import db.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

import java.io.IOException;

@WebServlet(value="/auth")
public class AuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password  = req.getParameter("password");
        User user = DBUtil.getUserByMailPass(email,password);
        req.getSession().setAttribute("currentUser",user);
        String redirect = "sign-in?error=1";
        if(user != null) {
            redirect = "/";
        }
        resp.sendRedirect(redirect);
    }
}
