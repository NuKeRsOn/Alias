package org.happysanta.alias.game;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.happysanta.alias.R;

/**
 * Created by Jesus Christ. Amen.
 */
public class RoundResultFragment extends Fragment {

    private Button nextRoundButton;
    private TextView finishedRoundText;
    private GameActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_result,container,false);

        View headerView = inflater.inflate(R.layout.fragment_result_header, null);
        View footerView = inflater.inflate(R.layout.fragment_result_footer, null);

        ListView listView = (ListView) rootView.findViewById(R.id.round_list);
        nextRoundButton = (Button) footerView.findViewById(R.id.next_round);
        finishedRoundText = (TextView) rootView.findViewById(R.id.round_finished);


        listView.addHeaderView(headerView);
        listView.addFooterView(footerView);
        listView.setAdapter(new RoundResultAdapter(activity, activity.getTeams()));

        nextRoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewed();
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (GameActivity) activity;
    }

    private void viewed(){
        GameActivity gameActivity = (GameActivity) getActivity();
        gameActivity.roundResultViewed();
    }

}
