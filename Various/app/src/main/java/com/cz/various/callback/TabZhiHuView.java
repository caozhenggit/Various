package com.cz.various.callback;

import com.cz.various.bean.ZhiHuBean;
import com.cz.various.mvp.BaseView;

/**
 * @author caozheng
 * @date 2017/12/11
 * <p>
 * describe:
 */

public interface TabZhiHuView extends BaseView {

    void getDataDone(ZhiHuBean bean);
}
