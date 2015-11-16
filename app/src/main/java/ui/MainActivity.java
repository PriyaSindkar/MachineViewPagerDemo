package ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageView;

import com.example.priyasindkar.machineviewpagerdemo.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    ViewPager pager;
    SmartTabLayout tabHost;
    TabsPagerAdapter pagerAdapter;
    ImageView imgListGrid;
    public static boolean isList = true;
    private  int currentTabItem = 0;
    private List<MachineModel> machines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = (SmartTabLayout) this.findViewById(R.id.tabs);
        pager = (ViewPager) this.findViewById(R.id.pager);
        imgListGrid = (ImageView) findViewById(R.id.imgListGrid);


        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        tabHost.setDistributeEvenly(true);

        //init machine-list
        machines = new ArrayList<>();
        machines.add(new MachineModel(1, "Machine 1"));
        machines.add(new MachineModel(2, "Machine 2"));
        machines.add(new MachineModel(3, "Machine 3"));

        // init view pager
        pagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(), isList, machines);
        pager.setAdapter(pagerAdapter);
        tabHost.setViewPager(pager);

        tabHost.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentTabItem = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        imgListGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isList = !isList;
                pagerAdapter.setIsListView(isList);
                pagerAdapter.notifyDataSetChanged();
                pager.setCurrentItem(currentTabItem);
            }
        });

    }
}

class TabsPagerAdapter extends FragmentStatePagerAdapter {
    boolean isListView = true;
    private List<MachineModel> machines = new ArrayList<>();

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public TabsPagerAdapter(FragmentManager fm, boolean _isListView, List<MachineModel> _machines) {
        super(fm);
        this.isListView = _isListView;
        this.machines = _machines;
    }

    public void setIsListView(boolean isListView) {
        this.isListView = isListView;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int index) {
        return MachineFragment.newInstance(machines.get(index).getId(), machines.get(index).getName(), isListView);
    }



    @Override
    public CharSequence getPageTitle(int position) {
        //return "TAB "+ (position);
        return machines.get(position).getName();
    }

    @Override
    public int getCount() {
        return machines.size();
    }
}


class MachineModel {
    private int id;
    private String name;

    public MachineModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

