package org.happysanta.alias.start;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.happysanta.alias.R;

/**
 * Created by Jesus Christ. Amen.
 */
public class TourFragment extends Fragment {
    private ImageView image;
    private TextView description;
    private View next;

    public static Fragment getInstance(int position) {
        Fragment fragment = new TourFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tour, container, false);
        image = (ImageView) rootView.findViewById(R.id.image);
        description = (TextView) rootView.findViewById(R.id.description);
        next = rootView.findViewById(R.id.next);

        int position = getArguments().getInt("position");
        switch (position) {
            case 0:
                description.setText(R.string.dictionaries_activity_title);
                image.setImageResource(R.drawable.pic1);
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((StartActivity)getActivity()).next();
                    }
                });
                break;
            case 1:
                description.setText(R.string.dictionaries_activity_title);
                image.setImageResource(R.drawable.pic2);
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((StartActivity)getActivity()).next();
                    }
                });
                break;
            case 2:
                description.setText(R.string.dictionaries_activity_title);
                image.setImageResource(R.drawable.pic3);
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((StartActivity)getActivity()).showMenu();
                    }
                });
                break;
        }

        return rootView;
    }
}
