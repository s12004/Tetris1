package com.s12004.std.itcollege.ac.jp.tetris;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import static com.s12004.std.itcollege.ac.jp.tetris.R.id.down;


public class MainActivity extends Activity implements View.OnClickListener {

    FieldView mFieldView;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        Button left = (Button) findViewById(R.id.left);
        Button right = (Button) findViewById(R.id.right);
        Button down = (Button) findViewById(R.id.down);
        Button rotation = (Button) findViewById(R.id.rotation);

        left.setOnClickListener(this);
        down.setOnClickListener(this);
        right.setOnClickListener(this);
        rotation.setOnClickListener(this);
    }

    private void setFieldView() {
        if (mFieldView == null) {
            mFieldView = new FieldView(getApplication());
            ((LinearLayout)findViewById(R.id.game)).addView(mFieldView);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left: // 左に移動
                mFieldView.move(1);
                //Toast.makeText(MainActivity.this, "右をクリック", Toast.LENGTH_LONG).show();
                break;
            case R.id.right:// 右に移動
                mFieldView.move(2);
                //Toast.makeText(MainActivity.this, "右をクリック", Toast.LENGTH_LONG).show();
                break;
            case down:// 下に移動
                mFieldView.move(3);
                //Toast.makeText(MainActivity.this, "右をクリック", Toast.LENGTH_LONG).show();
                break;
            case R.id.rotation:// 回転
                mFieldView.move(4);
                //Toast.makeText(MainActivity.this, "回転をクリック", Toast.LENGTH_LONG).show();
                break;
        }
}

    @Override
    protected void onResume() {
        super.onResume();
        setFieldView();
        mFieldView.initGame();
        mFieldView.startAnime();
        Looper.myQueue().addIdleHandler(new Idler());
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFieldView.stopAnime();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFieldView.stopAnime();
    }

    class Idler implements MessageQueue.IdleHandler {
            public Idler() {
                super();
            }

            public final boolean queueIdle() {

                return false;
            }
        }
    }