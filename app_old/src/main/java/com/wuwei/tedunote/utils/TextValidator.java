package com.wuwei.tedunote.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wuwei on 2017/9/23.
 */

public class TextValidator {

    public static interface Result {
        /**
         * 验证通过
         */
        int OK = 0;
        /**
         * 用户名长度错误
         */
        int ERR_USERNAME_LENGTH = 1;
        /**
         * 用户名包括非常字符
         */
        int ERR_USERNAME_INVALID_CHAR = 2;
        /**
         * 密码长度错误
         */
        int ERR_PASSWORD_LENGTH = 3;
        /**
         * 错误信息
         */
        String[] MESSAGE = {
                "验证通过！",
                "错误！用户名长度有误！",
                "错误！用户名中有非法字符！",
                "错误！密码长度有误！"
        };
    }

    /**
     * 用户名的最小长度
     */
    private static final int USERNAME_MIN_LENGTH = 4;
    /**
     * 用户名的最大长度
     */
    private static final int USERNAME_MAX_LENGTH = 16;
    /**
     * 检查用户名的正则表达式
     */
    private static final String REGEX_USERNAME = "[a-zA-Z]{1}[a-zA-Z0-9_]{1,15}";
    /**
     * 密码最小长度
     */
    private static final int PASSWORD_MIN_LENGTH = 4;
    /**
     * 密码最大长度
     */
    private static final int PASSWORD_MAX_LENGTH = 16;

    /**
     * 检查用户名合法性
     * @param username 要验证的用户名
     * @return {@link TextValidator.Result#OK} 表示正确，{@link TextValidator.Result#ERR_USERNAME_LENGTH} 表示用户名长度有误，
     * {@link TextValidator.Result#ERR_USERNAME_INVALID_CHAR} 表示用户名包含非法字符
     */
    public static int checkUsername(String username) {
        if (username.length() < USERNAME_MIN_LENGTH || username.length() > USERNAME_MAX_LENGTH) {
            return Result.ERR_USERNAME_LENGTH;
        }

        Pattern pattern = Pattern.compile(REGEX_USERNAME);
        Matcher matcher = pattern.matcher(username);
        if (!matcher.matches()) {
            return Result.ERR_USERNAME_INVALID_CHAR;
        }

        return Result.OK;
    }

    /**
     * 检查密码合法性
     * @param password 要验证的密码
     * @return {@link TextValidator.Result#OK} 表示正确，{@link TextValidator.Result#ERR_PASSWORD_LENGTH} 表示密码长度有误
     */
    public static int checkPassword(String password) {
        if (password.length() < PASSWORD_MIN_LENGTH || password.length() > PASSWORD_MAX_LENGTH) {
            return Result.ERR_PASSWORD_LENGTH;
        }
        return Result.OK;
    }

    public static int checkNickname(String nickname) {
        return  checkUsername(nickname);
    }

}
