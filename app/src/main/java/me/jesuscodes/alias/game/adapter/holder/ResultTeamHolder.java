package me.jesuscodes.alias.game.adapter.holder;

import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import me.jesuscodes.alias.R;
import me.jesuscodes.alias.models.AliasTeam;

/**
 * Created by alex
 */
public class ResultTeamHolder extends RecyclerView.ViewHolder {

    private TextView mTeamName;
    private TextView mTeamPlace;
    private TextView mGuessedCount;
    private String mPlace;

    public ResultTeamHolder(View itemView) {
        super(itemView);

        mTeamName = (TextView) itemView.findViewById(R.id.res_team_name);
        mTeamPlace = (TextView) itemView.findViewById(R.id.res_team_place);
        mGuessedCount = (TextView) itemView.findViewById(R.id.res_team_icon);
    }

    public void bindContent(AliasTeam team, int position) {

        mPlace = getPlaceString(position);

        if (mPlace == null) mTeamPlace.setVisibility(View.GONE);
        else                mTeamPlace.setText(mPlace);

        mTeamName.setText(team.getName());

        mGuessedCount.setText(String.valueOf(team.getGuessedCount()));
        mGuessedCount.getBackground().setColorFilter(
                team.getTeamColor(),
                PorterDuff.Mode.MULTIPLY
        );
    }

    private String getPlaceString(int i) {

        switch (i) {

            case 1: return "Winner";
            case 2: return "2nd place";
            case 3: return "3rd place";
            default: return null;
        }
    }
}
