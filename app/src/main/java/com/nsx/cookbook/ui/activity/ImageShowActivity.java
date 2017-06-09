package com.nsx.cookbook.ui.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nsx.cookbook.R;
import com.nsx.cookbook.base.BaseActivity;
import com.nsx.cookbook.utils.DialogHelper;
import com.nsx.cookbook.utils.FileUtil;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2017/4/24.
 */

public class ImageShowActivity extends BaseActivity implements View.OnLongClickListener {
    @BindView(R.id.iv_food_image)
    PhotoView mIvFoodImage;

    @Override
    protected void initView() {
        String imageUrl = getIntent().getStringExtra("FOOD_IMAGE");
        //设置图片
        Glide.with(this)
                .load(imageUrl)
                .asBitmap()
                .centerCrop()
                .placeholder(R.color.colorAccent)
                .into(mIvFoodImage);
        mIvFoodImage.setOnLongClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_imageshow;
    }


    @Override
    public boolean onLongClick(View v) {
        DialogHelper.getConfirmDialog(this, "是否保存图片", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkPermission();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
        return true;
    }

    private void checkPermission() {
        String permissions = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        if (Build.VERSION.SDK_INT >= 23) {
            int check = ContextCompat.checkSelfPermission(this, permissions);
            if (check == PackageManager.PERMISSION_GRANTED) {
                FileUtil.saveImage(mIvFoodImage, ImageShowActivity.this);
            } else {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        } else {
            FileUtil.saveImage(mIvFoodImage, ImageShowActivity.this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //写入需要权限才能使用的方法
            FileUtil.saveImage(mIvFoodImage, ImageShowActivity.this);
        } else {
            // 没有获取 到权限，从新请求，或者关闭app
            Toast.makeText(this, "需要获得存储权限", Toast.LENGTH_SHORT).show();
        }

    }
}
