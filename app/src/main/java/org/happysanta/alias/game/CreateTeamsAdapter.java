package org.happysanta.alias.game;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

import org.happysanta.alias.models.AliasTeam;

import java.util.ArrayList;

/**
 * Created by Jesus Christ. Amen.
 */
public class CreateTeamsAdapter extends BaseAdapter {
    private ArrayList<AliasTeam> teams;
    private android.content.Context context;


    public CreateTeamsAdapter(Context context) {
        this.context = context;
        this.teams = new ArrayList<AliasTeam>();

        teams.add(new AliasTeam("Львы"));
        teams.add(new AliasTeam("Собаки"));
    }


    @Override
    public int getCount() {

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

        if (position == getCount()-1){
            Button button = new Button(context);
            button.setText("Добавить команду");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    teams.add(new AliasTeam(null));
                    notifyDataSetChanged();
                }
            });
            return button;
        }

        EditText editText = new EditText(context);
        final AliasTeam team = (AliasTeam) getItem(position);
        editText.setText(team.name);
        editText.setHint("Введите имя команды");
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

        return editText;
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
