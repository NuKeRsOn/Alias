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
        View rootView = inflater.inflate(R.layout.result_fragment,container,false);
        ListView listView = (ListView) rootView.findViewById(R.id.round_list);
        listView.setAdapter(new RoundResultAdapter(activity, activity.getTeams()));
        nextRoundButton = (Button) rootView.findViewById(R.id.next_round);
        finishedRoundText = (TextView) rootView.findViewById(R.id.round_finished);
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
