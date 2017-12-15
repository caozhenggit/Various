package com.cz.various;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.cz.various.bean.ZhiHuBean;
import com.cz.various.callback.TabZhiHuView;
import com.cz.various.mvp.AppFragment;
import com.cz.various.presenter.TabZhiHuPresenter;
import com.cz.various.widget.CircleImageView;
import com.cz.various.widget.ZhiHuAdvertsView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
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
    private int refreshDataCount = 0;

    private static int ITEM_REFRESH = 0;
    private static int ITEM_REFRESH_TOAST = 1;
    private static int ITEM_ADVERTISING1 = 2;
    private static int ITEM_CONTENT = 3;
    private static int ITEM_CONTENT_LIST = 4;


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
        initAdapter();

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if(mDatas.get(position).getType() == ITEM_CONTENT){
                    Intent intentContent = new Intent(getActivity(), ZhiHuContentActivity.class);
                    intentContent.putExtra("content_id", mDatas.get(position).getId());
                    startActivity(intentContent);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void initAdapter(){
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
                CircleImageView mCircleImageView = holder.getView(R.id.imv_user_icon);

                Glide.with(getActivity())
                        .load(item.getImages().get(0))
                        .into(mCircleImageView);
            }
        });
        mAdapter.addItemViewDelegate(ITEM_CONTENT_LIST, new ItemViewDelegate<ZhiHuBean.StoriesBean>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_zhihu_3;
            }

            @Override
            public boolean isForViewType(ZhiHuBean.StoriesBean item, int position) {
                return item.getType() == ITEM_CONTENT_LIST;
            }

            @Override
            public void convert(ViewHolder holder, ZhiHuBean.StoriesBean item, int position) {
                List<Integer> list = new ArrayList<Integer>();
                for(int i = 0; i < 10; i++){
                    list.add(i);
                }
                RecyclerView mItemRecyclerView = holder.getView(R.id.item_recyclerView);
                CommonAdapter<Integer> commonAdapter = new CommonAdapter<Integer>(getActivity(), R.layout.item_zhihu_list_item, list) {
                    @Override
                    protected void convert(ViewHolder holder, Integer integer, int position) {

                    }
                };
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                mItemRecyclerView.setLayoutManager(linearLayoutManager);
                mItemRecyclerView.setAdapter(commonAdapter);
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
        mAdapter.addItemViewDelegate(ITEM_REFRESH_TOAST, new ItemViewDelegate<ZhiHuBean.StoriesBean>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_refresh_toast;
            }

            @Override
            public boolean isForViewType(ZhiHuBean.StoriesBean item, int position) {
                return item.getType() == ITEM_REFRESH_TOAST;
            }

            @Override
            public void convert(ViewHolder holder, ZhiHuBean.StoriesBean item, int position) {
                holder.setText(R.id.tv_top_toast, "本次共有"+ refreshDataCount + "条数据更新");
            }
        });
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

        //添加数据, 设置类型为正文
        for(int i = 0; i < bean.getStories().size(); i++){
            bean.getStories().get(i).setType(ITEM_CONTENT);
        }
        mDatas.addAll(0, bean.getStories());

        //添加刷新item
        ZhiHuBean.StoriesBean mStoriesBean1 = new ZhiHuBean.StoriesBean();
        mStoriesBean1.setType(ITEM_REFRESH);
        mDatas.add(bean.getStories().size(), mStoriesBean1);

        //添加广告item
        if(mDatas.size() > 10){
            ZhiHuBean.StoriesBean mStoriesBean2 = new ZhiHuBean.StoriesBean();
            mStoriesBean2.setType(ITEM_ADVERTISING1);
            mDatas.add(3, mStoriesBean2);

            ZhiHuBean.StoriesBean mStoriesBean4 = new ZhiHuBean.StoriesBean();
            mStoriesBean4.setType(ITEM_CONTENT_LIST);
            mDatas.add(1, mStoriesBean4);
        }

        //更新数据条数
        ZhiHuBean.StoriesBean mStoriesBean3 = new ZhiHuBean.StoriesBean();
        mStoriesBean3.setType(ITEM_REFRESH_TOAST);
        mDatas.add(0, mStoriesBean3);

        refreshDataCount = bean.getStories().size();
        topToast();

        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);

        mLinearLayoutManager.scrollToPosition(0);
    }

    private void topToast(){
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                mDatas.remove(0);
                mAdapter.notifyDataSetChanged();

                mLinearLayoutManager.scrollToPosition(0);
            }
        }, 2000);
    }
}
