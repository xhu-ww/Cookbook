package com.nsx.cookbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.nsx.cookbook.R;
import com.nsx.cookbook.bean.FoodCategoryBean;
import com.nsx.cookbook.widget.NonScrollGridView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<FoodCategoryBean.ResultBean> mList;

    public ExpandableListViewAdapter(Context context, List<FoodCategoryBean.ResultBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    //获得父项的个数
    @Override
    public int getGroupCount() {
        return mList.size();
    }

    //获得某个父项的子项数目 
    @Override
    public int getChildrenCount(int groupPosition) {
        //子项只有一个GridView
        return 1;
    }

    //获得某个父项  
    @Override
    public Object getGroup(int groupPosition) {
        return mList.get(groupPosition);
    }

    //获得某个父项的某个子项
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mList.get(groupPosition).getList().get(childPosition);
    }

    //获得某个父项的id 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //获得某个父项的某个子项的id 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //按函数的名字来理解应该是是否具有稳定的id，这个方法目前一直都是返回false，没有去改动过  
    @Override
    public boolean hasStableIds() {
        return false;
    }

    //  获得父项显示的view  
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_expandable_group, parent, false);
            holder = new GroupViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        holder.tv_group.setText(mList.get(groupPosition).getName());
        return convertView;
    }

    //  获得子项显示的view  
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_expandable_child, parent, false);
            holder = new ChildViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        //适配器
        GridViewAdapter adapter = new GridViewAdapter(mContext, mList.get(groupPosition).getList());
        holder.gridview.setAdapter(adapter);
        return convertView;
    }

    //子项是否可选中，如果需要设置子项的点击事件，需要返回true  
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder {
        @BindView(R.id.tv_group)
        TextView tv_group;

        public GroupViewHolder(View convertView) {
            ButterKnife.bind(this, convertView);
        }
    }

    class ChildViewHolder {
        @BindView(R.id.gv_child)
        NonScrollGridView gridview;

        public ChildViewHolder(View convertView) {
            ButterKnife.bind(this, convertView);
        }
    }
}
