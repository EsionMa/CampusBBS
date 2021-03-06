package com.yan.campusbbs.module.campusbbs.life;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.yan.campusbbs.ApplicationCampusBBS;
import com.yan.campusbbs.R;
import com.yan.campusbbs.module.campusbbs.PagerTabAdapterModule;
import com.yan.campusbbs.module.campusbbs.TabPagerFragment;
import com.yan.campusbbs.setting.SettingHelper;
import com.yan.campusbbs.setting.SettingModule;
import com.yan.campusbbs.util.SPUtils;
import com.yan.campusbbs.module.campusbbs.PagerTabAdapter;
import com.yan.campusbbs.rxbusaction.ActionChangeSkin;
import com.yan.campusbbs.util.RxBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Main UI for the add task screen. Users can enter a task title and description.
 */
public class LifeFragment extends TabPagerFragment implements LifeContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.store_house_ptr_frame)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.pager_bar)
    FrameLayout appBar;
    @BindView(R.id.pager_bar_recycler)
    RecyclerView pagerBarRecycler;
    @Inject
    LifePresenter mPresenter;

    @Inject
    RxBus rxBus;
    @Inject
    PagerTabAdapter pagerTabAdapter;
    @Inject
    SettingHelper changeSkinHelper;

    @Inject
    SPUtils spUtils;


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    private void dataInit() {
        pagerTabItem.add(new PagerTabAdapter.PagerTabItem("全部", true));
        pagerTabItem.add(new PagerTabAdapter.PagerTabItem("生活"));
        pagerTabItem.add(new PagerTabAdapter.PagerTabItem("生活"));
        pagerTabItem.add(new PagerTabAdapter.PagerTabItem("生活"));
        pagerTabItem.add(new PagerTabAdapter.PagerTabItem("生活"));
        pagerTabItem.add(new PagerTabAdapter.PagerTabItem("生活"));
        pagerTabItem.add(new PagerTabAdapter.PagerTabItem("生活"));
        pagerTabItem.add(new PagerTabAdapter.PagerTabItem("生活"));
        pagerTabItem.add(new PagerTabAdapter.PagerTabItem("生活"));
        pagerTabAdapter.notifyDataSetChanged();
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_campus_bbs_life, container, false);
        ButterKnife.bind(this, view);
        init();
        daggerInject();
        dataInit();
        return view;
    }

    private void daggerInject() {
        DaggerLifeComponent.builder()
                .applicationComponent(((ApplicationCampusBBS) getActivity()
                        .getApplication())
                        .getApplicationComponent())
                .settingModule(new SettingModule(this, compositeDisposable))
                .lifeFragmentModule(new LifeFragmentModule(this))
                .pagerTabAdapterModule(new PagerTabAdapterModule(pagerTabItem))
                .build().inject(this);

        attach(recyclerView, pagerBarRecycler, pagerTabAdapter, appBar, rxBus);
    }

    private void init() {
    }

    public static LifeFragment newInstance() {
        return new LifeFragment();
    }

    public LifeFragment() {
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected SPUtils sPUtils() {
        return spUtils;
    }

    @Override
    protected SwipeRefreshLayout swipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    @Override
    public void changeSkin(ActionChangeSkin actionChangeSkin) {
        super.changeSkin(actionChangeSkin);
    }

}
