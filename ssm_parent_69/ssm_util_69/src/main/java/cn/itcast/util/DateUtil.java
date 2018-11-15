package cn.itcast.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: wyan
 * @Date: 2018/11/7 09:58
 * @Description:
 */
public class DateUtil {
    /**
     * 功能描述:接受日期处理成字符串返回
     * @param:
     * @return:
     * @auther: wyan
     * @date: 2018/11/7 9:59
     */
    public static  String formatDateToStr(Date date){

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return  "";
        }
    }

    /**
     * 功能描述:接受字符串参数 返回日期对象
     * @param:
     * @return:
     * @auther: wyan
     * @date: 2018/11/7 10:54
     */
    public static Date parseStrToDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
}
