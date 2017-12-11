package com.cz.various.presenter;

import com.cz.various.callback.TabZhiHuView;
import com.cz.various.mvp.BasePresenter;

/**
 * @author caozheng
 * @date 2017/12/11
 * <p>
 * describe:
 */

public class TabZhiHuPresenter extends BasePresenter<TabZhiHuView> {

    public TabZhiHuPresenter(TabZhiHuView view) {
        attachView(view);
    }
}
