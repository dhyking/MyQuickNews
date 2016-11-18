package com.example.myquicknews.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myquicknews.R;
import com.example.myquicknews.model.ChannelItem;

import java.util.List;

/**
 * Created by Administrator on 2016/11/15.
 */

public class DragAdapter extends BaseAdapter {

    private boolean isItemShow = false; //是否显示底部item
    private Context mContext;
    private int holdPosition;   //控制的position
    private boolean isChanged = false;  //能否改变
    private boolean isVisiable = true;  //能否可见
    public List<ChannelItem> channelList;   //用户选择列表
    private TextView mTvChannel;    //内容
    private int removePosition = -1;    //要删除的选项
    private boolean isListChanged = false;      //频道是否改变

    public boolean isListChanged() {
        return isListChanged;
    }

    public DragAdapter(Context mContext, List<ChannelItem> mChannelList) {
        this.mContext = mContext;
        this.channelList = mChannelList;
    }

    @Override
    public int getCount() {
        return channelList == null ? 0 : channelList.size();
    }

    @Override
    public ChannelItem getItem(int position) {
        if (channelList != null && channelList.size()>0) {
            return channelList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_grid_category,parent,false);
        mTvChannel = (TextView) view.findViewById(R.id.tv_category_name);
        ChannelItem mChannelItem = channelList.get(position);
        mTvChannel.setText(mChannelItem.getName());

        if ((position == 0) || (position == 1)){
//			item_text.setTextColor(context.getResources().getColor(R.color.black));
            mTvChannel.setEnabled(false);
        }
        if (isChanged && (position == holdPosition) && !isItemShow) {
            mTvChannel.setText("");
            mTvChannel.setSelected(true);
            mTvChannel.setEnabled(true);
            isChanged = false;
        }
        if (!isVisiable && (position == -1 + channelList.size())) {
            mTvChannel.setText("");
            mTvChannel.setSelected(true);
            mTvChannel.setEnabled(true);
        }
        if(removePosition == position){
            mTvChannel.setText("");
        }
        return view;

    }

    /**
     * 频道添加
     * @param mChannelItem
     */
    public void addItem(ChannelItem mChannelItem) {
        channelList.add(mChannelItem);
        isListChanged = true;
        notifyDataSetChanged();
    }

    /**
     * 拖动变更频道排序
     * @param dragPosition
     * @param dropPosition
     */
    public void exchange(int dragPosition,int dropPosition) {
        holdPosition = dropPosition;
        isListChanged = true;
        ChannelItem dragItem =  getItem(dragPosition);
        if (dragPosition < dropPosition) {
            channelList.add(dropPosition+1,dragItem);
            channelList.remove(dragPosition);
        } else {
            channelList.add(dropPosition+1,dragItem);
            channelList.remove(dragPosition +1);
        }
        isChanged = true;
        notifyDataSetChanged();
    }

    /**
     * 获取频道列表数据
     * @return
     */
    public List<ChannelItem> getChannelList() {
        return channelList;
    }

    /**
     * 设置删除的position
     * @param position
     */
    public void setRemovePosition(int position) {
        removePosition = position;
        notifyDataSetChanged();
    }

    /**
     * 删除position
     */
    public void remove() {
        Log.d("DragAdapter", "removePosition:" + removePosition);
        isListChanged = true;
        channelList.remove(removePosition);
        removePosition = -1;
        notifyDataSetChanged();
    }

    public void setItemShow(boolean mItemShow) {
        isItemShow = mItemShow;
    }

    public boolean isVisiable() {
        return isVisiable;
    }

    public void setVisiable(boolean mVisiable) {
        isVisiable = mVisiable;
    }
}
