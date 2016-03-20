package com.feghali.obsoft.applicationfeghali;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    Home home;


    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */

    private CharSequence mTitle;


   @Override
    public void onBackPressed() {

   FragmentManager manager = getSupportFragmentManager();
       manager.getBackStackEntryCount();

    if(manager.getBackStackEntryCount()==1)
       {
           mTitle=getString(R.string.title_section1);
       }

       restoreActionBar();
       super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        home=new Home();

        FragmentManager home_manager=getSupportFragmentManager();
        home_manager.beginTransaction().replace(R.id.container,home).commit();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
        getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();


        // Set up the drawer.

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Fragment object_Selected;

       FragmentManager fragmentManager = getSupportFragmentManager();
        switch(position)
        {

            case 1:

                object_Selected=new Home();
                fragmentManager.beginTransaction().replace(R.id.container,object_Selected).addToBackStack(null).commit();

                break;

            case 2:
                object_Selected=new News();
               fragmentManager.beginTransaction()
                      .replace(R.id.container, object_Selected).addToBackStack(null)
                       .commit();
                break;

            case 3:
                object_Selected=new Calendar();

               fragmentManager.beginTransaction()
                      .replace(R.id.container, object_Selected).addToBackStack(null)
                      .commit();

                break;

            case 4:
               object_Selected=new Palmares();

            fragmentManager.beginTransaction()
                      .replace(R.id.container, object_Selected).addToBackStack(null)
                       .commit();

                break;

            case 5:
                object_Selected=new Results();

                fragmentManager.beginTransaction()
                       .replace(R.id.container, object_Selected).addToBackStack(null)
                      .commit();

                break;

            case 6:
                object_Selected=new About();

                fragmentManager.beginTransaction()
                    .replace(R.id.container, object_Selected).addToBackStack(null)
                       .commit();

                break;


        }
    }

    public void onSectionAttached(int number) {
        switch (number) {

            case 1:
                mTitle=getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle=getString(R.string.title_section5);
                break;
            case 6:
                mTitle=getString(R.string.title_section6);
                break;
        }
    }

    public void restoreActionBar() {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
     /*   if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            return rootView;
        }

    }

}
