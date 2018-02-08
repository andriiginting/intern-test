package com.andriginting.internshiptest.util;


import android.content.Context;
import android.content.SharedPreferences;

public class UserPreference {
    public static final String PREF_NAME = "com.andriiginting";
    public static final String PREF_IS_LOGIN = "isLogin";

    public static final String PREF_USERNAME ="prefUsernmae";
    public static final String PREF_PASSWORD ="prefPassword";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public UserPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

   public  void setPrefUsername(String keyPref, String value){
        editor.putString(keyPref,value);
        editor.commit();
   }

   public void setPrefPassword(String keyPref, String value){
       editor.putString(keyPref,value);
       editor.commit();
   }
   public void isLogin(String keyPref, boolean value){
       editor.putBoolean(keyPref,value);
       editor.commit();
   }

   public String getPrefName(){
       return sharedPreferences.getString(PREF_USERNAME,"");
   }
   public String getPrefPassword(){
       return sharedPreferences.getString(PREF_PASSWORD,"");
   }
   public boolean alreadyLogin(){
       return sharedPreferences.getBoolean(PREF_IS_LOGIN,false);
   }
}
