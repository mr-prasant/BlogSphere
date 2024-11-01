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
public class BlogDetail {
    private Blog blog;
    private List<Comment> comments;

    public BlogDetail(Blog blog, List<Comment> comments) {
        this.blog = blog;
        this.comments = comments;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
