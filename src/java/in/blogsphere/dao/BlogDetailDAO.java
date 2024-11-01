/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.blogsphere.dao;

import in.blogsphere.pojo.Blog;
import in.blogsphere.pojo.BlogDetail;
import in.blogsphere.pojo.Comment;
import java.util.List;

/**
 *
 * @author Prasant
 */
public class BlogDetailDAO {
    
    public static BlogDetail get(String id) {
        Blog blog = BlogDAO.get(id);
        List<Comment> comments = CommentDAO.getAll(id);
        
        return (blog == null)? null : new BlogDetail(blog, comments);
    }
    
}
