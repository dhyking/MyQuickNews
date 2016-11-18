package com.example.myquicknews;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.myquicknews.activity.BaseActivity;
import com.example.myquicknews.activity.ChannelActivity;
import com.example.myquicknews.adapter.LeftMenuAdapter;
import com.example.myquicknews.adapter.TabFragmentAdapter;
import com.example.myquicknews.fragment.HeadlineFragment;
import com.example.myquicknews.http.ApiService;
import com.example.myquicknews.http.BaseSubscriber;
import com.example.myquicknews.http.HttpManager;
import com.example.myquicknews.model.ChannelItem;
import com.example.myquicknews.model.ChannelManage;
import com.example.myquicknews.util.MyToast;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.press_channel)
    ImageView mPressChannel;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.recycle_left)
    RecyclerView mRecycleLeft;
    private List<ChannelItem> showList;     //导航栏数据列表
    private final static String PIC_KEY = "pic_key";    //图片的键值
    private final static String STR_KEY = "str_key";    //数据的键值
    private final int[] PIC_INT_ARR = new int[]{R.drawable.biz_navigation_tab_pics,
    R.drawable.biz_navigation_tab_video,R.drawable.biz_navigation_tab_read,R.drawable.biz_navigation_tab_ties};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        setDataView();
//        requestNews();

    }
    private void requestNews() {
        HttpManager mHttpManager = HttpManager.getInstance();
        ApiService mApiService = mHttpManager.getRetrofit(ApiService.CommonUrl).create(ApiService.class);
        Observable<JsonObject> mObservable = mApiService.requestNewsData(Constant.NBAId,0);
        mObservable.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<JsonObject>(this,false) {
                    @Override
                    public void onError(Throwable e) {
                        Log.d("MainActivity", e.toString());
                    }

                    @Override
                    public void onNext(JsonObject mJsonObject) {
                        Log.d("MainActivity", "news:"+mJsonObject.toString());
                    }
                });
    }



    /**
     * 设置导航及viewpager
     */
    private void setDataView() {
        initData();
        initViewpager();
        mTabLayout.setupWithViewPager(mViewpager);
        mTabLayout.setTabTextColors(getResources().getColor(R.color.default_text), Color.WHITE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initLeft();
    }

    /**
     * 侧滑菜单初始化
     */
    private void initLeft() {
        String[] arrStr = getResources().getStringArray(R.array.left_string);
        List<HashMap> list = new ArrayList<>();
        for (int i = 0; i < arrStr.length; i++) {
            HashMap map = new HashMap();
            map.put(PIC_KEY,PIC_INT_ARR[i]);
            map.put(STR_KEY,arrStr[i]);
            list.add(map);
        }
        LeftMenuAdapter mLeftMenuAdapter = new LeftMenuAdapter(this,list);
        mRecycleLeft.setLayoutManager(new LinearLayoutManager(this));
        mRecycleLeft.setAdapter(mLeftMenuAdapter);
        mLeftMenuAdapter.setOnItemOnClikListener(new LeftMenuAdapter.OnItemOnClickListener() {
            @Override
            public void onItemClick(View view, HashMap map) {
                String checkName = (String) map.get(STR_KEY);
                MyToast.getInstance().showTosat(MainActivity.this,checkName,2000);
            }
        });

    }

    /**
     * 获取导航数据
     */
    private void initData() {
        showList = ChannelManage.getManage(AppApplication.getApp().getSQLHelper()).getUserChannel();
        Log.d("MainActivity", "showList.size():" + showList.size());
    }

    /**
     * 初始化适配
     */
    private void initViewpager() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < showList.size(); i++) {
            Fragment fragment = new HeadlineFragment();
            Bundle bundle = new Bundle();
            bundle.putString("text", showList.get(i).getName());
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        TabFragmentAdapter mTabFragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), this, fragments, showList);
        mViewpager.setAdapter(mTabFragmentAdapter);
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
        getMenuInflater().inflate(R.menu.main, menu);
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

    @OnClick(R.id.press_channel)
    public void onClick() {
        Log.d("MainActivity", "------------------点击");
        Intent mIntent = new Intent(MainActivity.this, ChannelActivity.class);
        startActivityForResult(mIntent, 0);
        overridePendingTransition(R.anim.anim_slide_up,R.anim.anim_slide_down);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            Log.d("MainActivity", "------------------刷新");
            setDataView();
        }
    }
}
