package pe.apiconz.android.sanisidromovil.presentation.activities;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import pe.apiconz.android.sanisidromovil.R;
import pe.apiconz.android.sanisidromovil.application.SanIsidroMovilApp;
import pe.apiconz.android.sanisidromovil.model.entities.EventEntity;
import pe.apiconz.android.sanisidromovil.presentation.fragments.CardContentAllFragment;
import pe.apiconz.android.sanisidromovil.presentation.fragments.CardContentTodayFragment;
import pe.apiconz.android.sanisidromovil.presentation.fragments.CardContentTomorrowFragment;
import pe.apiconz.android.sanisidromovil.utils.Utils;


public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();

    @Bind(R.id.drawer)
    protected DrawerLayout mDrawerLayout;

    @Bind(R.id.tabs)
    protected TabLayout tabs;

    @Bind(R.id.viewpager)
    protected ViewPager viewPager;


    protected List<EventEntity> currentActitivies;
    protected List<EventEntity> tomorrowActitivies;
    protected List<EventEntity> soonActitivies;

    @Override
    protected void onCreateView() {

        setDataFromServices();
        setSupportActionBar();
        setupViewPager();
        setTabLayout();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    // This method will trigger on item Click of navigation menu
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Set item in checked state
                        menuItem.setChecked(true);

                        // TODO: Manejar la Navegacion

                        // Closing drawer on item click
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

    private void setDataFromServices() {
        SanIsidroMovilApp application = (SanIsidroMovilApp) getApplicationContext();
        Firebase firebase = application.getFirebaseReferenceForActivities();

        currentActitivies = new ArrayList<EventEntity>();
        getCurrentActivities(firebase);

        tomorrowActitivies = new ArrayList<EventEntity>();
        getNextDayActivities(firebase);

        soonActitivies = new ArrayList<EventEntity>();
        getSoonActivities(firebase);


    }

    private void getNextDayActivities(Firebase firebase) {
        String fechaRequerida = Utils.getCurrentDate(2);
        Query query = firebase.orderByChild("FECHA").startAt(fechaRequerida).endAt(fechaRequerida).limitToFirst(50);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "count" + dataSnapshot.getChildrenCount());
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    String nombreEvento = postSnapshot.child("EVENTO").getValue().toString();
                    String fechaEvento = postSnapshot.child("FECHA").getValue().toString();
                    String horaEvento = postSnapshot.child("HORA").getValue().toString();
                    int id = postSnapshot.child("ID").getValue(int.class);

                    EventEntity eventEntity = new EventEntity(nombreEvento, fechaEvento, horaEvento, id);
                    tomorrowActitivies.add(eventEntity);


                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void getCurrentActivities(Firebase firebase) {
        String fechaRequerida = Utils.getCurrentDate(1);
        Query query = firebase.orderByChild("FECHA").startAt(fechaRequerida).endAt(fechaRequerida).limitToFirst(50);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "count" + dataSnapshot.getChildrenCount());
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    String nombreEvento = postSnapshot.child("EVENTO").getValue().toString();
                    String fechaEvento = postSnapshot.child("FECHA").getValue().toString();
                    String horaEvento = postSnapshot.child("HORA").getValue().toString();
                    int id = postSnapshot.child("ID").getValue(int.class);


                    EventEntity eventEntity = new EventEntity(nombreEvento, fechaEvento, horaEvento, id);
                    currentActitivies.add(eventEntity);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void getSoonActivities(Firebase firebase) {
        Query query = firebase.orderByChild("FECHA").limitToFirst(50);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "count" + dataSnapshot.getChildrenCount());
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    String nombreEvento = postSnapshot.child("EVENTO").getValue().toString();
                    String fechaEvento = postSnapshot.child("FECHA").getValue().toString();
                    String horaEvento = postSnapshot.child("HORA").getValue().toString();
                    int id = postSnapshot.child("ID").getValue(int.class);

                    EventEntity eventEntity = new EventEntity(nombreEvento, fechaEvento, horaEvento, id);
                    soonActitivies.add(eventEntity);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void setupViewPager() {

        Adapter adapter = new Adapter(getSupportFragmentManager());

        CardContentTodayFragment todayFragment = new CardContentTodayFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("currentActivities", (ArrayList<? extends Parcelable>) currentActitivies);
        todayFragment.setArguments(bundle);
        adapter.addFragment(todayFragment, getString(R.string.tab_hoy));

        CardContentTomorrowFragment tomorrowFragment = new CardContentTomorrowFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putParcelableArrayList("nextDayActivities", (ArrayList<? extends Parcelable>) tomorrowActitivies);
        tomorrowFragment.setArguments(bundle2);
        adapter.addFragment(tomorrowFragment, getString(R.string.tab_manana));

        CardContentAllFragment allFragment = new CardContentAllFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putParcelableArrayList("soonActitivies", (ArrayList<? extends Parcelable>) soonActitivies);
        allFragment.setArguments(bundle3);
        adapter.addFragment(allFragment, getString(R.string.tab_semana));

        viewPager.setAdapter(adapter);

    }

    private void setTabLayout() {
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    protected void setSupportActionBar() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
