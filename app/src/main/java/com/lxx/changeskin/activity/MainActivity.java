package com.lxx.changeskin.activity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lxx.changeskin.R;

import java.io.File;

import cn.feng.skin.manager.base.BaseActivity;
import cn.feng.skin.manager.listener.ILoaderListener;
import cn.feng.skin.manager.loader.SkinManager;

public class MainActivity extends BaseActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }


    private Button btn;
    private Button btn1;
    String SKIN_NAME = "lee.skin";
    String SKIN_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + SKIN_NAME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());*/

        btn = findViewById(R.id.btn);
        btn1 = findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SkinManager.getInstance().restoreDefaultTheme();
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("lee", SKIN_DIR);

                File skin = new File(SKIN_DIR);

                if (!skin.exists()) {
                    Toast.makeText(MainActivity.this, "请检查" + SKIN_DIR + "是否存在", Toast.LENGTH_SHORT).show();
                    return;
                }

                SkinManager.getInstance().load(skin.getAbsolutePath(),
                        new ILoaderListener() {
                            @Override
                            public void onStart() {
                                Toast.makeText(MainActivity.this, "onStart", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onSuccess() {
                                Toast.makeText(MainActivity.this, "onSuccess", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailed() {
                                Toast.makeText(MainActivity.this, "onFailed", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
