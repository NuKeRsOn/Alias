package org.happysanta.alias.game;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.happysanta.alias.R;
import org.happysanta.alias.models.AliasTeam;

import java.util.ArrayList;

/**
 * Created by Jesus Christ. Amen.
 */
public class RoundResultAdapter extends BaseAdapter {

    private final Activity activity;
    private final ArrayList<AliasTeam> teams;

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

        View teamColor = itemView.findViewById(R.id.team_color);

        TextView scoreView = (TextView) itemView.findViewById(R.id.score);
        TextView placeView = (TextView) itemView.findViewById(R.id.place);
        TextView nameView = (TextView) itemView.findViewById(R.id.name);

        AliasTeam team = (AliasTeam) getItem(position);
        switch (position){
            case 1:
                teamColor.setBackgroundResource(R.drawable.yellow_team);
                break;
            case 2:
                teamColor.setBackgroundResource(R.drawable.green_team);
                break;
            case 3:
                teamColor.setBackgroundResource(R.drawable.blue_team);
                break;
            case 4:
                teamColor.setBackgroundResource(R.drawable.purple_team);
                break;
        }


        scoreView.setText("" + team.words.size());
        placeView.setText("Place " + (position + 1));
        nameView.setText(team.name);

        return itemView;
    }
}
