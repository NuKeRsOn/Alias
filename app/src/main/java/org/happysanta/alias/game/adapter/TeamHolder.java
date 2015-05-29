package org.happysanta.alias.game.adapter;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import org.happysanta.alias.R;
import org.happysanta.alias.models.AliasTeam;

/**
 * Created by alex
 */
public class TeamHolder extends RecyclerView.ViewHolder {

    public static final int TEAM_RED = Color.parseColor("#e74c3c");
    public static final int TEAM_YELLOW = Color.parseColor("#f39c12");
    public static final int TEAM_GREEN = Color.parseColor("#2ecc71");
    public static final int TEAM_BLUE = Color.parseColor("#3498db");
    public static final int TEAM_PURPLE = Color.parseColor("#9b59b6");

    public ImageView mTeamIcon;
    public EditText mTeamNameEdit;

    public TeamHolder(View itemView) {
        super(itemView);
        initContent(itemView);
    }

    public void bindContent(AliasTeam team, int position) {

        mTeamIcon.getDrawable().setColorFilter(
                selectColor(position),
                PorterDuff.Mode.MULTIPLY
        );

        mTeamNameEdit.setText(team.getName());
    }

    private void initContent(View itemView) {

        mTeamIcon = (ImageView) itemView.findViewById(R.id.item_team_color);
        mTeamNameEdit = (EditText) itemView.findViewById(R.id.item_team_enter_name);
    }

    public int selectColor(int position) {

        switch (position) {

            case 0: return TEAM_RED;
            case 1: return TEAM_YELLOW;
            case 2: return TEAM_GREEN;
            case 3: return TEAM_BLUE;
            case 4: return TEAM_PURPLE;
            default: return TEAM_RED;
        }
    }
}
