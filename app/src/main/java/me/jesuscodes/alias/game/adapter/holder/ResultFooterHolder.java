package me.jesuscodes.alias.game.adapter.holder;

import android.app.Activity;
import android.content.Intent;
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
    private String mShareLink;

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

        mShareLink = "http://play.google.com/store/apps/details?id=" + mRoot.getPackageName();

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

                share(SocialNetwork.VK);
            }
        });

        mTwitter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                share(SocialNetwork.TWITTER);
            }
        });

        mFacebook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                share(SocialNetwork.FACEBOOK);

            }
        });
    }

    private void share(SocialNetwork sn) {

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        try {

            mRoot.getPackageManager().getPackageInfo(sn.packageName, 0);

            if (sn == SocialNetwork.TWITTER) {

                shareIntent.setClassName(
                        "com.twitter.android",
                        "com.twitter.android.composer.ComposerActivity"
                );
            }

            shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Alias for android!\n" + mShareLink
            );

            mRoot.startActivity(shareIntent);

        } catch (Exception e) {

            e.printStackTrace();

            shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Alias for android!\n" + mShareLink
            );

            mRoot.startActivity(Intent.createChooser(shareIntent, "Поделиться"));
        }

    }

    private enum SocialNetwork {

        TWITTER("com.twitter.android"),
        FACEBOOK("com.facebook.android"),
        VK("com.vkontakte.android");

        final String packageName;

        SocialNetwork(String packageName) {

            this.packageName = packageName;
        }
    }

}
