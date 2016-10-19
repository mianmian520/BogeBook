package com.boge.bogebook.mvp.ui.fragments.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.component.DaggerFragmentComponent;
import com.boge.bogebook.component.FragmentComponent;

import butterknife.ButterKnife;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/14
 */

public abstract class BaseFragment extends Fragment {

    private View view;

    protected FragmentComponent mFragmentComponent;

    protected abstract int getLayoutResId();

    protected abstract void initDatas();

    protected abstract void initInjector();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(((BookApplication) getActivity().getApplication()).getmApplicationComponent())
//                .fragmentModule(new FragmentModule(this))
                .build();
        initInjector();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(getLayoutResId(), container, false);
            ButterKnife.bind(this, view);
            initDatas();
        }
        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        view = null;
    }

    public FragmentComponent getFragmentComponent() {
        return mFragmentComponent;
    }
}
