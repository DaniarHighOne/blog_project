package db;

import models.Blog;
import models.BlogComments;
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

    public static Blog getBlogById(Long id) {
        Blog blog = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select b.id, b.title, b.content, b.post_date, "
                    +"b.user_id, u.full_name, u.age, u.email from blogs b "
                    +"inner join users u on b.user_id=u.id "
                    +"where b.id= ? ");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                blog = new Blog();
                blog.setId(resultSet.getLong("id"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setPostDate(resultSet.getDate("post_date"));

                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setEmail(resultSet.getString("email"));
                user.setFullName(resultSet.getString("full_name"));
                user.setAge(resultSet.getInt("age"));
                blog.setUser(user);
            }
            statement.close();

        } catch (Exception e){
            e.printStackTrace();
        } return blog;
    }

    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from users ");
            ResultSet  resultSet = statement.executeQuery();
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setAge(resultSet.getInt("age"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }

        } catch (Exception e){
            e.printStackTrace();
        } return users;
    }

    public static List<BlogComments> getCommentsById(Long blogId) {
        List<BlogComments> comments = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select c.id, c.description, c.post_date, c.user_id, c.blog_id "
                    +"u.full_name, u.age "
                    +"inner join blogs b on b.id=c.blog_id "
                    +"inner join users u on u.id = c.user_id "
                    +"where b.id = ? ");
            statement.setLong(1, blogId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                BlogComments comment = new BlogComments();
                comment.setId(resultSet.getLong("id"));
                comment.setDescription(resultSet.getString("description"));
                comment.setPostDate(resultSet.getDate("post_date"));

                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setAge(resultSet.getInt("age"));
                comment.setUser(user);
                comments.add(comment);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

    public static void addComments(String description, Long blogId, Long userId){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into comments(description, post_date, blod_id, user_id) "
                    +"values (?, now(), ?, ?) ");
            statement.setString(1, description);
            statement.setLong(2, blogId);
            statement.setLong(3, userId);
            statement.executeUpdate();
            statement.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editBlog(Long blogId, String title, String content, Long userId){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "update blogs "
                    +"set title = ?, content = ?, user_id = ? "
                    +"where id = ? ");
            statement.setString(1, title);
            statement.setString(2, content);
            statement.setLong(3, userId);
            statement.setLong(4, blogId);
            statement.executeUpdate();
            statement.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
