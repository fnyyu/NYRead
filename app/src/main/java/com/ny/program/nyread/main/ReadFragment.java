package com.ny.program.nyread.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ny.program.nyread.R;

/**
 * Created by ny on 2018/4/23.
 */

public class ReadFragment extends Fragment {

    private View viewRoot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (viewRoot == null){
            viewRoot = inflater.inflate(R.layout.fragment_read, container, false);
        }

        return viewRoot;
    }
}
