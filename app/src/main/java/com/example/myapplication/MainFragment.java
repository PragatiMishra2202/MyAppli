package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainFragment extends Fragment {

   public MainFragment(){

   }

   private RecyclerView categoryRecyclerView;
   private category_adapter categoryAdapter;

   private ViewPager bannerSliderViewPager;
   private List<SliderModel> sliderModelList;
   private int currentPage= 2;
   private Timer timer;
   final private long DELAY_TIME = 3000;
   final private long PERIOD_TIME = 3000;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        categoryRecyclerView = view.findViewById(R.id.category_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);
        List<category_model> category_modelList = new ArrayList<category_model>();
        category_modelList.add(new category_model("link","Home"));
        category_modelList.add(new category_model("link","Electronics"));
        category_modelList.add(new category_model("link","Appliances"));
        category_modelList.add(new category_model("link","Furniture"));
        category_modelList.add(new category_model("link","Fashion"));
        category_modelList.add(new category_model("link","Home"));
        category_modelList.add(new category_model("link","Home"));
        category_modelList.add(new category_model("link","Home"));
        category_modelList.add(new category_model("link","Home"));
        category_modelList.add(new category_model("link","Home"));

        categoryAdapter = new category_adapter(category_modelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        bannerSliderViewPager = view.findViewById(R.id.banner_slider_view_pager);

        sliderModelList = new ArrayList<SliderModel>();
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher_round,"#000000"));
        sliderModelList.add(new SliderModel(R.mipmap.profile_placeholder_,"#000000"));
        sliderModelList.add(new SliderModel(R.mipmap.logomilaanc,"#000000"));

        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher,"#000000"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher_round,"#000000"));
        sliderModelList.add(new SliderModel(R.mipmap.profile_placeholder_,"#000000"));
        sliderModelList.add(new SliderModel(R.mipmap.logomilaanc,"#000000"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher,"#000000"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher_round,"#000000"));

        sliderModelList.add(new SliderModel(R.mipmap.profile_placeholder_,"#000000"));
        sliderModelList.add(new SliderModel(R.mipmap.logomilaanc,"#000000"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher,"#000000"));

        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);

        bannerSliderViewPager.setCurrentItem(currentPage);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int position) {
                if (position == ViewPager.SCROLL_STATE_IDLE){
                    pageLooper();
                }
            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        startBannerSlideShow();
        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                pageLooper();
                stopBannerSlideShow();
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    startBannerSlideShow();
                }
                return false;
            }
        });

        return view;
    }

    private void pageLooper(){
        if (currentPage == sliderModelList.size()-2){
            currentPage = 2;
            bannerSliderViewPager.setCurrentItem(currentPage, false);
        }
        if (currentPage == 1) {
            currentPage = sliderModelList.size() - 3;
            bannerSliderViewPager.setCurrentItem(currentPage, false);
        }
    }

    private void startBannerSlideShow(){
        Handler handler = new Handler();
        Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage >= sliderModelList.size()){
                    currentPage = 1;
                }
                bannerSliderViewPager.setCurrentItem(currentPage++, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_TIME,PERIOD_TIME);
    }

    private void stopBannerSlideShow(){
        timer.cancel();
    }
}
