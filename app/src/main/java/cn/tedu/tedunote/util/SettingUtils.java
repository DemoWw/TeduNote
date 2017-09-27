package cn.tedu.tedunote.util;

import android.content.Context;
import android.content.SharedPreferences;

import cn.tedu.tedunote.entity.User;

/**
 * Created by tarena on 2017/9/26.
 */
public abstract class SettingUtils {
    private static final String FILE_NAME = "settings";
    private static final String KEY_IS_FIRST_RUN = "is_first_run";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_NICK = "user_nick";
    private static final String KEY_USER_TOKEN = "user_token";
    private static final String KEY_COOKIE = "cookie";

    public static void saveUserCookie(Context context, String cookie) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_COOKIE, cookie);
        editor.commit();
    }

    public static String getUserCookie(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        String cookie = sp.getString(KEY_COOKIE, null);
        return cookie;
    }

    public static void saveUserInfo(Context context, User user) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_USER_ID, user.getId());
        editor.putString(KEY_USER_NAME, user.getName());
        editor.putString(KEY_USER_NICK, user.getNick());
        editor.putString(KEY_USER_TOKEN, user.getToken());
        editor.commit();
    }

    public static User getUserInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        String id = sp.getString(KEY_USER_ID, null);
        String name = sp.getString(KEY_USER_NAME, null);
        String nick = sp.getString(KEY_USER_NICK, null);
        String token = sp.getString(KEY_USER_TOKEN, null);
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setNick(nick);
        user.setToken(token);
        return user;
    }

    /**
     * 保存用户是否是第1次运行这个软件
     * @param context
     * @param isFirstRun 是否是第1次运行
     */
    public static void setIsFirstRun(Context context, boolean isFirstRun) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_IS_FIRST_RUN, isFirstRun);
        editor.commit();
    }

    /**
     * 读取用户是否是第1次运行这个软件
     * @param context
     * @return 是否是第1次运行这个软件
     */
    public static boolean isFirstRun(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(KEY_IS_FIRST_RUN, true);
    }
}
