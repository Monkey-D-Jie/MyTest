package com.jf.mydemo.mytest.Utils.date;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
    private static final int WEEK = 7;
    private static final long MILLISECONDS_PER_DAY = 24L * 3600 * 1000;
    /**
     * 格式化字符串，年月日时分秒
     */
    public final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式化字符串，年月日
     */
    public final static String DATE_FORMAT_ONE = "yyyy-MM-dd";
    /**
     * 格式化字符串，时分秒
     */
    public final static String DATE_FORMAT_TWO = "HH:mm:ss";
    /**
     * 格式化字符串，月日
     */
    public final static String DATE_FORMAT_THREE = "MM-dd";
    /**一周的毫秒数*/
    public static final int MILLISECONDS_OF_WEEK = 7 * 24 * 60 * 60 * 1000;

    /**
     * 获取两个日期之间的周数。周一作为一周的起始
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 周数
     */
    public static int getWeeks(Date startDate, Date endDate) {
        if(StringUtils.isEmpty(startDate)&&StringUtils.isEmpty(endDate)){
            throw new RuntimeException("入参不能为空");
        }
        Calendar calStart = Calendar.getInstance();
        Calendar calEnd = Calendar.getInstance();
        calStart.setTime(startDate);
        calEnd.setTime(endDate);
        if (endDate.before(startDate)) {
            calStart.setTime(endDate);
            calEnd.setTime(startDate);
        }
        int dayStart=calStart.get(Calendar.DAY_OF_WEEK);
        // SUNDAY--1
        if (dayStart < Calendar.MONDAY) {
            calStart.add(Calendar.WEEK_OF_MONTH,-1);
        }
        calStart.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        calStart.set(Calendar.HOUR_OF_DAY,0);
        calStart.set(Calendar.MINUTE,0);
        calStart.set(Calendar.SECOND,0);
        calStart.set(Calendar.MILLISECOND,0);
        int dayEnd = calEnd.get(Calendar.DAY_OF_WEEK);
        // SUNDAY--1
        if (dayEnd < Calendar.MONDAY) {
            calEnd.add(Calendar.WEEK_OF_MONTH,-1);
        }
        calEnd.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        calEnd.set(Calendar.HOUR_OF_DAY,0);
        calEnd.set(Calendar.MINUTE,0);
        calEnd.set(Calendar.SECOND,0);
        calEnd.set(Calendar.MILLISECOND,0);
        int weeks=(int) ((calEnd.getTimeInMillis()-calStart.getTimeInMillis())/MILLISECONDS_OF_WEEK);
        return ++weeks;
    }

    /**
     * 获取给定的日期是星期几
     * @param date 给定的日期
     * @return 1-7，代表中国的星球一到星期天
     */
    public static int getWeekDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int n = calendar.get(Calendar.DAY_OF_WEEK);
        return --n;
    }
    /**
     * 获取给定的时间与当天24点的时间差（秒数）
     * @param date 给定的日期
     * @return 距离24点还有多少秒
     */
    public static long difftimeWithToday(Date date){
        Calendar curDate = Calendar.getInstance();
        curDate.setTime(date);
        Calendar nextDayDate = new GregorianCalendar(curDate.get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate.get(Calendar.DATE)+1, 0, 0, 0);
        long n = (nextDayDate.getTimeInMillis() - curDate.getTimeInMillis())/1000;
//        long h = n/3600;
//        n = n % 3600;
//        long m = n/60;
//        System.out.println("剩余时间为"+h+" 小时"+m+" 分钟");
        return n;
    }
    /**
     * 获取给定的时间所在的星期一的开始时间
     * @param date 给定的日期
     * @return 一周的开始时间
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        return calendar.getTime();
    }

    /**
     * 获取给定的时间所在的星期天的结束时间
     * @param date 给定的日期
     * @return 一周的结束时间
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6);
        return calendar.getTime();
    }

    /**
     * 由学期开始时间，周次，星期几获取到星期数对应的日期
     */
    public static String getWeekDate(Date sbeginDate, int weekday, int weeks, String dateFormat) {
        String date = "";
        if (sbeginDate != null && weekday != 0 && weeks != 0) {
            Map<String, Object> weekMap = new HashMap<String, Object>();
            Calendar cal = Calendar.getInstance();
            cal.setTime(sbeginDate);
            /*判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了*/
            int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
            if (1 == dayWeek) {
                cal.add(Calendar.DAY_OF_MONTH, -1);
            }
            /*设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一*/
            cal.setFirstDayOfWeek(Calendar.MONDAY);
            /*获得当前日期是一个星期的第几天*/
            int day = cal.get(Calendar.DAY_OF_WEEK);
            /*根据日历的规则，给当前日期减去星期几与一个星期第一天的差值*/
            cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
            /*获取到传入日期所在周的周一*/
            long time = cal.getTime().getTime()+(weeks-1)*WEEK*MILLISECONDS_PER_DAY;
            Date mondayDate = new Date(time);
            for (int i = 1; i <= WEEK; i++) {
                String weekDay = convert(dateFormat,addDate(mondayDate,i-1));
                weekMap.put("week"+i, weekDay);
            }
            date = String.valueOf(weekMap.get("week" + weekday));
        }
        return date;
    }
    /**
     * 时间转化成字符串，并且格式化，本类中提供三种格式化字符串
     *
     * @param dateFormat 格式化字符串
     * @param date       待格式化时间
     * @return 格式化后字符串
     */
    public static String convert(final String dateFormat, final Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String time = formatter.format(date);
        return time;
    }
    /**
     * 时间加日计算，时间日增加
     *
     * @param date   待计算时间
     * @param number 改变数量
     * @return 计算后时间
     */
    public static Date addDate(Date date, final int number) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, +number);
        Date date1 = calendar.getTime();
        return date1;
    }

    public static Date UTCToCST(String UTCStr) throws ParseException {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        date = sdf.parse(UTCStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 8);
        //calendar.getTime() 返回的是Date类型，也可以使用calendar.getTimeInMillis()获取时间戳
        return calendar.getTime();
    }

    /**
     * 修改日期的格式 例如：星期五 --> 07-30(周五)
     * @param semStartDate 学期开始时间
     * @param weekNum 周次
     * @return
     */
    public static List<String> getDateList(Date semStartDate,Integer weekNum){
        List<String> dates = new ArrayList<>();
        for (int i = 1;i<8;i++){
            if(i == 1){
                dates.add(getWeekDate(semStartDate,i,weekNum,DATE_FORMAT_THREE) +"(周一)"
                );
            }else if(i == 2){
                dates.add(getWeekDate(semStartDate,i,weekNum,DATE_FORMAT_THREE) +"(周二)"
                );
            }else if(i == 3){
                dates.add(getWeekDate(semStartDate,i,weekNum,DATE_FORMAT_THREE) +"(周三)"
                );
            }else if(i == 4){
                dates.add(getWeekDate(semStartDate,i,weekNum,DATE_FORMAT_THREE) +"(周四)"
                );
            }else if(i == 5){
                dates.add(getWeekDate(semStartDate,i,weekNum,DATE_FORMAT_THREE) +"(周五)"
                );
            }else if(i == 6){
                dates.add(getWeekDate(semStartDate,i,weekNum,DATE_FORMAT_THREE) +"(周六)"
                );
            }else {
                dates.add(getWeekDate(semStartDate,i,weekNum,DATE_FORMAT_THREE) +"(周日)"
                );
            }
        }
        return dates;
    }

//    public static void main(String[] args) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date l = getLastDayOfWeek(new Date());
//        String s = simpleDateFormat.format(l);
//        String start = simpleDateFormat.format(getFirstDayOfWeek(new Date()));
//        System.out.println(s+"-"+start);
//    }

}














