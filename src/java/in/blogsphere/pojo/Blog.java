package in.blogsphere.pojo;

import java.util.Date;

public class Blog {

    private String id, email, title, content, category, username;
    private int likes, comments, views;
    private Date date;
    private long ms;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getMillisecond() {
        return ms;
    }

    public void setMillisecond(long ms) {
        this.ms = ms;
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", email=" + email + ", title=" + title + ", content=" + content + ", category=" + category + ", username=" + username + ", likes=" + likes + ", comments=" + comments + ", views=" + views + ", date=" + date + ", ms=" + ms + '}';
    }

}
