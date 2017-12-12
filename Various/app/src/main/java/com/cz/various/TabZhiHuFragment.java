package com.cz.various;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.cz.various.bean.ZhiHuBean;
import com.cz.various.callback.TabZhiHuView;
import com.cz.various.mvp.AppFragment;
import com.cz.various.presenter.TabZhiHuPresenter;
import com.cz.various.widget.ZhiHuAdvertsView;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author caozheng
 * @date 2017/12/11
 *
 * describe:
 */

public class TabZhiHuFragment extends AppFragment<TabZhiHuPresenter> implements TabZhiHuView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.ll_search)
    LinearLayout mLlSearch;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private int dateCount = 0;

    private static int ITEM_REFRESH = 0;
    private static int ITEM_CONTENT = 1;
    private static int ITEM_ADVERTISING1 = 2;

    private MultiItemTypeAdapter<ZhiHuBean.StoriesBean> mAdapter;
    private List<ZhiHuBean.StoriesBean> mDatas = new ArrayList<ZhiHuBean.StoriesBean>();

    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected TabZhiHuPresenter createPresenter() {
        return new TabZhiHuPresenter(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_tab_zhihu;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark);
    }

    @Override
    public void doBusiness() {
        initData();

        refreshData();
    }

    @OnClick(R.id.ll_search)
    public void onClick() {

    }

    @Override
    public void onRefresh() {
        refreshData();
    }

    private void refreshData(){
        mSwipeRefreshLayout.setRefreshing(true);
        mPresenter.getData(dateCount);
    }

    private void initData(){
        mAdapter = new MultiItemTypeAdapter<ZhiHuBean.StoriesBean>(getActivity(), mDatas);
        mAdapter.addItemViewDelegate(ITEM_CONTENT, new ItemViewDelegate<ZhiHuBean.StoriesBean>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_zhihu_1;
            }

            @Override
            public boolean isForViewType(ZhiHuBean.StoriesBean item, int position) {
                return item.getType() == ITEM_CONTENT;
            }

            @Override
            public void convert(ViewHolder holder, ZhiHuBean.StoriesBean item, int position) {
                holder.setText(R.id.tv_title, item.getTitle());
            }
        });
        mAdapter.addItemViewDelegate(ITEM_ADVERTISING1, new ItemViewDelegate<ZhiHuBean.StoriesBean>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_zhihu_2;
            }

            @Override
            public boolean isForViewType(ZhiHuBean.StoriesBean item, int position) {
                return item.getType() == ITEM_ADVERTISING1;
            }

            @Override
            public void convert(ViewHolder holder, ZhiHuBean.StoriesBean item, int position) {
                ZhiHuAdvertsView adImageView = holder.getView(R.id.id_iv_ad);
                adImageView.bindView(mRecyclerView);
            }
        });
        mAdapter.addItemViewDelegate(ITEM_REFRESH, new ItemViewDelegate<ZhiHuBean.StoriesBean>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_refresh;
            }

            @Override
            public boolean isForViewType(ZhiHuBean.StoriesBean item, int position) {
                return item.getType() == ITEM_REFRESH;
            }

            @Override
            public void convert(ViewHolder holder, ZhiHuBean.StoriesBean item, int position) {
                holder.setOnClickListener(R.id.ll_refresh, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        refreshData();
                    }
                });
            }
        });

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getDataDone(ZhiHuBean bean) {
        dateCount++;

        for (int i = 0; i < mDatas.size(); i++) {
            if(mDatas.get(i).getType() == ITEM_REFRESH){
                mDatas.remove(i);

                break;
            }
        }

        Collections.reverse(mDatas);

        //添加刷新item
        ZhiHuBean.StoriesBean mStoriesBean1 = new ZhiHuBean.StoriesBean();
        mStoriesBean1.setType(ITEM_REFRESH);
        mDatas.add(mStoriesBean1);

        //添加广告
        ZhiHuBean.StoriesBean mStoriesBean2 = new ZhiHuBean.StoriesBean();
        mStoriesBean2.setType(ITEM_ADVERTISING1);
        mDatas.add(mStoriesBean2);

        //添加数据, 设置类型为正文
        for(int i = 0; i < bean.getStories().size(); i++){
            bean.getStories().get(i).setType(ITEM_CONTENT);
        }
        mDatas.addAll(bean.getStories());

        Collections.reverse(mDatas);

        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);

        mLinearLayoutManager.scrollToPosition(0);
    }
}
