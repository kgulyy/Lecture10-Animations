package ru.mail.park.lecture10;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        final Fragment fragment;
        switch (item.getItemId()) {
            case R.id.nav_comparision:
                fragment = new ComparisionFragment();
                toolbar.setTitle(R.string.menu_comparision);
                break;
            case R.id.nav_animator:
                fragment = new AnimatorFragment();
                toolbar.setTitle(R.string.menu_animator);
                break;
            case R.id.nav_set:
                fragment = new AnimatorSetFragment();
                toolbar.setTitle(R.string.menu_animator_set);
                break;
            case R.id.nav_interpolator:
                fragment = new InterpolatorFragment();
                toolbar.setTitle(R.string.menu_interpolator);
                break;
            default:
                throw new UnsupportedOperationException();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
