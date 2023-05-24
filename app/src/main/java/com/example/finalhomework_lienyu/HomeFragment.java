package com.example.finalhomework_lienyu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.finalhomework_lienyu.modified_classes.MyPagerAdapter;
import com.example.finalhomework_lienyu.modified_classes.ShufflingPicsFragment;
import com.example.finalhomework_lienyu.modified_classes.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeFragment extends Fragment {
    private ListView broadcastListView;
    private View view;
    //从strings.xml中获取数据
    private String[] broadcasts;
    private String[] bcDateList;
    private ViewPager viewPager_shuffling;
    private MyPagerAdapter shufflingAdapter;

    //声明图片资源
    int[] shufflingImgIds = new int[]{R.drawable.pic1, R.drawable.pic2, R.drawable.pic3};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = initViews(inflater,container,savedInstanceState);
        return view;
    }

    private View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);
        //初始化
        broadcastListView = view.findViewById(R.id.listView_broadcast);//获取ListView
        broadcasts = getResources().getStringArray(R.array.broadcast_list);
        bcDateList = getResources().getStringArray(R.array.broadcastDate_list);
        viewPager_shuffling = view.findViewById(R.id.viewPager_shuffling);
        shufflingAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());

        //设置轮播图viewPager_shuffling
        for(int i = 0; i < shufflingImgIds.length; i++){
            shufflingAdapter.addFragment(new ShufflingPicsFragment(shufflingImgIds[i]));
        }
        viewPager_shuffling.setPageTransformer(true,new ZoomOutPageTransformer());
        viewPager_shuffling.setAdapter(shufflingAdapter);



        //设置公告ListView
        //创建键值对列表
        List<Map<String,Object>> bcListItems = new ArrayList<Map<String,Object>>();
        //添加数据到列表
        for (int i = 0; i < broadcasts.length; i++){
            //建立单个键值对变量
            Map<String,Object> listItem = new HashMap<String,Object>();
            listItem.put("bcContent",broadcasts[i]);
            listItem.put("bcDate",bcDateList[i]);

            bcListItems.add(listItem);//添加至列表
        }
        //创建SimpleAdapter 并把数据和View绑定.
        SimpleAdapter bcAdapter = new SimpleAdapter(getContext(),bcListItems,R.layout.broadcast_item,new String[]{"bcContent","bcDate"},new int[]{R.id.textView_bc_content,R.id.textView_bc_date});
        broadcastListView.setAdapter(bcAdapter);//设置Adapter



        return view;
    }


}