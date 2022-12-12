package com.application.vozyk.ui.notifications;


public class TIME {
    public static String MORNING = "0800";
    public static String AFTERNOON = "1400";
    public static String NIGHT = "2030";


    public static class AlarmBundle {
        String time;

        AlarmBundle() {
        }

        AlarmBundle(String time) {
            this.time = time;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

    }


}
