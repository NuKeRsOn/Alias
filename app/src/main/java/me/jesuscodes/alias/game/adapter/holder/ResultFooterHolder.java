package me.jesuscodes.alias.game.adapter.holder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import me.jesuscodes.alias.R;
import me.jesuscodes.alias.game.GameActionsListener;

/**
 * Created by alex
 */
public class ResultFooterHolder extends RecyclerView.ViewHolder {

    private GameActionsListener mActionsListener;
    private Activity mRoot;

    private ImageView mVk;
    private ImageView mTwitter;
    private ImageView mFacebook;

    private Button mReplay;
    private Button mNewGame;

    public ResultFooterHolder(View itemView, Activity actionsListener) {
        super(itemView);

        mActionsListener = (GameActionsListener) actionsListener;
        mRoot = actionsListener;

        mVk = (ImageView) itemView.findViewById(R.id.res_footer_vk);
        mTwitter = (ImageView) itemView.findViewById(R.id.res_footer_twitter);
        mFacebook = (ImageView) itemView.findViewById(R.id.res_footer_facebook);

        mReplay = (Button) itemView.findViewById(R.id.res_footer_replay);
        mNewGame = (Button) itemView.findViewById(R.id.res_footer_new_game);

        initButtons();
    }

    private void initButtons() {

        mReplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mActionsListener.onRestartGame();
            }
        });

        mNewGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mActionsListener.onRecreateGame();
            }
        });

        mVk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent();

                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "ссылка на игру");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"А я поиграл в Alias на андроид!");
                shareIntent.setType("text/plain");

                mRoot.startActivity(Intent.createChooser(shareIntent, "Поделиться"));
            }
        });

        mTwitter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {

                    mRoot.getPackageManager().getPackageInfo("com.twitter.android", 0);

                    Intent shareIntent = new Intent(Intent.ACTION_SEND);

                    shareIntent.setClassName(
                            "com.twitter.android",
                            "com.twitter.android.composer.ComposerActivity"
                    );

                    shareIntent.setType("text/plain");

                    shareIntent.putExtra(
                            Intent.EXTRA_TEXT,
                            "Alias for android!\n"
                                    + "http://play.google.com/store/apps/details?id="
                                    + mRoot.getPackageName()
                    );

                    mRoot.startActivity(shareIntent);

                } catch (Exception exp) {

                    String url =
                            "http://www.twitter.com/intent/" +
                            "tweet?url=google.com&text=Alias for android!";

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));

                    mRoot.startActivity(i);
                }
            }
        });

        mFacebook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "ссылка на игру");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"А я поиграл в Alias на андроид!");
                shareIntent.setType("text/plain");
                mRoot.startActivity(Intent.createChooser(shareIntent, "Поделиться"));

            }
        });
    }
}
