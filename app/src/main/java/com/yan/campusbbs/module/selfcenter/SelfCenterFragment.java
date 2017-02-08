package com.yan.campusbbs.module.selfcenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yan.adapter.CustomAdapter;
import com.yan.campusbbs.R;
import com.yan.campusbbs.base.StatedFragment;
import com.yan.campusbbs.module.selfcenter.adapterholder.SelfCenterAdapterHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Main UI for the add task screen. Users can enter a task title and description.
 */
public class SelfCenterFragment extends StatedFragment implements SelfCenterContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.store_house_ptr_frame)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.app_bar_background)
    View appBarBackground;
    @BindView(R.id.app_bar_title)
    TextView appBarTitle;

    private int actionBarPinHeight;
    private float offsetDy;

    private List<String> strings;
    private CustomAdapter adapter;

    private SelfCenterContract.Presenter mPresenter;

    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_self_center, container, false);
            ButterKnife.bind(this, root);
            init();
        } else {
            if (root.getParent() != null) {
                ((ViewGroup) root.getParent()).removeView(root);
            }
        }
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null)
            mPresenter.start();
    }

    private void init() {
        actionBarPinHeight =
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP
                        , 100
                        , getResources().getDisplayMetrics());
        dataInit();
        adapter = SelfCenterAdapterHelper.getAdapter(getContext(), strings);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(onScrollListener);
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        swipeRefreshLayout.setProgressViewOffset(true,
                (int) getResources().getDimension(R.dimen.action_bar_height)
                , (int) getResources().getDimension(R.dimen.action_bar_height) * 2);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(
                ContextCompat.getColor(getContext(), R.color.colorAccent)
        );
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getContext(), R.color.crFEFEFE)
        );
    }

    private void dataInit() {
        strings = new ArrayList<>();
        strings.add("个人中心");
        strings.add("个人中心");
        strings.add("个人中心");
        strings.add("个人中心");
        strings.add("个人中心");
        strings.add("个人中心");
        strings.add("个人中心");
        strings.add("个人中心");
        strings.add("个人中心");
        strings.add("个人中心");
        strings.add("个人中心");
        strings.add("个人中心");
    }

    @Override
    protected void onSaveArguments(Bundle bundle) {
        bundle.putFloat("offsetDy", offsetDy);
    }

    @Override
    protected void reLoadArguments(Bundle bundle) {
        offsetDy = bundle.getFloat("offsetDy");
    }

    public static SelfCenterFragment newInstance() {
        SelfCenterFragment selfCenterFragment = new SelfCenterFragment();
        selfCenterFragment.setArguments(new Bundle());
        return new SelfCenterFragment();
    }

    public SelfCenterFragment() {
    }

    @Override
    public void setPresenter(@NonNull SelfCenterContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            strings.clear();
            strings.add("个人中心个人中心个人中心个人中心");
            strings.add("个人中心个人中心个人中心个人中心");
            strings.add("个人中心个人中心个人中心个人中心");
            strings.add("个人中心个人中心个人中心个人中心");
            strings.add("个人中心个人中心个人中心个人中心");
            strings.add("个人中心个人中心个人中心个人中心");
            strings.add("个人中心个人中心个人中心个人中心");
            strings.add("个人中心个人中心个人中心个人中心");
            strings.add("个人中心个人中心个人中心个人中心");
            strings.add("个人中心个人中心个人中心个人中心");
            strings.add("个人中心个人中心个人中心个人中心");
            strings.add("个人中心个人中心个人中心个人中心");
            strings.add("个人中心个人中心个人中心个人中心");
            strings.add("个人中心个人中心个人中心个人中心");
            adapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        }
    };


    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            offsetDy += dy;
            float alpha = Math.min(offsetDy / actionBarPinHeight, 0.5f);

            Log.e("dy", dy + "  " + offsetDy + "    " + alpha);

            if (alpha < 0.5) {
                appBarBackground.setAlpha(alpha);
            }
        }
    };
}
