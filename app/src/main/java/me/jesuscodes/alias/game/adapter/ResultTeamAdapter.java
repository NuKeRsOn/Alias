package me.jesuscodes.alias.game.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import me.jesuscodes.alias.R;
import me.jesuscodes.alias.models.AliasWord;

/**
 * Created by alex
 */
public class ResultTeamAdapter extends RecyclerView.Adapter<ResultTeamAdapter.ViewHolder> {

    private ArrayList<AliasWord> mAliasWordList;

    public ResultTeamAdapter(ArrayList<AliasWord> aliasWordList) {
        
        mAliasWordList = aliasWordList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_result_word, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        AliasWord word = mAliasWordList.get(position);

        holder.mTeamWordRus.setText(word.bodyRu);
        holder.mTeamWordEng.setText(word.bodyEn);

        holder.mTeamWordIcon.setImageResource(
                word.isGuessed()
                        ?
                        R.drawable.v_green
                        :
                        R.drawable.x_red
        );
    }

    @Override
    public int getItemCount() {
        return mAliasWordList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTeamWordEng;
        public TextView mTeamWordRus;
        public ImageView mTeamWordIcon;

        public ViewHolder(View itemView) {
            super(itemView);

            mTeamWordRus = (TextView) itemView.findViewById(R.id.team_res_word_rus);
            mTeamWordEng = (TextView) itemView.findViewById(R.id.team_res_word_eng);

            mTeamWordIcon = (ImageView) itemView.findViewById(R.id.team_res_word_icon);

        }

    }
}