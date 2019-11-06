package com.mrhi.ex81jsontest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= findViewById(R.id.tv);
    }

    public void clickBtn(View view) {

        //json파일 읽어와서 분석하기

        //assets폴더의 파일을 가져오기 위해
        //창고관리자(AssetManager) 얻어오기
        AssetManager assetManager= getAssets();

        //assets/test.json 파일 읽기위한 InputStream
        try {
            InputStream is= assetManager.open("jsons/test.json");
            InputStreamReader isr= new InputStreamReader(is);
            BufferedReader reader= new BufferedReader(isr);

            StringBuffer buffer= new StringBuffer();
            String line= reader.readLine();
            while(line!=null){
                buffer.append(line+"\n");
                line= reader.readLine();
            }

            String jsonData= buffer.toString();

            //읽어온 json문자열 확인
            //tv.setText(jsonData);

            //json 분석
            //json객체 생성
//            JSONObject jsonObject= new JSONObject(jsonData);
//            String name= jsonObject.getString("name");
//            String mag= jsonObject.getString("msg");
//
//            tv.setText("이름: "+ name+"\n"+ "메세지: "+ mag);

            //json데이터가 []로 시작하는 배열일때..
            JSONArray jsonArray= new JSONArray(jsonData);

            String s="";
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jo= jsonArray.getJSONObject(i);

                String name= jo.getString("name");
                String msg= jo.getString("msg");
                JSONObject flag= jo.getJSONObject("flag");
                int aa= flag.getInt("aa");
                int bb= flag.getInt("bb");

                s += name+" : " + msg + "==>" + aa+"," +bb +"\n";
            }

            tv.setText(s);



        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
