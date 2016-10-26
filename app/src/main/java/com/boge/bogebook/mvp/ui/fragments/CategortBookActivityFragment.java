package com.boge.bogebook.mvp.ui.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boge.bogebook.R;


public class CategortBookActivityFragment extends Fragment {

    public CategortBookActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categort_book, container, false);
    }
}
