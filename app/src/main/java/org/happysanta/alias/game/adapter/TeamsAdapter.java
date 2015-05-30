package org.happysanta.alias.game.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.happysanta.alias.R;
import org.happysanta.alias.models.AliasTeam;
import org.happysanta.alias.util.TextWatcherAdapter;

import java.util.ArrayList;

/**
 * Created by alex
 */
public class TeamsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_TEAM = 1;
    private static final int TYPE_ADD_BUTTON = 2;

    private ArrayList<AliasTeam> mAliasTeamList = new ArrayList<AliasTeam>() {{

        add(new AliasTeam("Lions"));
        add(new AliasTeam("Dawgs"));

    }};

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder;

        switch (viewType) {

            case TYPE_TEAM : {

                View itemView = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_team_add, parent, false);

                holder = new TeamHolder(itemView);
                break;
            }

            case TYPE_ADD_BUTTON : {

                View itemView = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_team_add_button, parent, false);

                holder = new AddButtonHolder(itemView);
                break;
            }

            default: holder = null;
        }

        return holder;
    }

    @Override
    public int getItemViewType(int position) {

        return (mAliasTeamList.size() == position) ? TYPE_ADD_BUTTON : TYPE_TEAM;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof TeamHolder) {

            final AliasTeam team = mAliasTeamList.get(position);

            TeamHolder teamHolder = (TeamHolder) holder;
            teamHolder.bindContent(team, position);

            teamHolder.mTeamNameEdit.addTextChangedListener(new TextWatcherAdapter() {
                @Override
                public void afterTextChanged(Editable s) {
                    super.afterTextChanged(s);

                    team.setName(s.toString().trim());
                }
            });

        } else {

            AddButtonHolder addButtonHolder = (AddButtonHolder) holder;

            addButtonHolder.bindContent(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mAliasTeamList.add(new AliasTeam(""));
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        if (mAliasTeamList.size() == 5) return 5;

        return mAliasTeamList.size() + 1;
    }

    public ArrayList<AliasTeam> getAliasTeams() {
        return mAliasTeamList;
    }
}