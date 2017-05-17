package com.ricardodecarvalho.planningpoker;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity, new ListCardFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_menu_about:
                showAbout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showAbout() {
        AboutFragment aboutFragment = (AboutFragment) getSupportFragmentManager()
                .findFragmentByTag("ABOUT_FRAGMENT");
        if (aboutFragment == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_activity, new AboutFragment(), "ABOUT_FRAGMENT")
                    .addToBackStack(null)
                    .commit();
        }
    }

}
