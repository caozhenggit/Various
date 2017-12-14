package com.cz.various;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cz.various.mvp.BaseAppActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author caozheng
 * @date 2017/12/14
 * <p>
 * describe:
 */

public class ZhiHuContentActivity extends BaseAppActivity {

    @BindView(R.id.webView)
    WebView webView;

    @Override
    public int bindLayout() {
        return R.layout.activity_zhihu_content;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);

        int contentId = getIntent().getIntExtra("content_id", 1);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl("http://daily.zhihu.com/story/" + contentId);
    }
}
