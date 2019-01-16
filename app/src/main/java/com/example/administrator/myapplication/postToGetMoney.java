package com.example.administrator.myapplication;

import java.io.IOException;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class postToGetMoney {
    public void get(String money) {
        OkHttpClient client = new OkHttpClient();
        //构造Request对象
        //采用建造者模式，链式调用指明进行Get请求,传入Get的请求地址
        long date_l = new Date().getTime();
        String date =Long.toString(date_l);
        String url="http://103.55.25.13/wxpay/getMoney.php?mid=alipay"+date+money+"&money="+money;
        System.out.println(url);
        Request request = new Request.Builder().get().url(url).build();
        Call call = client.newCall(request);
        //异步调用并设置回调函数
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //ToastUtil.showToast(GetActivity.this, "Get 失败");
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseStr = response.body().string();
                System.out.println(responseStr);
                if(responseStr.equals("ok")){
                    System.out.println("添加数据成功");
                }else {
                    System.out.println("添加数据不成功");
                }
            }
        });
    }

}
