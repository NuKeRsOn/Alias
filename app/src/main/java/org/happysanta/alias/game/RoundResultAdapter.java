package org.happysanta.alias.game;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.happysanta.alias.R;
import org.happysanta.alias.models.AliasTeam;
import org.happysanta.alias.models.AliasWord;

import java.util.ArrayList;

/**
 * Created by Jesus Christ. Amen.
 */
public class RoundResultAdapter extends BaseAdapter {

    private final Activity activity;
    private final ArrayList<AliasTeam> teams;
    private AliasWord currentWord;

    public RoundResultAdapter(Activity activity, ArrayList<AliasTeam> teams){
        this.activity = activity;
        this.teams = teams;
    }
    @Override
    public int getCount() {
        return teams.size();
    }

    @Override
    public Object getItem(int position) {
        return teams.get(position);

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView = activity.getLayoutInflater().inflate(R.layout.item_team_result, null);

        //View teamColor = itemView.findViewById(R.id.team_color);

        TextView scoreView = (TextView) itemView.findViewById(R.id.add_icon);
        TextView placeView = (TextView) itemView.findViewById(R.id.place);
        TextView nameView = (TextView) itemView.findViewById(R.id.name);

        AliasTeam team = (AliasTeam) getItem(position);
        switch (position){
            case 1:
                scoreView.setBackgroundResource(R.drawable.yellow_team);
                break;
            case 2:
                scoreView.setBackgroundResource(R.drawable.green_team);
                break;
            case 3:
                scoreView.setBackgroundResource(R.drawable.blue_team);
                break;
            case 4:
                scoreView.setBackgroundResource(R.drawable.purple_team);
                break;
        }

        int wordsCounter =0;
        for (AliasWord word : team.words) {
            if (word.isGuessed()) {
                wordsCounter++;
            }
        }

        scoreView.setText("" + wordsCounter);
        if(position==0){
            placeView.setText(activity.getResources().getString(R.string.place_winner));
        } else {
            placeView.setText(activity.getResources().getString(R.string.place, position + 1));
        }
//        nameView.setText(team.name);

        return itemView;
    }
}
