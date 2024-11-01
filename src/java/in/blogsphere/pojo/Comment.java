/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.blogsphere.pojo;

import java.util.Date;

/**
 *
 * @author Prasant
 */
public class Comment {

    private String blogID, userID, content, username;
    private Date date;
    private long ms;

    public String getBlogID() {
        return blogID;
    }

    public void setBlogID(String blogID) {
        this.blogID = blogID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getMillisecond() {
        return ms;
    }

    public void setMillisecond(long ms) {
        this.ms = ms;
    }

    @Override
    public String toString() {
        return "Comment{" + "blogID=" + blogID + ", userID=" + userID + ", content=" + content + ", date=" + date + '}';
    }

}
