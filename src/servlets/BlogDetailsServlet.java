package servlets;

import db.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

import java.io.IOException;

@WebServlet(value="blog-details")
public class BlogDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        User user = (User) req.getSession().getAttribute("currentUser");
        if (user != null){
            req.setAttribute("blog", DBUtil.getBlogById(id));
            req.setAttribute("users", DBUtil.getUsers());
            req.setAttribute("comments", DBUtil.getCommentsById(id));
            req.getRequestDispatcher("blog-details.jsp").forward(req,resp);
        }
        resp.sendRedirect("/sign-in");


    }
}
