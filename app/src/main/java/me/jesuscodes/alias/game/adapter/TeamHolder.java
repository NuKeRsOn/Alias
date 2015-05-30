package me.jesuscodes.alias.game.adapter;

import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import me.jesuscodes.alias.R;
import me.jesuscodes.alias.models.AliasTeam;
import me.jesuscodes.alias.util.ColorUtil;

/**
 * Created by alex
 */
public class TeamHolder extends RecyclerView.ViewHolder {

    public ImageView mTeamIcon;
    public EditText mTeamNameEdit;

    public TeamHolder(View itemView) {
        super(itemView);
        initContent(itemView);
    }

    public void bindContent(AliasTeam team, int position) {

        mTeamIcon.getDrawable().setColorFilter(
                ColorUtil.selectColor(position),
                PorterDuff.Mode.MULTIPLY
        );

        mTeamNameEdit.setText(team.getName());
    }

    private void initContent(View itemView) {
        mTeamIcon = (ImageView) itemView.findViewById(R.id.item_team_color);
        mTeamNameEdit = (EditText) itemView.findViewById(R.id.item_team_enter_name);
    }
}
