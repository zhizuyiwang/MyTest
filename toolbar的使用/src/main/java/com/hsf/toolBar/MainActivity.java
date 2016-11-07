package com.hsf.toolBar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private AuthorRecyclerAdapter myAdapter;
    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }*/
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBar();
        data.add("上海人民大会堂");
        data.add("上海人民大会堂");
        data.add("上海人民大会堂");
        data.add("上海人民大会堂");
        data.add("上海人民大会堂");
        data.add("上海人民大会堂");
        data.add("上海人民大会堂");
        data.add("上海人民大会堂");
        data.add("上海人民大会堂");
        data.add("上海人民大会堂");
        data.add("上海人民大会堂");
        data.add("上海人民大会堂");
        //第一步，先设置好recyclerView的Adapter
        myAdapter = new AuthorRecyclerAdapter();
        //第二步：设置布局管理器，控制布局效果

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);
    }

    private void initToolBar() {
        toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);//设置导航栏图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
            }
        });
        //toolbar.setLogo(R.mipmap.ic_launcher);//设置app logo
        toolbar.setTitle("首页");//设置主标题
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_f5f5f5));
        toolbar.setTitleTextAppearance(this,R.style.ActionBarTitle);
        //toolbar.setSubtitle("Subtitle");//设置子标题

        toolbar.inflateMenu(R.menu.base_toolbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuId = item.getItemId();
                switch (menuId) {
                    case R.id.action_search:
                        Toast.makeText(MainActivity.this, R.string.menu_search, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_notification:
                        Toast.makeText(MainActivity.this, R.string.menu_notifications, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_item1:
                        Toast.makeText(MainActivity.this, R.string.item_01, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_item2:
                        Toast.makeText(MainActivity.this, R.string.item_02, Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    // 第一步：继承重写 RecyclerView.Adapter 和 RecyclerView.ViewHolder
    public class AuthorRecyclerAdapter extends RecyclerView.Adapter<AuthorRecyclerAdapter.AuthorViewHolder>{



        @Override
        public AuthorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }


        @Override
        public void onBindViewHolder(AuthorViewHolder holder, int position) {

        }


        @Override
        public int getItemCount() {
            return 0;
        }

        class AuthorViewHolder extends RecyclerView.ViewHolder{

            public AuthorViewHolder(View itemView) {
                super(itemView);
            }
        }

    }

}
