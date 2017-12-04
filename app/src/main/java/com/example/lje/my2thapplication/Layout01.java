package com.example.lje.my2thapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class Layout01 extends AppCompatActivity {
    //이미지 스레드 구현을 위한 변수 선언
    ImageSwitcher imageSwitcher;
    //이미지배열
    int[] imageArray ={R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d};

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    ImageThread imageThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fragment1);

        imageSwitcher=(ImageSwitcher)findViewById(R.id.imageSwitcher1);

        //이미지 바꾸기
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView =new ImageView(getApplicationContext());
                return imageView;
            }
        });


        //안드로이드에 내장된 애니메이션 적용
        Animation in= AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out=AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);

    }


    public void onClick(View v){

        switch (v.getId()){
            case R.id.button1:
                imageThread=new ImageThread();
                imageThread.start();

                Toast.makeText(getApplicationContext(), "시작", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button2:
                if(imageThread!=null){
                    imageThread.halt();
                    imageThread=null;
                }
                Toast.makeText(getApplicationContext(), "중지", Toast.LENGTH_SHORT).show();
                break;
        }
    }




    class ImageThread extends Thread{

        boolean running =false;
        int index=0;

        @Override
        public void run() {
            running=true;

            while (running){

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageSwitcher.setImageResource(imageArray[index]);
                        imageSwitcher.invalidate();
                    }
                });

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                index++;
                if(index >= imageArray.length){
                    index=0;
                }


            }

        }

        public void halt(){
            running=false;
        }

    }




}