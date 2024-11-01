/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.blogsphere.pojo;

import java.util.List;

/**
 *
 * @author Prasant
 */
public class Profile {
    private User user;
    private List<Blog> blogs;

    public Profile(User user, List<Blog> blogs) {
        this.user = user;
        this.blogs = blogs;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
    
}
