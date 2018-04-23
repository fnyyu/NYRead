package com.ny.program.nyread;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ny.program.nyread.main.AllFragment;
import com.ny.program.nyread.main.MineFragment;
import com.ny.program.nyread.main.ReadFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Fragment> fragments;
    private static final String TAG_READ = "read";
    private static final String TAG_ALL = "all";
    private static final String TAG_MINE = "mine";
    private static final String[] TAGS = {TAG_READ, TAG_ALL, TAG_MINE};
    private int prePos;
    private String PRE = "PRE_POS";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (prePos != 0){
                        switchFragment(0);
                    }
                    return true;
                case R.id.navigation_dashboard:
                    if (prePos != 1){
                        switchFragment(1);
                    }
                    return true;
                case R.id.navigation_notifications:
                    if (prePos != 2){
                        switchFragment(2);
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(savedInstanceState == null){//默认为0
            prePos = 0;
            fragments = new ArrayList<>();
            buildFragmentList();
        }else{
            //内存被回收了,fragments的list也被回收了,重新add进去
            prePos = savedInstanceState.getInt(PRE);
            fragments = new ArrayList<>();
            ReadFragment readFragment = (ReadFragment) getSupportFragmentManager().findFragmentByTag(TAGS[0]);
            AllFragment allFragment = (AllFragment) getSupportFragmentManager().findFragmentByTag(TAGS[1]);
            MineFragment mineFragment = (MineFragment) getSupportFragmentManager().findFragmentByTag(TAGS[2]);
            fragments.add(readFragment);
            fragments.add(allFragment);
            fragments.add(mineFragment);
        }
        //设置默认
        setDefaultFragment(prePos);
    }

    //设置默认
    private void setDefaultFragment(int pos){
        Fragment now = fragments.get(pos);
        if(!now.isAdded()){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fl_content, fragments.get(prePos), TAGS[pos])
                    .commit();
        }else{
            getSupportFragmentManager()
                    .beginTransaction()
                    .show(now)
                    .commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存上一个位置
        outState.putInt(PRE, prePos);
    }

    private void buildFragmentList() {
        ReadFragment readFragment = new ReadFragment();
        AllFragment allFragment = new AllFragment();
        MineFragment mineFragment = new MineFragment();
        fragments.add(readFragment);
        fragments.add(allFragment);
        fragments.add(mineFragment);
    }
    private void switchFragment(int pos) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        Fragment from = fragments.get(prePos);
        Fragment to = fragments.get(pos);
        if(!to.isAdded()){
            transaction.hide(from)
                    .add(R.id.fl_content, fragments.get(pos), TAGS[pos])
                    .commit();
        }else{
            transaction.hide(from)
                    .show(to)
                    .commit();
        }
        prePos = pos;
    }

}
