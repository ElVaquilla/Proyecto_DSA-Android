package com.example.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.MultiAutoCompleteTextView;

public class SessionManager {

    private static final String PREF_NAME="SessionPREFS";
    private static final String KEY_USERNAME="username";
    private static final String KEY_DIF = "dificultad";
    private static final String KEY_LAST_LOGIN_TIME="lastLogin";

    private static final String KEY_MUSIC = "musica";

    public  static void dif(Context context, int dificultad){
        SharedPreferences.Editor editor=context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(KEY_DIF,dificultad);
        editor.putLong(KEY_LAST_LOGIN_TIME,System.currentTimeMillis());
        editor.apply();
    }

    public static void sonido(Context context, Boolean musica){
        SharedPreferences.Editor editor=context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(KEY_MUSIC,musica);
        editor.putLong(KEY_LAST_LOGIN_TIME,System.currentTimeMillis());
        editor.apply();
    }

    public static void loginUser(Context context, String username){
        SharedPreferences.Editor editor=context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(KEY_USERNAME,username);
        editor.putLong(KEY_LAST_LOGIN_TIME,System.currentTimeMillis());
        editor.apply();
    }
    public static boolean userLogged(Context context){
        SharedPreferences preferences= context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String username=preferences.getString(KEY_USERNAME,null);
        long lastLogin=preferences.getLong(KEY_LAST_LOGIN_TIME,0);
        long sessionTimeOut=getSessionTimeout();
        return  username !=null &&(System.currentTimeMillis()-lastLogin)<sessionTimeOut;
    }
    private static long getSessionTimeout(){
        return 86400*1000;
    }
    public  static void registerUser(Context context, String username){

        loginUser(context,username);
    }
    public static void logOutUser(Context context){
        SharedPreferences.Editor editor=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE).edit();
        editor.remove(KEY_USERNAME);
        editor.remove(KEY_LAST_LOGIN_TIME);
        editor.remove(KEY_DIF);
        editor.remove(KEY_MUSIC);
        editor.apply();
    }

    public static String getLoggedUsername(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getString(KEY_USERNAME, null);
    }

    public static int getKeyDif(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(KEY_DIF, 0);
    }

    public static Boolean getKeyMusic(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(KEY_MUSIC,true);
    }
}
