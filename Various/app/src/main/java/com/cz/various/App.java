package com.cz.various;

import android.app.Application;
import android.content.Context;

/**
 * @author caozheng
 * @date 2017/11/23
 *
 * describe:
 */

public class App extends Application {

    /** 是否打印Log */
    private static final boolean LOG_DEBUG = true;
    /** 全局的连接超时时间 */
    private static final int CONNECT_TIMEOUT_TIME = 60000;
    /** 全局的读取超时时间 */
    private static final int READ_TIMEOUT_TIME = 60000;
    /** 全局的写入超时时间 */
    private static final int WRITE_TIMEOUT_TIME = 60000;
    /** 重连次数 */
    private static final int RETRY_COUNT = 3;

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
    }

    public static Context getAppContext(){
        return mContext;
    }

}
