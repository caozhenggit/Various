package com.cz.various.presenter;

import com.cz.various.callback.MainView;
import com.cz.various.mvp.BasePresenter;

/**
 * @author caozheng
 * Created time on 2017/11/24
 *
 * description:
 */

public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView view){
        attachView(view);
    }
}
