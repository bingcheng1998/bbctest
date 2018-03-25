package com.example.bingchenghu.listviewtest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SideActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private List<Fruit> fruitList=new ArrayList<>();

    private List<String> list = new ArrayList<String>();

    int checkedNum = 0;

    private boolean isAdd = false;
    private RelativeLayout rlAddBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        final FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);


//        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                adapter.addData();
//            }
//        });
//        final FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                adapter.addData();
//            }
//        });
        adapter.setOnItemClickListener(new FruitAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //adapter.notifyItemRemoved(position);
                adapter.moveToRoof(position);
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                checkedNum = 0;
                final FruitAdapter.ViewHolder holder = new FruitAdapter.ViewHolder(view);
                AlertDialog.Builder builder = new AlertDialog.Builder(SideActivity.this);
                final String[] items = new String[] { "删除", "置顶" };
                builder.setTitle("操作")
                        //.setNegativeButton("no", null);
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setSingleChoiceItems(items, 0,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(SideActivity.this, "选择的是：" +which + items[which], Toast.LENGTH_SHORT)
                                                .show();
                                        checkedNum = which;
                                        //dialog.dismiss()
                                    }
                                })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(checkedNum == 0){
                                    adapter.removeData(position);
                                }
                                else if(checkedNum == 1) {
                                    adapter.moveToRoof(position);
                                }

                                //adapter.notifyItemRemoved(position);
                                //Toast.makeText(adapter.this,MainActivity..get(position),Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();


            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.side, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void initFruits(){

        for (int i=0;i<1;i++){

            Fruit apple=new Fruit("Apple",R.drawable.apple_pic);

            fruitList.add(apple);

            Fruit banana=new Fruit("banana",R.drawable.banana_pic);

            fruitList.add(banana);

            Fruit orange=new Fruit("orange",R.drawable.orange_pic);

            fruitList.add(orange);

            Fruit watermelon=new Fruit("watermelon",R.drawable.watermelon_pic);

            fruitList.add(watermelon);

            Fruit pear=new Fruit("pear",R.drawable.pear_pic);

            fruitList.add(pear);

            Fruit grape=new Fruit("grape",R.drawable.grape_pic);

            fruitList.add(grape);

            Fruit pineapple=new Fruit("pineapple",R.drawable.pineapple_pic);

            fruitList.add(pineapple);

            Fruit strawberry=new Fruit("strawberry",R.drawable.strawberry_pic);

            fruitList.add(strawberry);

            Fruit cherry=new Fruit("cherry",R.drawable.cherry_pic);

            fruitList.add(cherry);

            Fruit mango=new Fruit("mango",R.drawable.mango_pic);

            fruitList.add(mango);

        }

    }
}
