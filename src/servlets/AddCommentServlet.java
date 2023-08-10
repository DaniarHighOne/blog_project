package servlets;

import db.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

import java.io.IOException;


@WebServlet(value="/add-comment")
public class AddCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //need user comment for special blog
        String description = req.getParameter("description");
        Long blogId = Long.parseLong(req.getParameter("blog_id"));
        User user = (User) req.getSession().getAttribute("currentUser");
        DBUtil.addComments(description,blogId,user.getId());
        resp.sendRedirect("/blog-details?id="+blogId);

    }
}
