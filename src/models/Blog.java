package models;

import java.sql.Date;

public class Blog {
    private Long id;
    private String title;
    private String content;
    private Date postDate;
    private User user;

    public Blog(){}

    public Blog(Long id, String title, String content, Date postDate, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.postDate = postDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
