package com.example.lje.my2thapplication;


import android.os.Bundle;

        import android.support.v7.app.AppCompatActivity;

        import android.view.View;

        import android.webkit.WebView;

        import android.webkit.WebViewClient;

        import android.widget.Button;

        import android.widget.EditText;


public class Layout03 extends AppCompatActivity {


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_fragment3);


        final EditText address = (EditText) findViewById(R.id.address);

        final WebView webView = (WebView) findViewById(R.id.web);


        Button get = (Button) findViewById(R.id.go);

        get.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                webView.setWebViewClient(new WebViewClient());

                // 위 코드 한줄을 입력하지 않으면 새 창에서 웹페이지가 뜹니다.

                webView.loadUrl("http://" + address.getText().toString());

            }

        });

    }

}



