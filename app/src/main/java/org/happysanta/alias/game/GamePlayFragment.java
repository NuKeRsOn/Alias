package org.happysanta.alias.game;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.happysanta.alias.R;
import org.happysanta.alias.util.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class GamePlayFragment extends BaseFragment {


    public GamePlayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gameplay, container, false);
    }


}
