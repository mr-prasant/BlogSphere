/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.blogsphere.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Prasant
 */
public class FormatUtil {

    public static String count(int n) {
        if (n >= 1000000) {
            return ((int) (n / 1000000)) + "M";
        }

        if (n >= 1000) {
            return ((int) (n / 1000)) + "K";
        }

        return String.valueOf(n);
    }

    public static String date(Date date) {
        if (date == null) {
            return "Invalid Data";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        return sdf.format(date);
    }

    public static String time(long ms) {
        long s = (System.currentTimeMillis() - ms) / 1000;
        int spd = 86400; // seconds per day

        if (s >= spd * 365) {
            int years = (int) (s / (spd * 365));
            return years + " year" + (years > 1 ? "s" : "");
        }

        if (s >= spd * 30) {
            int months = (int) (s / (spd * 30));
            return months + " month" + (months > 1 ? "s" : "");
        }

        if (s >= spd * 7) {
            int weeks = (int) (s / (spd * 7));
            return weeks + " week" + (weeks > 1 ? "s" : "");
        }

        if (s >= spd) {
            int days = (int) (s / spd);
            return days + " day" + (days > 1 ? "s" : "");
        }

        if (s >= 3600) {
            int hrs = (int) (s / 3600);
            return hrs + " hr" + (hrs > 1 ? "s" : "");
        }

        if (s >= 60) {
            int mins = (int) (s / 60);
            return mins + " min" + (mins > 1 ? "s" : "");
        }

        return s + " sec";
    }
    
    public static String paragraph(String para) {
        
        return para.substring(0, Math.min(80, para.length()))+"...";
    }

}
