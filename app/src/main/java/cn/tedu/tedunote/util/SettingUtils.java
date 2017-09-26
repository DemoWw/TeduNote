package cn.tedu.tedunote.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tarena on 2017/9/26.
 */
public abstract class SettingUtils {
    private static final String FILE_NAME = "settings";
    private static final String KEY_IS_FIRST_RUN = "is_first_run";

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
