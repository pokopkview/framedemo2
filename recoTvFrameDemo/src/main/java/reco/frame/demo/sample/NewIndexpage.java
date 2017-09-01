package reco.frame.demo.sample;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import reco.frame.demo.R;
import reco.frame.demo.compoment.testViewpager;
import reco.frame.demo.entity.IndexInfoModel;
import reco.frame.demo.entity.MainPageModel;

public class NewIndexpage extends AppCompatActivity {

    @Bind(R.id.tv_top_date)
    TextView tvTopDate;
    @Bind(R.id.iv_top_clock)
    ImageView ivTopClock;
    @Bind(R.id.tv_top_time)
    TextView tvTopTime;
    @Bind(R.id.tlMain)
    TabLayout tlMain;
    @Bind(R.id.vp_main_shows)
    testViewpager vpMainShows;

    public static int moveRight = 0;
    public static boolean moveLeft = true;
    public static boolean isScrolling = false;
    public static boolean isPagerChange = false;
    private int lastValue = 0;
    public static boolean isTabView = false;

    private View tempView;
    private boolean isoutFrag = false;
    private List<View> vpChile = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private List<testfragment2> fragmentList = new ArrayList<>();
    private List<LinearLayout> selectitem = new ArrayList<>();
    private List<TabLayout.Tab> tabList = new ArrayList<>();
    private FragmentManager fragmentManager;
    private View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                vpMainShows.setCurrentItem(selectitem.indexOf(v));
                v.setBackgroundResource(R.drawable.cursor_shine);
            } else {
                v.setBackgroundResource(0);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_page);
        ButterKnife.bind(this);
        setDate();
        fragmentManager = getSupportFragmentManager();
        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }
        };
        for (String title : titleList) {
            tlMain.addTab(tlMain.newTab().setText(title));
        }
        vpMainShows.setAdapter(pagerAdapter);
        vpMainShows.setOffscreenPageLimit(10);
        tlMain.setupWithViewPager(vpMainShows);
        for (int i = 0; i < tlMain.getTabCount(); i++) {
            try {
                /**
                 * 通过反射来获取Tab内的View并设置监听
                 */
                Field field = tlMain.getTabAt(i).getClass().getDeclaredField("mView");
                field.setAccessible(true);
                Object obj = field.get(tlMain.getTabAt(i));
                selectitem.add((LinearLayout) obj);
                tabList.add(tlMain.getTabAt(i));
                ((LinearLayout) obj).setOnFocusChangeListener(focusChangeListener);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        vpMainShows.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(!isTabView) {
                    if (lastValue > positionOffsetPixels && positionOffsetPixels != 0) {
                        NewIndexpage.moveRight = 2;
                    } else if (lastValue < positionOffsetPixels && positionOffsetPixels != 0) {
                        NewIndexpage.moveRight = 1;
                    }
                    lastValue = positionOffsetPixels;
                    if (positionOffsetPixels == 0) {
                        fragmentList.get(position).setFocus();
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                Log.i("onPageSelected",isPagerChange+"");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i("onPageScrollStateChanged",state+"");
            }
        });
    }

    private LinearLayout getFocusItem() {
        for (LinearLayout layout : selectitem) {
            if (layout.isFocused()) {
                return layout;
            }
        }
        return null;
    }

    private TabLayout.Tab getFocusTab() {
        for (TabLayout.Tab tab : tabList) {
            if (tab.isSelected()) {
                return tab;
            }
        }
        return null;
    }

    private void setDate() {
        /**
         * fragment  需要设计集中要是的布局对应到每个fragment上面，后台就可以完全控制布局的显示和编辑
         * 在数据初始化的时候，解析网络获取的首页的json格式来对首页进行布局
         */
        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = getAssets().open("index_pages.json");
            InputStreamReader sr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(sr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        MainPageModel model = gson.fromJson(sb.toString(), MainPageModel.class);
        for (IndexInfoModel entity : model.getMain_title()) {
            titleList.add(entity.getTitle());
            Bundle bundle = new Bundle();
            bundle.putParcelable("info", entity.getPageinfo());
            testfragment2 test = new testfragment2();
            test.setArguments(bundle);
            fragmentList.add(test);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        System.out.println(keyCode+"___"+KeyEvent.KEYCODE_DPAD_RIGHT);
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            if (fragmentList.get(vpMainShows.getCurrentItem()).viewList.size()>0) {
                selectitem.get(tabList.indexOf(getFocusTab())).requestFocus();//如果是第一层view则直接将焦点推上TabLayout
                fragmentList.get(vpMainShows.getCurrentItem()).viewList.clear();
                return true;
            }
        }
//        else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
//            if (vpMainShows.getCurrentItem() == 0) {
//                if (fragmentList.get(0).getLefttemp() != null) {
//                    return true;
//                }
//            }
//        } else if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
//
//        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
//        if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {
//            if (vpMainShows.getCurrentItem() == fragmentList.size()-1) {
//                if (fragmentList.get(fragmentList.size()-1).getRighttemp() != null) {
//                    return true;
//                }
//            }
//            if(fragmentList.get(vpMainShows.getCurrentItem()).getRighttemp()!=null){
//
//            }
//        }
        return super.dispatchKeyEvent(event);
    }
}
