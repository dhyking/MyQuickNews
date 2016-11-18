package com.example.myquicknews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myquicknews.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/16.
 */

public class LeftMenuAdapter extends RecyclerView.Adapter<LeftMenuAdapter.MenuHolder> implements View.OnClickListener{
    private final static String PIC_KEY = "pic_key";    //图片的键值
    private final static String STR_KEY = "str_key";    //数据的键值
    private Context mContext;
    private List<HashMap> menuList;
    private final LayoutInflater mInflater;

    public LeftMenuAdapter(Context mContext, List<HashMap> mMenuList) {
        this.mContext = mContext;
        this.menuList = mMenuList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_menu_view, parent, false);
        MenuHolder mMenuHolder = new MenuHolder(view);
        view.setOnClickListener(this);

        return mMenuHolder;
    }

    @Override
    public void onBindViewHolder(MenuHolder holder, int position) {
        HashMap map = menuList.get(position);
        holder.itemView.setTag(map);    //设置标记
        int picId = (int) map.get(PIC_KEY);
        String textName = (String) map.get(STR_KEY);
        holder.mIvMenu.setImageResource(picId);
        holder.mTvMenu.setText(textName);

    }

    @Override
    public int getItemCount() {
        return menuList.isEmpty() ? 0 : menuList.size();
    }

    private OnItemOnClickListener mOnItemOnClickListener;

    @Override
    public void onClick(View v) {
        if (mOnItemOnClickListener != null) {
            mOnItemOnClickListener.onItemClick(v, (HashMap) v.getTag());
        }
    }

    public interface OnItemOnClickListener{
        void onItemClick(View view,HashMap map);
    }

    public void setOnItemOnClikListener(OnItemOnClickListener onItemClickListener){
        this.mOnItemOnClickListener  = onItemClickListener;
    }

    protected class MenuHolder extends RecyclerView.ViewHolder {
        public ImageView mIvMenu;
        public TextView mTvMenu;
        public MenuHolder(View itemView) {
            super(itemView);
            mIvMenu = (ImageView) itemView.findViewById(R.id.iv_menu);
            mTvMenu = (TextView) itemView.findViewById(R.id.tv_menu);
        }
    }
}

