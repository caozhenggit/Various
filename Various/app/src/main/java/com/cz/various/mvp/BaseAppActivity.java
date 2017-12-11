package com.cz.various.mvp;

import android.os.Bundle;

/**
 * @author 10744
 * @date 2017/11/22
 * <p>
 * describe:
 */

public abstract class BaseAppActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppActivityManager.getInstance().addActivity(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppActivityManager.getInstance().finishActivity();
    }
}
