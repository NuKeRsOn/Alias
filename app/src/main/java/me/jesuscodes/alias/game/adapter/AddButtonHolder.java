package me.jesuscodes.alias.game.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by alex
 */
public class AddButtonHolder extends RecyclerView.ViewHolder {

    private View mAddButton;

    public AddButtonHolder(View itemView) {
        super(itemView);

        mAddButton = itemView;

    }

    public void bindContent(View.OnClickListener onClickListener) {

        mAddButton.setOnClickListener(onClickListener);
    }
}
