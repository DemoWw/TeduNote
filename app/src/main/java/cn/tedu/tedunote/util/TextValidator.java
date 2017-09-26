package cn.tedu.tedunote.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tarena on 2017/9/23.
 */
public abstract class TextValidator {
    /**
     * 文本验证的结果
     */
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
         * 用户名中包括非常字符的错误
         */
        int ERR_USERNAME_INVALID_CHAR = 2;
        /**
         * 密码长度错误
         */
        int ERR_PASSWORD_LENGTH = 3;
        /**
         * 错误的描述
         */
        String[] TEXT = {
                "验证通过！",
                "错误！用户名长度有误！",
                "错误！用户名中包含非常字符！",
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
     * 验证用户名的正则表达式
     */
    private static final String REGEX_USERNAME = "[a-zA-Z]{1}[a-zA-Z0-9_]{1,15}";
    /**
     * 密码的最小长度
     */
    private static final int PASSWORD_MIN_LENGTH = 4;
    /**
     * 密码的最大长度
     */
    private static final int PASSWORD_MAX_LENGTH = 16;

    /**
     * 验证用户名
     * @param username 被验证的字符串
     * @return 可能返回 {@link TextValidator.Result#OK} 表示正确，或返回 {@link TextValidator.Result#ERR_USERNAME_LENGTH} 表示用户名的长度有误，或返回 {@link TextValidator.Result#ERR_USERNAME_INVALID_CHAR} 表示用户名中包括非法字符。
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

    public static int checkNickname(String nickname) {
        return checkUsername(nickname);
    }

    /**
     * 验证密码
     * @param password 被验证的字符串
     * @return 可能返回 {@link TextValidator.Result#OK} 表示正确，或返回 {@link TextValidator.Result#ERR_PASSWORD_LENGTH} 表示密码的长度有误。
     */
    public static int checkPassword(String password) {
        if (password.length() < PASSWORD_MIN_LENGTH || password.length() > PASSWORD_MAX_LENGTH) {
            return Result.ERR_PASSWORD_LENGTH;
        }
        return Result.OK;
    }
}
