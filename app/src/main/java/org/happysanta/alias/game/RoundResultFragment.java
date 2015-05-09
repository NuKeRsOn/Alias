package org.happysanta.alias.game;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.happysanta.alias.R;

/**
 * Created by Jesus Christ. Amen.
 */
public class RoundResultFragment extends Fragment {

    private Button nextRoundButton;
    private TextView finishedRoundText;
    private GameActivity activity;
    private Button replayButton;
    private ImageButton twitterButton;
    private ImageButton facebookButton;
    private ImageButton vkButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_result,container,false);

        View headerView = inflater.inflate(R.layout.fragment_result_header, null);
        View footerView = inflater.inflate(R.layout.fragment_result_footer, null);

        ListView listView = (ListView) rootView.findViewById(R.id.round_list);
        nextRoundButton = (Button) footerView.findViewById(R.id.next_round);
        finishedRoundText = (TextView) rootView.findViewById(R.id.round_finished);
        replayButton = (Button) footerView.findViewById(R.id.replay);
        twitterButton = (ImageButton) footerView.findViewById(R.id.twitterButton);
        facebookButton = (ImageButton) footerView.findViewById(R.id.facebookButton);
        vkButton = (ImageButton) footerView.findViewById(R.id.vkButton);

        listView.addHeaderView(headerView, null, false);
        listView.addFooterView(footerView, null, false);
        listView.setAdapter(new RoundResultAdapter(activity, activity.getTeams()));


        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                again();
            }
        });

        nextRoundButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                viewed();
            }
        });
        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getActivity().getPackageManager().getPackageInfo("com.twitter.android", 0);
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setClassName("com.twitter.android", "com.twitter.android.composer.ComposerActivity");
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, "Alias for android!\n"+ "http://play.google.com/store/apps/details?id=" + getActivity().getPackageName());
                    startActivity(intent);
                }catch (Exception exp){
                    String url = "http://www.twitter.com/intent/tweet?url=google.com&text=Alias for android!";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            }
        });

        vkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "ссылка на игру");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"А я поиграл в Alias на андроид!");
                shareIntent.setType("text/plain");
                getActivity().startActivity(Intent.createChooser(shareIntent, "Поделиться"));
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "ссылка на игру");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"А я поиграл в Alias на андроид!");
                shareIntent.setType("text/plain");
                getActivity().startActivity(Intent.createChooser(shareIntent, "Поделиться"));
            }
        });
        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (GameActivity) activity;
    }

    private void viewed(){
        GameActivity gameActivity = (GameActivity) getActivity();
        gameActivity.roundResultViewed();
    }


    private void again(){
        GameActivity prepareFragment = (GameActivity) getActivity();
        prepareFragment.playRound();
    }
}
