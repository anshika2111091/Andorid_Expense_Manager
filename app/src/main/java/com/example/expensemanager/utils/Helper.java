package com.example.expensemanager.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
    public static String formatDate(Date date){
        SimpleDateFormat dateformat=new SimpleDateFormat("dd MMMM, yyyy");
        return dateformat.format(date);
    }
    public static String formatDateByMonth(Date date){
        SimpleDateFormat dateformat=new SimpleDateFormat(" MMMM, yyyy");
        return dateformat.format(date);
    }

}
