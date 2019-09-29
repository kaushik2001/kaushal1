package com.appsnipp.loginsamples.Navigation_Profile;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatDelegate;
import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Toast;

import com.appsnipp.loginsamples.Navigation_Profile.ui.account.AccountFragment;
import com.appsnipp.loginsamples.Navigation_Profile.ui.buildingdetails.BuildingDetailsFragment;
import com.appsnipp.loginsamples.Navigation_Profile.ui.complain.ComplainFragment;
import com.appsnipp.loginsamples.Navigation_Profile.ui.dashboard.DashBoardFragment;
import com.appsnipp.loginsamples.Navigation_Profile.ui.event.EventFragment;
import com.appsnipp.loginsamples.Navigation_Profile.ui.noticeboard.NoticeBoardFragment;
import com.appsnipp.loginsamples.Navigation_Profile.ui.profile.ProfileFragment;
import com.appsnipp.loginsamples.Navigation_Profile.ui.resource.ResourceFragment;
import com.appsnipp.loginsamples.Navigation_Profile.ui.document.DocumentFragment;
import com.appsnipp.loginsamples.Navigation_Profile.ui.members.MembersFragment;
import com.appsnipp.loginsamples.Navigation_Profile.ui.electionandpoll.ElectionFragment;
import com.appsnipp.loginsamples.Navigation_Profile.ui.visitor.VisitorFragment;
import com.appsnipp.loginsamples.R;

import java.lang.ref.WeakReference;

import static androidx.navigation.ui.NavigationUI.onNavDestinationSelected;

public class Navigation_Activity extends AppCompatActivity {
    Fragment fragment;
    FragmentManager manager;
    FragmentTransaction transaction;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;
    private AppBarConfiguration mAppBarConfiguration;
//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//            int id = item.getItemId();
//            navigationView=findViewById(R.id.nav_view);
//            if (id == R.id.navB_home) {
//                navigationView.setCheckedItem(R.id.nav_home);
//                fragment = new DashBoardFragment();
//
//
//            } else if (id == R.id.navB_building) {
//                navigationView.setCheckedItem(R.id.nav_building);
//                fragment = new BuildingDetailsFragment();
//
//
//            } else if (id == R.id.navB_profile) {
//                navigationView.setCheckedItem(R.id.nav_profile);
//                fragment = new ProfileFragment();
//
//            }
//            else if (id == R.id.navB_notice) {
//                navigationView.setCheckedItem(R.id.nav_notice);
//                fragment = new NoticeBoardFragment();
//
//            }else if (id == R.id.navB_menu) {
//                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//                drawer.openDrawer(GravityCompat.START);
//                return true;
//            }
//
//            transaction = manager.beginTransaction();
//            transaction.replace(R.id.nav_host_fragment, fragment,"A");
//            transaction.addToBackStack("addA");
//            manager.popBackStack();
//            transaction.commit();
//
//            return true;
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_);
        Toolbar toolbar = findViewById(R.id.toolbar);
        manager=getSupportFragmentManager();
        setSupportActionBar(toolbar);
//      /*  FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
         navigationView =findViewById(R.id.nav_view);
        bottomNavigationView = findViewById(R.id.nav_view_bottom);
        //bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigationView.setSelectedItemId(R.id.navB_home);
        navigationView=findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_home);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_account, R.id.nav_member,
                R.id.nav_election, R.id.nav_document, R.id.nav_resource,R.id.nav_profile,
                R.id.nav_building,R.id.nav_complain,R.id.navB_home,R.id.navB_notice,
                R.id.navB_building,R.id.navB_profile,
                R.id.nav_event,R.id.nav_notice,R.id.nav_visitor)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
        NavigationUI.setupWithNavController(navigationView, navController);
        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
  if(R.id.navB_home!=bottomNavigationView.getSelectedItemId()) {
      bottomNavigationView.setSelectedItemId(R.id.navB_home);
  }
        super.onBackPressed();
    }
    //    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                int id = menuItem.getItemId();
//
//        if (id == R.id.nav_home) {
//
//
//
//            Toast.makeText(this, "Activity is refresh", Toast.LENGTH_SHORT).show();
//            fragment = new DashBoardFragment();
//
//
//        } else if (id == R.id.nav_account) {
//            Toast.makeText(this, "Activity is refresh", Toast.LENGTH_SHORT).show();
//            fragment = new AccountFragment();
//
//
//        } else if (id == R.id.nav_member) {
//            Toast.makeText(this, "Activity is refresh", Toast.LENGTH_SHORT).show();
//
//            fragment = new MembersFragment();
//
//        }
//        else if (id == R.id.nav_notice) {
//
//            fragment = new NoticeBoardFragment();
//
//        }else if (id == R.id.nav_complain) {
//
//            fragment = new ComplainFragment();
//
//        }else if (id == R.id.nav_visitor) {
//            fragment = new VisitorFragment();
//
//        } else if (id == R.id.nav_election) {
//            fragment = new ElectionFragment();
//
//        } else if (id == R.id.nav_building) {
//
//            fragment = new BuildingDetailsFragment();
//
//        }else if (id == R.id.nav_document) {
//            fragment = new DocumentFragment();
//
//        }else if (id == R.id.nav_event) {
//            fragment = new EventFragment();
//
//
//        } else if (id == R.id.nav_resource) {
//            fragment = new ResourceFragment();
//
//
//        }
//        else if (id == R.id.nav_profile) {
//
//            fragment = new ProfileFragment();
//
//
//        }
//
//
//
//        transaction = manager.beginTransaction();
//        transaction.add(R.id.nav_host_fragment, fragment, "A");
//        transaction.addToBackStack("addA");
//        transaction.commit();
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//


}

