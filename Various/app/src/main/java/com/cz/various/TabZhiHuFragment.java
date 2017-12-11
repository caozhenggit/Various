package com.cz.various;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.cz.various.callback.TabZhiHuView;
import com.cz.various.mvp.AppFragment;
import com.cz.various.presenter.TabZhiHuPresenter;
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
 * <p>
 * describe:
 */

public class TabZhiHuFragment extends AppFragment<TabZhiHuPresenter> implements TabZhiHuView {

    @BindView(R.id.ll_search)
    LinearLayout mLlSearch;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private static int ITEM = 1;
    private static int ADVERTISING = 2;

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
    }

    @Override
    public void doBusiness() {
        initData();
    }

    @OnClick(R.id.ll_search)
    public void onClick() {

    }

    private void initData(){
        List<Integer> mData = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            mData.add(ITEM);
        }

        MultiItemTypeAdapter<Integer> adapter = new MultiItemTypeAdapter<Integer>(getActivity(), mData);
        adapter.addItemViewDelegate(ITEM, new ItemViewDelegate<Integer>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_zhihu_1;
            }

            @Override
            public boolean isForViewType(Integer item, int position) {
                return item == ITEM;
            }

            @Override
            public void convert(ViewHolder holder, Integer integer, int position) {

            }
        });
//        adapter.addItemViewDelegate(ADVERTISING, new ItemViewDelegate<Integer>() {
//            @Override
//            public int getItemViewLayoutId() {
//                return R.layout.item_zhihu_advertising;
//            }
//
//            @Override
//            public boolean isForViewType(Integer item, int position) {
//                return item == ADVERTISING;
//            }
//
//            @Override
//            public void convert(ViewHolder holder, Integer integer, int position) {
//                ZhiHuAdvertsView adImageView = holder.getView(R.id.id_iv_ad);
//                adImageView.bindView(mRecyclerView);
//            }
//        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);
    }
}
