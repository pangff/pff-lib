package com.pffair.android_libs.rich_text;

import com.pffair.android_libs.BaseActivity;
import com.pffair.android_libs.R;
import com.pffair.android_libs.rich_text.util.AsyncRichTextParser;
import com.pffair.android_libs.rich_text.util.ClipboardUtil;
import com.pffair.android_libs.rich_text.util.SpanClickListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Map;

import butterknife.Bind;

/**
 * Created by pangff on 16/3/29.
 * Description DetailActivity
 */
public class DetailActivity extends BaseActivity {

    @Bind(R.id.tv_rich)
    TextView tvRich;

    @Bind(R.id.bt_copy)
    Button btCopy;


    String content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rich_text_detail_activity);
        content = getIntent().getStringExtra("str");
        final int height = (int) (tvRich.getPaint().descent() - tvRich.getPaint().ascent());
        AsyncRichTextParser.parseText(content, height, new SpanClickListener() {
            @Override
            public void onClicked(View view, String tag, Map data) {

            }
        }, new AsyncRichTextParser.ParseListener() {
            @Override
            public void onParseFinished(SpannableStringBuilder stringBuilder) {
                tvRich.setText(stringBuilder);
            }
        });
        btCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("pangff",":"+tvRich.getText());
                ClipboardUtil.setCopyText(DetailActivity.this,content);
            }
        });

    }

    public static void start(Activity activity,String str){
        Intent intent = new Intent();
        intent.putExtra("str",str);
        intent.setClass(activity,DetailActivity.class);
        activity.startActivity(intent);
    }
}
