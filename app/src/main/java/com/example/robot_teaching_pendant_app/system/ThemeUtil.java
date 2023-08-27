//package com.example.robot_teaching_pendant_app.System;
//
//import android.os.Build;
//
//import androidx.appcompat.app.AppCompatDelegate;
//
//public class ThemeUtil {
//    public static final String DEFAULT_MODE = "default";
//    public static final String DARK_MODE = "dark";
//    public static final String LIGHT_MODE = "light";
//
//    public static void applyTheme(String themeColor){
//        switch(themeColor){
//            case LIGHT_MODE:
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                break;
//
//            case DARK_MODE:
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                break;
//
//            default:
//                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
//                }
//
//                else{
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
//                }
//                break;
//        }
//    }
//}

////                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
////                val editor = sharedPreferences.edit()
////                editor.putBoolean("night",false)
//
//
////
//////              AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
////                val editor = sharedPreferences.edit()
////                editor.putBoolean("night",true)
