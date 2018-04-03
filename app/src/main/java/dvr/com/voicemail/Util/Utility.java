package dvr.com.voicemail.Util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by android on 24/10/17.
 */

public class Utility {


    public static void setLoginCredential(Context ctx ,String email, String Password)
    {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences("logincredential",ctx.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email",email);
        editor.putString("pass",Password);
        editor.apply();

    }

    public static String getLoginEmail(Context ctx )
    {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences("logincredential",ctx.MODE_PRIVATE);
        return     sharedPreferences.getString("email","");

    }
    public static String getLoginPass(Context ctx )
    {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences("logincredential",ctx.MODE_PRIVATE);
        return     sharedPreferences.getString("pass","");

    }


    public static void ClearLoginCredential(Context  context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("logincredential",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
    }







}
