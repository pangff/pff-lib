package com.pffair.android_libs.rich_text;

import com.BaseApplication;
import com.pffair.android_libs.R;
import com.pffair.android_libs.rich_text.util.AsyncRichTextParser;
import com.pffair.android_libs.rich_text.util.LinkTouchMovementMethod;
import com.pffair.android_libs.rich_text.util.SpanClickListener;
import com.pffair.android_libs.rich_text.util.TextParseUtil;

import android.app.Activity;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pangff on 16/3/28.
 * Description RichAdapter
 */
public class RichAdapter extends BaseAdapter {

    List<String> strList = new ArrayList<>();

    @Override
    public int getCount() {
        return strList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void refresh(List<String> list) {
        strList.clear();
        strList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.page_rich_text_view_item,
                            parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.bind(strList.get(position));
        return convertView;
    }

    public static class ViewHolder {

        View rootView;

        @Bind(R.id.tv_rich)
        TextView tvRich;

        public ViewHolder(View root) {
            this.rootView = root;
            ButterKnife.bind(this, rootView);
        }

        public void bind(final String str) {
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DetailActivity.start((Activity) v.getContext(),str);
                }
            });
            final int height = (int) (tvRich.getPaint().descent() - tvRich.getPaint().ascent());
            AsyncRichTextParser.parseText(str, height, new SpanClickListener() {
                @Override
                public void onClicked(final View view, final String tag, final Map data) {
                    BaseApplication.mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (tag.equals(TextParseUtil.TAG_STOCK)) {
                                Toast.makeText(view.getContext(), "点击" + data.get("name"),
                                        Toast.LENGTH_SHORT).show();
                            }
                            if (tag.equals(TextParseUtil.TAG_A)) {
                                Toast.makeText(view.getContext(), "点击链接", Toast.LENGTH_SHORT)
                                        .show();
                            }
                            if (tag.equals(TextParseUtil.TAG_TOPIC)) {
                                Toast.makeText(view.getContext(), "点击" + data.get("text"),
                                        Toast.LENGTH_SHORT).show();
                            }
                            if (tag.equals(TextParseUtil.TAG_AT_USER)) {
                                Toast.makeText(view.getContext(), "点击" + data.get("nick"),
                                        Toast.LENGTH_SHORT).show();
                            }
                            if (tag.equals(TextParseUtil.TAG_USER)) {
                                Toast.makeText(view.getContext(), "点击" + data.get("nick"),
                                        Toast.LENGTH_SHORT).show();
                            }
                            if (tag.equals(TextParseUtil.TAG_MATCH)) {
                                Toast.makeText(view.getContext(), "点击" + data.get("text"),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }, new AsyncRichTextParser.ParseListener() {
                @Override
                public void onParseFinished(final SpannableStringBuilder stringBuilder) {
                    Log.e("pangff","stringBuilder.length():"+stringBuilder.length());
                    tvRich.setText(stringBuilder);
                    tvRich.setMovementMethod(new LinkTouchMovementMethod());
                }
            });
        }
    }
}
