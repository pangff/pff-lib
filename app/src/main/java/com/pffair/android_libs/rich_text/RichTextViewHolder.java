package com.pffair.android_libs.rich_text;

import com.pffair.android_libs.R;
import com.pffair.android_libs.base.PageViewHolder;
import com.pffair.android_libs.rich_text.util.DataUtil;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pangff on 16/3/25.
 * Description RichTextViewHolder
 */
public class RichTextViewHolder extends PageViewHolder {

    @Bind(R.id.lv_rich)
    ListView lvRich;

    public RichTextViewHolder(Context context){
        super(context);
    }


    @Override
    public void onBindView(ViewGroup view) {
        ButterKnife.bind(this,view);
    }

    @Override
    public void onAttach(ViewGroup viewGroup) {
        RichAdapter richAdapter = new RichAdapter();
        lvRich.setAdapter(richAdapter);
        final List<String> dataList = new ArrayList<>();
        for(int i=0;i<200;i++){
            dataList.add(DataUtil.getData2(i));
        }
        richAdapter.refresh(dataList);

    }


    @Override
    public int getLayout() {
        return R.layout.page_rich_text_view;
    }
}

