package com.nsx.cookbook.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nsx.cookbook.R;
import com.nsx.cookbook.app.Config;
import com.nsx.cookbook.base.BaseActivity;
import com.nsx.cookbook.bean.FoodCategoryBean;
import com.nsx.cookbook.model.LocalJsonResolutionUtils;
import com.nsx.cookbook.utils.FileUtil;
import com.nsx.cookbook.utils.StringUtils;
import com.nsx.cookbook.widget.WordWrapView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class SearchActivity extends BaseActivity implements TextWatcher, TextView.OnEditorActionListener {
    @BindView(R.id.iv_search_text_clear)
    ImageView mIvSearchTextClear;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.wwv_search_history)
    WordWrapView mSearchHistory;
    @BindView(R.id.wwv_hot_search)
    WordWrapView mHotSearch;

    @Override
    protected void initView() {
        //禁止输入空格
        StringUtils.setEditTextInhibitInputSpace(mEtSearch);
        //输入框设置文本改变事件监听
        mEtSearch.addTextChangedListener(this);
        //输入框软键盘回车键监听
        mEtSearch.setOnEditorActionListener(this);
        //设置历史搜索 记录显示
        setHistoryView();
        //设置热门搜索
        setHotSearch();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }


    @OnClick({R.id.food_search_back, R.id.tv_search_button, R.id.iv_search_text_clear,
            R.id.iv_search_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.food_search_back:
                onBackPressed();
                break;
            case R.id.tv_search_button:
                //搜索
                search();
                break;
            case R.id.iv_search_text_clear:
                //清空当前文本
                mEtSearch.getEditableText().clear();
                break;
            case R.id.iv_search_clear:
                //删除 历史记录 数据，清空子View
                deleteSearchRecord();
                mSearchHistory.removeAllViews();
                showToast("成功删除历史记录");
                break;
        }
    }

    //输入文本之前调用
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    //输入文本时调用
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //文本长度大于0 则显示清除文本的图片
        if (s.length() > 0) {
            mIvSearchTextClear.setVisibility(View.VISIBLE);
        } else {
            mIvSearchTextClear.setVisibility(View.INVISIBLE);
        }
    }

    //输入文本之后调用
    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * 搜索菜谱，即带数据 跳转界面 并保存搜索内容
     */
    private void search() {
        String content = mEtSearch.getText().toString();
        //去掉空格
        if (content.equals("")) {
            showToast("请输入搜索内容");
        } else {
            //保存搜索的 文本内容
            saveSearchRecord(content);
            Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
            String searchContent = mEtSearch.getText().toString();
            intent.putExtra("SEARCH", searchContent);
            //随意的 请求码
            startActivityForResult(intent, 123);
        }
    }

    /**
     * 保存搜索的文本记录
     *
     * @param content 文件名
     */
    private void saveSearchRecord(String content) {
        //文件保存在 /data/data/PACKAGE_NAME/files 目录下 并以 #' 分割
        FileUtil.write(this, Config.SEARCHFILENAME, content + "#'");
    }

    /**
     * 删除搜索的文本记录
     */
    private void deleteSearchRecord() {
        File file = this.getFilesDir();
        File newFile = new File(file, Config.SEARCHFILENAME);
        //删除文件
        newFile.delete();
    }

    /**
     * 得到搜索记录的 文本数据集合
     *
     * @return
     */
    private List<String> getSearchRecord() {
        List<String> list = new ArrayList<>();
        String content = FileUtil.read(this, Config.SEARCHFILENAME);
        Log.e(TAG, "------------------------" + content);
        //将得到的文本 以 #' 分割出来
        String[] array = content.split("#'");
        //逆序添加数据 进集合，最后搜索的 第一个显示
        for (int i = array.length - 1; i >= 0; i--) {
            if (!array[i].equals("")) {
                list.add(array[i]);
            }
        }
        return list;
    }

    //输入框软键盘事件监听
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        search();
        return true;
    }

    /**
     * 设置历史记录
     */
    private void setHistoryView() {
        List<String> searchHistory = getSearchRecord();
        if (searchHistory != null && searchHistory.size() > 0) {
            //清空子View后再次 设置新的
            mSearchHistory.removeAllViewsInLayout();
            for (int i = 0; i < searchHistory.size(); i++) {
                //搜索的内容
                final String searchText = searchHistory.get(i);
                //新建LinearLayout 将XML布局 添加到此View中 然后添加到 WordWrapView自动换行布局中
                LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this).inflate(
                        R.layout.item_search_history, null);
//                //设置 控件之间的间隔
//                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                params.setMargins(5,5,5,5);
//                linearLayout.setLayoutParams(params);
                //设置文字内容
                TextView textView = (TextView) linearLayout.findViewById(R.id.tv_search_history);
                textView.setText(searchText);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //跳转界面
                        Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                        intent.putExtra("SEARCH", searchText);
                        startActivityForResult(intent, 123);
                    }
                });

                mSearchHistory.addView(linearLayout);
            }
        }
    }
    /**
     * 设置热门搜索
     */
    private void setHotSearch() {
        //得到本地json文本内容
        String fileName = "food.json";
        String foodJson = LocalJsonResolutionUtils.getJson(this, fileName);
        //转换为对象 用于热门搜索
        FoodCategoryBean foodCategoryBean = LocalJsonResolutionUtils.JsonToObject(foodJson, FoodCategoryBean.class);
        List<FoodCategoryBean.ResultBean> list = foodCategoryBean.getResult();
        list.remove(3);
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < 2; j++) {
                int position = (int) (Math.random() * 20);
                final String hotSearch = list.get(i).getList().get(position).getName();
                //新建LinearLayout 将XML布局 添加到此View中 然后添加到 WordWrapView自动换行布局中
                LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this).inflate(
                        R.layout.item_search_history, null);
                //设置文字内容
                TextView textView = (TextView) linearLayout.findViewById(R.id.tv_search_history);
                textView.setText(hotSearch);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //跳转界面
                        Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                        intent.putExtra("SEARCH", hotSearch);
                        saveSearchRecord(hotSearch);
                        startActivityForResult(intent, 123);
                    }
                });
                mHotSearch.addView(linearLayout);
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setHistoryView();
    }
}
