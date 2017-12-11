package com.cz.various.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cz.various.R;
import com.cz.various.TabZhiHuFragment;
import com.cz.various.adapter.MainPagerAdapter;
import com.cz.various.callback.MainView;
import com.cz.various.mvp.AppActivity;
import com.cz.various.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author caozheng
 * @date 2016/11/27
 * <p>
 * describe:
 */
public class MainActivity extends AppActivity<MainPresenter> implements MainView {

    @BindView(R.id.imv_tab_zhi)
    ImageView mImvTabZhi;
    @BindView(R.id.tv_tab_zhi)
    TextView mTvTabZhi;
    @BindView(R.id.imv_tab_music)
    ImageView mImvTabMusic;
    @BindView(R.id.tv_tab_music)
    TextView mTvTabMusic;
    @BindView(R.id.imv_tab_gank)
    ImageView mImvTabGank;
    @BindView(R.id.tv_tab_gank)
    TextView mTvTabGank;
    @BindView(R.id.ll_bottom_tab)
    LinearLayout mLlBottomTab;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private static final int TAB_ZHIHU = 1;
    private static final int TAB_MUSIC = 2;
    private static final int TAB_GANK = 3;

    private List<Fragment> tabList = new ArrayList<Fragment>();

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
        initTab();

        switchTabColor(TAB_ZHIHU);
    }

    private void initTab(){
        tabList.add(new TabZhiHuFragment());

        FragmentManager mFragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new MainPagerAdapter(mFragmentManager, tabList));
    }

    @OnClick({R.id.imv_tab_zhi, R.id.tv_tab_zhi, R.id.imv_tab_music, R.id.tv_tab_music, R.id.imv_tab_gank, R.id.tv_tab_gank})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_tab_zhi:
            case R.id.tv_tab_zhi:
                switchTabColor(TAB_ZHIHU);
                break;

            case R.id.imv_tab_music:
            case R.id.tv_tab_music:
                switchTabColor(TAB_MUSIC);
                break;

            case R.id.imv_tab_gank:
            case R.id.tv_tab_gank:
                switchTabColor(TAB_GANK);
                break;

            default:
                break;
        }
    }

    private void switchTabColor(int tab) {
        switch (tab) {
            case TAB_ZHIHU:
                mImvTabZhi.setImageResource(R.mipmap.zhihu_selected_icon);
                mImvTabMusic.setImageResource(R.mipmap.music_normal_icon);
                mImvTabGank.setImageResource(R.mipmap.gank_normal_icon);
                break;

            case TAB_MUSIC:
                mImvTabZhi.setImageResource(R.mipmap.zhihu_normal_icon);
                mImvTabMusic.setImageResource(R.mipmap.music_selected_icon);
                mImvTabGank.setImageResource(R.mipmap.gank_normal_icon);
                break;

            case TAB_GANK:
                mImvTabZhi.setImageResource(R.mipmap.zhihu_normal_icon);
                mImvTabMusic.setImageResource(R.mipmap.music_normal_icon);
                mImvTabGank.setImageResource(R.mipmap.gank_selected_icon);
                break;

            default:
                break;
        }
    }
}
