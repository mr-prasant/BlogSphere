/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package in.blogsphere.listener;

import in.blogsphere.util.DBUtil;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Prasant
 */
public class DBConnectionListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ct = sce.getServletContext();
        
        String dbUrl = ct.getInitParameter("url");
        String username = ct.getInitParameter("username");
        String password = ct.getInitParameter("password");
        
        DBUtil.openConnection(dbUrl, username, password);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DBUtil.closeConnection();
    }
    
}
