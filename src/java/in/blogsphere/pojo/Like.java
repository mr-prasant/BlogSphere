/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.blogsphere.pojo;

/**
 *
 * @author Prasant
 */
public class Like {

    private String blogID, userID;

    public Like(String blogID, String userID) {
        this.blogID = blogID;
        this.userID = userID;
    }

    public void setBlogID(String blogID) {
        this.blogID = blogID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getBlogID() {
        return blogID;
    }

    public String getUserID() {
        return userID;
    }

    @Override
    public String toString() {
        return "Like{" + "blogID=" + blogID + ", userID=" + userID + '}';
    }

}
