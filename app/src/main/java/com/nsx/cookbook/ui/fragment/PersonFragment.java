package com.nsx.cookbook.ui.fragment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;

import com.nsx.cookbook.R;
import com.nsx.cookbook.app.Config;
import com.nsx.cookbook.base.BaseFragment;
import com.nsx.cookbook.utils.DialogHelper;
import com.nsx.cookbook.utils.FileUtil;

import java.io.File;

import butterknife.OnClick;



public class PersonFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_person;
    }

    @Override
    public void initview() {

    }

    @OnClick({R.id.personal_information, R.id.browsing_history, R.id.my_favorite, R.id.about_our, R.id.tv_opinion, R.id.version_check, R.id.clear_cache})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_information:
                createDialog("不好意思，此界面除了清除缓存有用以外，都可以忽略掉。");
                break;
            case R.id.browsing_history:
                createDialog("建议直接使用搜索功能，搜索想要查看的菜谱。");
                break;
            case R.id.my_favorite:
                createDialog("可以点击底部收藏按钮，查看收藏的菜谱。");
                break;
            case R.id.about_our:
                createDialog("此App属于个人创作，所以功能有限。\nQQ邮箱：897532167@qq.com");
                break;
            case R.id.tv_opinion:
                createDialog("如有建议可发送邮件至\nQQ邮箱：897532167@qq.com");
                break;
            case R.id.version_check:
                createDialog("永远都会是：您的版本以是最新版本");
                break;
            case R.id.clear_cache:
                clearCache();
                break;
        }
    }

    /**
     * 清除缓存
     */
    private void clearCache() {
        //文件的路径
        final String path1 = mActivity.getCacheDir().getAbsolutePath() + "/" + "image_manager_disk_cache";
        final String path2 = mActivity.getFilesDir().getAbsolutePath() + "/" + Config.SEARCHFILENAME;
        //得到文件大小
        long size1 = FileUtil.getDirSize(new File(path1));
        long size2 = FileUtil.getDirSize(new File(path2));
        //转换文件大小
        String fileSize = FileUtil.formatFileSize(size1 + size2);

        String content = "当前缓存 " + fileSize + "，确认清除？";
        DialogHelper.getConfirmDialog(mActivity, content, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FileUtil.clearFileWithPath(path1);
                FileUtil.deleteFileWithPath(path2);
            }
        }, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    /**
     * 创建对话框
     *
     * @param content
     */
    private void createDialog(String content) {
        DialogHelper.getConfirmDialog(mActivity, content, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
}
