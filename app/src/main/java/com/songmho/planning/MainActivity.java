package com.songmho.planning;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    Button todo;
    Button doing;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffFF7300));
        getSupportActionBar().setTitle("Planning");

        ViewPager main_viewPager=(ViewPager)findViewById(R.id.viewpager);
        int MAX_PAGE=3;             //viewpager 총 페이지 갯수

        todo=(Button)findViewById(R.id.todo);
        doing=(Button)findViewById(R.id.doing);
        done=(Button)findViewById(R.id.done);

        main_viewPager.setAdapter(new Viewpager_Adapter(getSupportFragmentManager(),MAX_PAGE));     //Adapter연결
        main_viewPager.setOnPageChangeListener(new Viewpager_change());
    }


    private void openAdd() {
        Toast.makeText(getApplicationContext(),"Toast",Toast.LENGTH_SHORT).show();
    }

    private class Viewpager_change implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            switch (position){
                case 0:                         //To do Fragment
                    todo.setSelected(true);
                    doing.setSelected(false);
                    done.setSelected(false);
                    break;
                case 1:                         //Doing Fragment
                    todo.setSelected(false);
                    doing.setSelected(true);
                    done.setSelected(false);
                    break;
                case 2:                        //Done Fragment
                    todo.setSelected(false);
                    doing.setSelected(false);
                    done.setSelected(true);
                    break;
            }
        }

        @Override
        public void onPageSelected(int position) {}

        @Override
        public void onPageScrollStateChanged(int state) {}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_add:
                openAdd();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
