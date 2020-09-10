package com.kyx.util;

import org.elasticsearch.client.ml.job.config.DataDescription;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeUtil {
    /**
     * 创建当前时间
     * @return
     */
    public String createCurrentTime(){
        Date date =new Date();
        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(date);
        return currentDate;
    }

    public long getLongTime(){
        Date date =new Date();
        return date.getTime()/1000;
    }

    public String currentPrecisionTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter =DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm");
        return now.format(dateTimeFormatter);
    }
}
