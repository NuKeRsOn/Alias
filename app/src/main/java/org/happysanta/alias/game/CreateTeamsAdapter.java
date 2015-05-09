package org.happysanta.alias.game;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

import org.happysanta.alias.R;
import org.happysanta.alias.models.AliasTeam;

import java.util.ArrayList;

/**
 * Created by Jesus Christ. Amen.
 */
public class CreateTeamsAdapter extends BaseAdapter {
    private ArrayList<AliasTeam> teams;
    private android.content.Context context;
    private Activity activity;


    public CreateTeamsAdapter(Context context) {
        this.activity = (Activity) context;
        this.context = context;
        this.teams = new ArrayList<AliasTeam>();
        teams.add(new AliasTeam(context.getResources().getString(R.string.lions)));
        teams.add(new AliasTeam(context.getResources().getString(R.string.dogs)));
    }


    @Override
    public int getCount() {
        if (teams.size() == 5) {
            return 5;
        }
        return teams.size()+1;

    }

    @Override
    public Object getItem(int position) {
        return teams.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        if (position == teams.size()){
            View buttonHolder = LayoutInflater.from(context).inflate(R.layout.item_team_add_button, null);
            Button button = (Button) buttonHolder.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AliasTeam lastTeam = teams.get(teams.size() - 1);
                    if (lastTeam.name == null){
                        return;
                    }
                    teams.add(new AliasTeam(null));
                    notifyDataSetChanged();
                }
            });
            return buttonHolder;
        }
        View editTextHolder = LayoutInflater.from(context).inflate(R.layout.item_team_add, null);
        EditText editText = (EditText) editTextHolder.findViewById(R.id.text);
        View teamColor = editTextHolder.findViewById(R.id.score);
        switch (position){
            case 1:
                teamColor.setBackgroundResource(R.drawable.yellow_team);
                break;
            case 2:
                teamColor.setBackgroundResource(R.drawable.green_team);
                break;
            case 3:
                teamColor.setBackgroundResource(R.drawable.blue_team);
                break;
            case 4:
                teamColor.setBackgroundResource(R.drawable.purple_team);
                break;
        }
        final AliasTeam team = (AliasTeam) getItem  (position);
        editText.setText(team.name);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                team.name = String.valueOf(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText.setFilters(new InputFilter[]{ new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                //todo возвращать source
                return source;
            }
        } });

        return editTextHolder;
    }

    public ArrayList<AliasTeam> getTeams() {

        ArrayList<AliasTeam> convertedTeams = new ArrayList<AliasTeam>();
        for (AliasTeam team : teams) {
            if(team.name!=null && !team.name.equals("")){
                convertedTeams.add(team);
            }
        }
        return convertedTeams;
    }
}
