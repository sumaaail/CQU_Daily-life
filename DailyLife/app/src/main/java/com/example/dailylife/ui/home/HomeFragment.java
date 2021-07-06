package com.example.dailylife.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dailylife.MainActivity;
import com.example.dailylife.R;
import com.google.gson.Gson;
import com.qweather.sdk.bean.base.Lang;
import com.qweather.sdk.bean.base.Unit;
import com.qweather.sdk.bean.weather.WeatherNowBean;
import com.qweather.sdk.view.HeConfig;
import com.qweather.sdk.view.QWeather;
import  com.example.dailylife.MainActivity;
import static android.content.ContentValues.TAG;


public class HomeFragment extends Fragment {
    public static final  String TAG="MainActivity";
    private HomeViewModel homeViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        TextView t = root.findViewById(R.id.t);
        TextView weatherm = root.findViewById(R.id.weatherm);
        ImageView weather = root.findViewById(R.id.weather);

        QWeather.getWeatherNow(getActivity(), "CN101040100", Lang.EN, Unit.METRIC, new QWeather.OnResultWeatherNowListener() {
            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "Weather Now onError: ", e);
                Toast.makeText(getActivity(), " "+e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(WeatherNowBean weatherBean) {
                Log.i(TAG, " Weather Now onSuccess: " + new Gson().toJson(weatherBean));
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                //先判断返回的status是否正确，当status正确时获取数据，若status不正确，可查看status对应的Code值找到原因
                //此时返回数据
                WeatherNowBean.NowBaseBean now = weatherBean.getNow();
                String temp = now.getTemp();
                String morning = now.getText();
                t.setText(temp + "℃");
                weatherm.setText(morning);
                if(morning.equals("多云")||morning.equals("少云")||morning.equals("晴间多云")){
                    weather.setImageResource(R.drawable.cloudydaytime01);
                }
                else if(morning.equals("阴")){
                    weather.setImageResource(R.drawable.yin);
                }
                else if(morning.equals("晴")){
                    weather.setImageResource(R.drawable.sunny01);
                }
                else if(morning.equals("阵雨")||morning.equals("强阵雨")||morning.equals("雷阵雨")||morning.equals("强雷阵雨")||morning.equals("极端降雨")||
                        morning.equals("小雨")||morning.equals("中雨")||morning.equals("大雨")||morning.equals("毛毛雨/细雨")||morning.equals("暴雨")||
                        morning.equals("大暴雨")||morning.equals("特大暴雨")||morning.equals("冻雨")|| morning.equals("小到中雨")||morning.equals("中到大雨")||
                        morning.equals("大到暴雨")||morning.equals("暴雨到大暴雨")||morning.equals("大暴雨到特大暴雨")||morning.equals("雨")){
                    weather.setImageResource(R.drawable.rainy01);
                }
                else if(morning.equals("薄雾")||morning.equals("雾")||morning.equals("扬沙")||morning.equals("浮尘")||morning.equals("沙尘暴")
                        ||morning.equals("强沙尘暴")||morning.equals("浓雾")||morning.equals("强浓雾")||morning.equals("大雾")||morning.equals("特强浓雾")){
                    weather.setImageResource(R.drawable.wu);
                }
                else if(morning.equals("霾")||morning.equals("中度霾")||morning.equals("重度霾")||morning.equals("严重霾")||morning.equals("未知")){
                    weather.setImageResource(R.drawable.mai);
                }
                else {
                    weather.setImageResource(R.drawable.yin);
                }
            }
        });
        ImageView image = root.findViewById(R.id.weather);
        return root;
    }
}