package cn.tedu.tedunote.model;

/**
 * Created by tarena on 2017/9/23.
 */
public abstract class UserModelFactory {

    public static IUserModel getInstance() {
        // 使用工厂的优势之一在于整个项目对UserModelRetrofitImpl类的依赖性！
        // return new UserModelVolleyImpl();
        return new UserModelRetrofitImpl();
    }

}
