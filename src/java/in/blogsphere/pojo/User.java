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
public class User {

    private String email, name;
    private long contact;
    private String password;

    public User(String email, String name, long contact) {
        this.email = email;
        this.name = name;
        this.contact = contact;
    }

    public User() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email.toLowerCase().trim();
    }

    public String getName() {
        return name;
    }

    public long getContact() {
        return contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", name=" + name + ", contact=" + contact + '}';
    }
}
