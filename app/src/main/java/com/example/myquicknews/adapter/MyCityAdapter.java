package com.example.myquicknews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.myquicknews.CharacterParser;
import com.example.myquicknews.R;
import com.example.myquicknews.model.City;
import com.example.myquicknews.util.PinYinComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 可侧滑弹出删除等控件的RecyclerView
 * Created by Administrator on 2016/11/8.
 */

public class MyCityAdapter extends RecyclerSwipeAdapter<MyCityAdapter.SimpleViewHolder> {
    private Context mContext;
    private LayoutInflater inflater;
    private List<String> list;
    private List<City> cityList;

    public MyCityAdapter(List<String> mList, Context mContext) {
        this.list = mList;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        cityList = new ArrayList<>();
        for (String str : list) {
            City mCity = new City();
            mCity.setFullName(str);
//            mCity.setHideEnable(false);
            String pinyin = CharacterParser.getInstance().getSelling(str);
//            String pinyin = HanziToPinyin.getPinYin(str);

            mCity.setPinyinName(pinyin);
            String sort = pinyin.substring(0, 1).toUpperCase();
            boolean isTrue = sort.matches("[A-Z]");
            Log.d("MyCityAdapter", "isS:" + isTrue);
            if (sort.matches("[A-Z]")) {
                mCity.setFirstLetters(sort.toUpperCase());
            } else {
                mCity.setFirstLetters("#");
            }

            cityList.add(mCity);
        }
        Collections.sort(cityList, new PinYinComparator());
    }

    /**
     * 根据城市名块分组区域位置获取该分组对应的位置
     *
     * @param position
     * @return
     */
    public int getSelectionForPosition(int position) {
        return cityList.get(position).getFirstLetters().charAt(0);
    }

    /**
     * 根据城市名所在位置获得分组区域位置
     *
     * @param selection
     * @return
     */
    public int getPositionForSelection(int selection) {
        for (int i = 0; i < getItemCount(); i++) {
            String str = cityList.get(i).getPinyinName();
            char firstChar = str.toUpperCase().charAt(0);
            if (firstChar == selection) {
                return i;
            }
        }
        return -1;
    }

    protected static class SimpleViewHolder extends RecyclerView.ViewHolder {
        SwipeLayout swipeLayout;
        TextView textViewData;
        TextView buttonDelete;
        TextView mTextView1;
        TextView mTextViewTop;
        TextView mTextViewMark;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            textViewData = (TextView) itemView.findViewById(R.id.tv_city);
            buttonDelete = (TextView) itemView.findViewById(R.id.delete);
            mTextView1 = (TextView) itemView.findViewById(R.id.tv_head_show);
            mTextViewMark = (TextView) itemView.findViewById(R.id.mark);
            mTextViewTop = (TextView) itemView.findViewById(R.id.top_set);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(getClass().getSimpleName(), "onItemSelected: " + textViewData.getText().toString());
                    Toast.makeText(view.getContext(), "onItemSelected: " + textViewData.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_city, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        City city = cityList.get(position);
        int selection = getSelectionForPosition(position);
        if (position == getPositionForSelection(selection)) {
            viewHolder.mTextView1.setVisibility(View.VISIBLE);
            viewHolder.mTextView1.setText(city.getFirstLetters());
        } else {
            viewHolder.mTextView1.setVisibility(View.GONE);
        }

        String item = city.getFullName();
        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        viewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {

            }
        });
        viewHolder.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                cityList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cityList.size());
                mItemManger.closeAllItems();
                Toast.makeText(view.getContext(), "Deleted " + viewHolder.textViewData.getText().toString() + "!", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.mTextViewMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "标记", Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.mTextViewTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "置顶", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.textViewData.setText(item);
        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }
}
