/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.blogsphere.util;

import java.net.Authenticator;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.Random;
import javax.websocket.Session;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Prasant
 */
public class GenerateUtil {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static SecureRandom random;

    private static String generator() {
        StringBuilder sb = new StringBuilder(String.valueOf(System.currentTimeMillis()));
        random = new SecureRandom();

        for (int i = 0; i < 4; i++) {
            int ri = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(ri));
        }

        return sb.toString();
    }

    public static String generate(char prefix) {
        return prefix + generator();
    }

    public static String generateOTP() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    public static void sendOTP(String email, String otp) {
        final String fromEmail = "prasantcp7@gmail.com"; // your email
        final String password = "Prasant@250701"; // your email password

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

    }

}
