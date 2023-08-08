package models;

import java.sql.Date;

public class BlogComments {
    private Long id;
    private String description;
    private Date postDate;
    private User user;
    private Blog blog;

    public BlogComments(){}

    public BlogComments(Long id, String description, Date postDate, User user, Blog blog) {
        this.id = id;
        this.description = description;
        this.postDate = postDate;
        this.user = user;
        this.blog = blog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
