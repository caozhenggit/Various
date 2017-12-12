package com.cz.various.presenter;

import com.cz.various.Api;
import com.cz.various.bean.ZhiHuBean;
import com.cz.various.callback.TabZhiHuView;
import com.cz.various.kit.DateKit;
import com.cz.various.mvp.BasePresenter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * @author caozheng
 * @date 2017/12/11
 *
 * describe:
 */

public class TabZhiHuPresenter extends BasePresenter<TabZhiHuView> {

    public TabZhiHuPresenter(TabZhiHuView view) {
        attachView(view);
    }

    public void getData(int date) {
        long timeInMills = System.currentTimeMillis() - (date*24*60*60*1000);
        OkGo.<String>get(Api.URL_ZHIHU + DateKit.getYmd(timeInMills))
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ZhiHuBean bean = new Gson().fromJson(response.body(), ZhiHuBean.class);

                        mView.getDataDone(bean);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }
}
