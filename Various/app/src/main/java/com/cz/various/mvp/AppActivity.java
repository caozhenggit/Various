package com.cz.various.mvp;

import android.content.Intent;
import android.os.Bundle;

/**
 * @author caozheng
 * @date 2017/10/28
 *
 * describe:
 */
public abstract class AppActivity<P extends BasePresenter> extends BaseActivity {
    protected P mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mPresenter = createPresenter();
        super.onCreate(savedInstanceState);

        initParams(getIntent());

        AppActivityManager.getInstance().addActivity(this);
    }

    public abstract P createPresenter();

    /**
     * 初始化Intent参数
     *
     * @param intent
     */
    public void initParams(Intent intent){

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        AppActivityManager.getInstance().finishActivity();
    }
}
