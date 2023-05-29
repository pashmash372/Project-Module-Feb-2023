package org.example;

import java.time.LocalDateTime;

public interface Lib {
    static String getGreeting(){
        int hour= LocalDateTime.now().getHour();
        if(hour<12){
            return "Good Morning";
        }
        else if(hour<18){
            return "Good AfterNoon";
        }
        else{
            return "Good Evening";
        }
    }
}
