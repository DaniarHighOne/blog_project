package db;

import models.Blog;
import models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/final_project",
                    "postgres",
                    "postgres"
            );

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //we need get all blogs, means we get List of them

    public static List<Blog> getBlog(){
        List<Blog> blogs  = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select b.id, b.title, b.content, b.post_date, b.user_d, "+
                            " u.age, u.full_name from blogs b "
                    + "inner join users u on u.id = b.user_id ");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Blog blog = new Blog();
                blog.setId(resultSet.getLong("id"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setPostDate(resultSet.getDate("post_date"));

                //need attach user
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                blog.setUser(user);
                blogs.add(blog);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return blogs;
    }

    public static User getUserByMailPass(String email,String password) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from users where email = ? and password = ? ");

            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(email);
                user.setPassword(password);
                user.setFullName(resultSet.getString("full_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } return user;
    }
}
