package com.hsf.tts;

import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private EditText et;
    private TextToSpeech mSpeech;
    private String[] langs;
    private String curLang;
    private List<String> langList = new ArrayList<String>();
    private ArrayAdapter<String> langAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner);

        et = (EditText) findViewById(R.id.et);
        //通过资源文件获取数组，获取的是语言数组
        langs = getResources().getStringArray(R.array.languages);

        Log.e("TAG",""+langs.length);
        //把数组变为list的方法
        //Collections.addAll(langList,langs);

        for(String lang : langs){
            langList.add(lang);
        }

        //设置下拉框的适配器和样式
        langAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,langList);
        langAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(langAdapter);

        //Spinner下拉框的选择监听器
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("TAG",""+i);
                curLang = (String) spinner.getAdapter().getItem(i);
                if(mSpeech!=null){
                    mSpeech.stop();
                    mSpeech.shutdown();
                    mSpeech = null;
                }
                mSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            int flag = setLanguge(curLang);

                            if (flag == TextToSpeech.LANG_MISSING_DATA
                                    || flag == TextToSpeech.LANG_NOT_SUPPORTED) {
                                Toast.makeText(
                                        MainActivity.this,
                                        "语音库缺失",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                },"com.iflytek.tts");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });


        //默认初始化Speech
        //initSpeech();

    }

    /**
     * 设置语言类型
     * @param lang
     * @return
     */
    private int setLanguge(String lang) {
        int result = 0;
        if (lang.equals("CANADA")) {
            result = mSpeech.setLanguage(Locale.CANADA);
        } else if (lang.equals("CANADA_FRENCH")) {
            result = mSpeech.setLanguage(Locale.CANADA_FRENCH);
        } else if (lang.equals("CHINA")) {
            result = mSpeech.setLanguage(Locale.CHINA);
        } else if (lang.equals("CHINESE")) {
            result = mSpeech.setLanguage(Locale.CHINESE);
        } else if (lang.equals("ENGLISH")) {
            result = mSpeech.setLanguage(Locale.ENGLISH);
        } else if (lang.equals("FRANCE")) {
            result = mSpeech.setLanguage(Locale.FRANCE);
        } else if (lang.equals("FRENCH")) {
            result = mSpeech.setLanguage(Locale.FRENCH);
        } else if (lang.equals("GERMAN")) {
            result = mSpeech.setLanguage(Locale.GERMAN);
        } else if (lang.equals("GERMANY")) {
            result = mSpeech.setLanguage(Locale.GERMANY);
        } else if (lang.equals("ITALIAN")) {
            result = mSpeech.setLanguage(Locale.ITALIAN);
        } else if (lang.equals("ITALY")) {
            result = mSpeech.setLanguage(Locale.ITALY);
        } else if (lang.equals("JAPAN")) {
            result = mSpeech.setLanguage(Locale.JAPAN);
        } else if (lang.equals("JAPANESE")) {
            result = mSpeech.setLanguage(Locale.JAPANESE);
        } else if (lang.equals("KOREA")) {
            result = mSpeech.setLanguage(Locale.KOREA);
        } else if (lang.equals("KOREAN")) {
            result = mSpeech.setLanguage(Locale.KOREAN);
        } else if (lang.equals("PRC")) {
            result = mSpeech.setLanguage(Locale.PRC);
        } else if (lang.equals("ROOT")) {
            result = mSpeech.setLanguage(Locale.ROOT);
        } else if (lang.equals("SIMPLIFIED_CHINESE")) {
            result = mSpeech.setLanguage(Locale.SIMPLIFIED_CHINESE);
        } else if (lang.equals("TAIWAN")) {
            result = mSpeech.setLanguage(Locale.TAIWAN);
        } else if (lang.equals("TRADITIONAL_CHINESE")) {
            result = mSpeech.setLanguage(Locale.TRADITIONAL_CHINESE);
        } else if (lang.equals("UK")) {
            result = mSpeech.setLanguage(Locale.UK);
        } else if (lang.equals("US")) {
            result = mSpeech.setLanguage(Locale.US);
        }

        return result;
    }

    private void initSpeech() {

        mSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int flag = mSpeech.setLanguage(Locale.CHINESE);

                    if (flag == TextToSpeech.LANG_MISSING_DATA
                            || flag == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(
                                MainActivity.this,
                                "语音库缺失",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }

        },"com.iflytek.tts");
    }

    public void speak(String msg){

        mSpeech.speak(msg, TextToSpeech.QUEUE_FLUSH,
                null);

    }


    public void play(View view){
        String speak = et.getEditableText().toString().trim();
        if(!TextUtils.isEmpty(speak)){
            speak(speak);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mSpeech!=null){
            mSpeech.stop();
            mSpeech.shutdown();
            mSpeech = null;
        }
    }
}
