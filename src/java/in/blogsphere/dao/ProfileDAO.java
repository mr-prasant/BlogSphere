/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.blogsphere.dao;

import in.blogsphere.pojo.Blog;
import in.blogsphere.pojo.Profile;
import in.blogsphere.pojo.User;
import java.util.List;

/**
 *
 * @author Prasant
 */
public class ProfileDAO {
    
    public static Profile get(String uid) {
        User user = UserDAO.get(uid);
        
        if (user == null) return null;
        
        List<Blog> blogs = BlogDAO.getBlogsOf(uid);
        
        return new Profile(user, blogs);
    }
    
}
