package com.notrealbutter.leaguefitness.lof.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.notrealbutter.leaguefitness.lof.Control.RiotController;
import com.notrealbutter.leaguefitness.lof.Model.MatchListAdapter;
import com.notrealbutter.leaguefitness.lof.Model.MatchListItem;
import com.notrealbutter.leaguefitness.lof.R;

import java.util.ArrayList;

public class GameStatActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Intent mainIntent;
    Intent gameStatIntent;
    Intent exerciseIntent;

    DrawerLayout mDrawerLayout;
    String[] navMenuTitles;


    public RiotController riotController;
    private TextView summNameBox;
    private TextView summIDBox;
    private TextView summLVLBox;

    ArrayList<MatchListItem> matchListItems = new ArrayList<>();
    MatchListAdapter matchListAdapter;
    ListView matchListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMenus();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        summNameBox.setText(riotController.summonerAccount.getNameCollected());
        summLVLBox.setText(" " + riotController.summonerAccount.getSummonerLevelCollected());
        summIDBox.setText(" " + riotController.summonerAccount.getSummonerIDCollected());
    }

    @Override
    public void onResume()
    {
        super.onResume();
        initMatchList();
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(mainIntent);
            Toast toast1 = Toast.makeText(getApplicationContext(), "this works1", Toast.LENGTH_LONG);
            toast1.show();
        } else if (id == R.id.nav_game_stat) {
            Toast toast2 = Toast.makeText(getApplicationContext(),"this works2",Toast.LENGTH_LONG);
            toast2.show();
        } else if (id == R.id.nav_exercise) {
            startActivity(exerciseIntent);
            Toast toast3 = Toast.makeText(getApplicationContext(),"this works3",Toast.LENGTH_LONG);
            toast3.show();
        } else if (id == R.id.nav_about) {
            Toast toast4 = Toast.makeText(getApplicationContext(),"this works4",Toast.LENGTH_LONG);
            toast4.show();
        } else if (id == R.id.nav_share) {
            Toast toast5= Toast.makeText(getApplicationContext(),"this works5",Toast.LENGTH_LONG);
            toast5.show();
        } else if (id == R.id.nav_send) {
            Toast toast6 = Toast.makeText(getApplicationContext(),"this works6",Toast.LENGTH_LONG);
            toast6.show();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void initMenus(){
        setContentView(R.layout.activity_game_stat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.riotController = MainActivity.riotControl;

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mainIntent = new Intent(mDrawerLayout.getContext(), MainActivity.class);
        gameStatIntent = new Intent(mDrawerLayout.getContext(), GameStatActivity.class);
        exerciseIntent = new Intent(mDrawerLayout.getContext(), ExerciseActivity.class);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_gs);
        navigationView.setNavigationItemSelectedListener(this);
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        matchListView = (ListView)findViewById(R.id.recentMatchListBoxGS);

        initViews();
        initMatchList();
    }

    public void initViews()
    {
        summNameBox = (TextView) findViewById(R.id.summonerNameBoxGS);
        summIDBox = (TextView) findViewById(R.id.summonerIDBoxGS);
        summLVLBox = (TextView) findViewById(R.id.summonerLevelBoxGS);
    }

    public void initMatchList(){
        System.out.println("Init Match List is Starting");

        for(int i=0; i < riotController.match.getRecentGamesCollected().size(); i++)
        {
            matchListItems.add(
                    new MatchListItem(riotController.match.getRecentGamesCollected().get(i).getChampion().getName(),
                        riotController.match.getRecentGamesCollected().get(i).getType().toString(),
                        riotController.match.getRecentGamesCollected().get(i).getStats().getKills(),
                        riotController.match.getRecentGamesCollected().get(i).getStats().getDeaths(),
                        riotController.match.getRecentGamesCollected().get(i).getStats().getAssists(),
                        riotController.match.getRecentGamesCollected().get(i).getStats().getMinionsKilled(),
                        ((int) riotController.match.getRecentGamesCollected().get(i).getStats().getTimePlayed())));
        }

        matchListAdapter = new MatchListAdapter(getApplicationContext(), R.layout.game_view, matchListItems);
        matchListView.setAdapter(matchListAdapter);
    }
}