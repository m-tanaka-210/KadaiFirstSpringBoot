package com.techacademy;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.Calendar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KadaiFirstController {

    // 仕様1：指定日の曜日を算定する
    @GetMapping("/dayofweek/{date}")
    public String dispDayOfWeek(@PathVariable("date") String date) {
        // substring()で年月日を取得
        String yearStr = date.substring(0,4);
        String monthStr = date.substring(4,6);
        String dayStr = date.substring(6,8);

        // 数値として扱えるようにする
        int year = Integer.parseInt(yearStr);
        int month = Integer.parseInt(monthStr) -1; // 値1からはじまるので1月は値0にするために-1
        int day = Integer.parseInt(dayStr);

        // Calendarクラスで曜日取得
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        // 曜日の数値を取得（日曜始まり）
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // 曜日の数値を文字列に変換
        String dayOfWeekStr = getDayOfWeekString(dayOfWeek);

        return "指定日の曜日：" + dayOfWeekStr;
    }

    // 曜日を文字列で表示するためのメソッド
    private String getDayOfWeekString(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return "Sunday";
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wednesday";
            case Calendar.THURSDAY:
                return "Thursday";
            case Calendar.FRIDAY:
                return "Friday";
            case Calendar.SATURDAY:
                return "Saturday";
            default:
                return "";
        }
    }

    // 仕様2：四則演算を行なう
    @GetMapping("/plus/{val1}/{val2}")
    public String calcPlus(@PathVariable int val1, @PathVariable int val2) {
        int res = 0;
        res = val1 + val2;
        return "足し算の結果：" + res;
    }
    @GetMapping("/minus/{val1}/{val2}")
    public String calcMinus(@PathVariable int val1, @PathVariable int val2) {
        int res = 0;
        res = val1 - val2;
        return "引き算の結果：" + res;
    }
    @GetMapping("/times/{val1}/{val2}")
    public String calcTimes(@PathVariable int val1, @PathVariable int val2) {
        int res = 0;
        res = val1 * val2;
        return "掛け算の結果：" + res;
    }
    @GetMapping("/divide/{val1}/{val2}")
    public String calcDivide(@PathVariable double val1, @PathVariable double val2) {
        double res = 0;
        if (val2 == 0) {
            return "0で割ることはできません";
        }
        res = val1 / val2;
        return "割り算の結果：" + res;
    }
}

