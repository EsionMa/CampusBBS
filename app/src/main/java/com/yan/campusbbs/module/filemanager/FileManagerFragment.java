package com.yan.campusbbs.module.filemanager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yan.adapter.CustomAdapter;
import com.yan.campusbbs.R;
import com.yan.campusbbs.module.selfcenter.adapterholder.SelfCenterAdapterHelper;
import com.yan.campusbbs.widget.refresh.PtrClassicFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrFrameLayout;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Main UI for the add task screen. Users can enter a task title and description.
 */
public class FileManagerFragment extends Fragment implements FileManagerContract.View {
    List<String> strings;
    CustomAdapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.store_house_ptr_frame)
    PtrClassicFrameLayout storeHousePtrFrame;

    private FileManagerContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_file_manager, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        strings = new ArrayList<>();
        strings.add("文件管理");
        strings.add("文件管理");
        strings.add("文件管理");
        strings.add("文件管理");
        strings.add("文件管理");
        strings.add("文件管理");
        strings.add("文件管理");
        adapter = SelfCenterAdapterHelper.getAdapter(getContext(), strings);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        storeHousePtrFrame.setLastUpdateTimeRelateObject(this);
        storeHousePtrFrame.setPtrHandler(defaultHandler);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    public static FileManagerFragment newInstance() {
        return new FileManagerFragment();
    }

    public FileManagerFragment() {
    }

    @Override
    public void setPresenter(@NonNull FileManagerContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    PtrClassicFrameLayout.PtrDefaultHandler defaultHandler = new PtrClassicFrameLayout.PtrDefaultHandler() {
        public void onRefreshBegin(PtrFrameLayout frame) {
            strings.clear();
            strings.add("文件管理文件管理文件管理文件管理");
            strings.add("文件管理文件管理文件管理文件管理");
            strings.add("文件管理文件管理文件管理文件管理");
            strings.add("文件管理文件管理文件管理文件管理");
            strings.add("文件管理文件管理文件管理文件管理");
            strings.add("文件管理文件管理文件管理文件管理");
            strings.add("文件管理文件管理文件管理文件管理");
            adapter.notifyDataSetChanged();
            storeHousePtrFrame.refreshFinish();
        }
    };
}