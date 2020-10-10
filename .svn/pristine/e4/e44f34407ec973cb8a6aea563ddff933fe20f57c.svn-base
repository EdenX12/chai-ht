package com.tian.sakura.cdd.common.util;

import org.joda.time.DateTime;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 *
 * @author lvzonggang
 */
public class DateUtils {
	

    private static final String PATTERN_YYMMDD = "yyMMdd";
    private static final String PATTERN_YYYYMMDD = "yyyyMMdd";
    private static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    private static final String PATTERN_YYYY_MM_DD_H24_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private static final String PATTERN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";


    public static String formatDate(Date date, String pattern) {
        return new DateTime(date.getTime()).toString(pattern);
    }

    public static String formatDateWithyyMMdd(Date date) {
        return formatDate(date,PATTERN_YYMMDD);
    }

    public static String formatDateWithyyyyMMdd(Date date) {
        return formatDate(date,PATTERN_YYYYMMDD);
    }

    public static String formatDateWithyyyy_MM_dd(Date date) {
        return formatDate(date,PATTERN_YYYY_MM_DD);
    }

    public static String formatDateWithyyyy_MM_dd_hms(Date date) {
        return formatDate(date,PATTERN_YYYY_MM_DD_H24_MM_SS);
    }

    public static String formatDateWithyyyyMMddhms(Date date) {
        return formatDate(date,PATTERN_YYYYMMDDHHMMSS);
    }

    public static Date parseDate(String value) {
        DateTime dateTime = new DateTime(value);
        return dateTime.toDate();
    }
    
    public static Date parseStrToDate(String dateStr) throws ParseException {
    	SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_YYYY_MM_DD);
		return sdf.parse(dateStr);
    }

    public static Date parseStrToDate1(String dateStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    
    public static Date parseStrToDate1(String dateStr) {
    	return parseStrToDate1(dateStr,PATTERN_YYYY_MM_DD);
    }

    public static Date getDate(Date date1, String dateType, int dateValue) {
        Date date = date1;
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if ("DATE".equals(dateType)) {
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + dateValue);
        } else if ("MONTH".equals(dateType)) {
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + dateValue);
        } else if ("YEAR".equals(dateType)) {
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + dateValue);
        }

        return getSimpleDate(calendar.getTime());
    }

    public static Date getSimpleDate(Date date1) {
        Date date = date1;
        if (date == null) {
            date = new Date();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
    
    private static DatatypeFactory df = null;
	static {
		try {
			df = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException dce) {
			throw new IllegalStateException(
					"Exception while obtaining DatatypeFactory instance", dce);
		}
	}
    
    public static XMLGregorianCalendar DateToXMLGCalendar(Date date) {
		if (date == null) {
			return null;
		} else {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTimeInMillis(date.getTime());
			return df.newXMLGregorianCalendar(gc);
		}
	}
    
    public static Date XMLGCalendarToDate(XMLGregorianCalendar xgc) {
		if (xgc == null) {
			return null;
		} else {
			return xgc.toGregorianCalendar().getTime();
		}

	}
    
    /**
     * 功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate;
        Date endDate;
        try{
            beginDate = format.parse(beginDateStr);
            endDate= format.parse(endDateStr);    
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            if(day<0){
            	day=0;
            }
        } catch (ParseException e){

        }   
        return day;
    }
    
    /**
     * 功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     */
    public static long getDaySubDate(Date beginDateStr,Date endDateStr){
        long day=0;
        try{
            day=(endDateStr.getTime()-beginDateStr.getTime())/(24*60*60*1000);
            if(day<0){
            	day=0;
            }
        } catch (Exception e){

        }   
        return day;
    }
    public static String getDateToCnDate(Date date){
    	String dateTime = DateUtils.formatDateWithyyyyMMdd(date);
    	return dateTime.substring(0,4)+"年"+dateTime.substring(4,6)+"月"+dateTime.substring(6,8)+"日";
    }
}
