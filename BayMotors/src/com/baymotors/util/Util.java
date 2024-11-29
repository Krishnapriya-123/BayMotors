package com.baymotors.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Util {
	public static String formatDate(Date date) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    return sdf.format(date);
	}
	
}
