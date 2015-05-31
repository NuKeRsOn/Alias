package me.jesuscodes.alias.game.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.jesuscodes.alias.R;
import me.jesuscodes.alias.game.adapter.holder.ResultFooterHolder;
import me.jesuscodes.alias.game.adapter.holder.ResultHeaderHolder;
import me.jesuscodes.alias.game.adapter.holder.ResultTeamHolder;
import me.jesuscodes.alias.models.AliasTeam;

/**
 * Created by alex
 */
public class ResultGameAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_HEADER = 1;
    private static final int VIEW_TYPE_FOOTER = 2;
    private static final int VIEW_TYPE_TEAM = 3;

    private Activity mListener;
    private ArrayList<AliasTeam> mAliasTeamList;

    public ResultGameAdapter(ArrayList<AliasTeam> aliasTeamList) {
        
        mAliasTeamList = aliasTeamList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;
        RecyclerView.ViewHolder holder;

        switch (viewType) {

            case VIEW_TYPE_HEADER: {

                itemView = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_result_header, parent, false);

                holder = new ResultHeaderHolder(itemView);
                break;
            }

            case VIEW_TYPE_TEAM: {

                itemView = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_result_team, parent, false);

                holder = new ResultTeamHolder(itemView);
                break;
            }

            case VIEW_TYPE_FOOTER: {

                itemView = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_result_footer, parent, false);

                holder = new ResultFooterHolder(itemView, mListener);
                break;
            }

            default: return null;
        }

        return holder;
    }

    @Override
    public int getItemViewType(int position) {

        return position == 0
                ?
                VIEW_TYPE_HEADER
                :
                (position == (mAliasTeamList.size() + 1)
                        ?
                        VIEW_TYPE_FOOTER
                        :
                        VIEW_TYPE_TEAM
                );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {

            case VIEW_TYPE_TEAM: {

                AliasTeam team = mAliasTeamList.get(position - 1);
                ResultTeamHolder teamHolder = (ResultTeamHolder) holder;
                teamHolder.bindContent(team, position);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mAliasTeamList.size() + 2;
    }

    public void setRootActivity(Activity a) {
        mListener = a;
    }
}