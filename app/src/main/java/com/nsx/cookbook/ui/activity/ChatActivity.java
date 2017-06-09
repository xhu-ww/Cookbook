package com.nsx.cookbook.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.nsx.cookbook.R;
import com.nsx.cookbook.adapter.ChatListViewAdapter;
import com.nsx.cookbook.base.BaseActivity;
import com.nsx.cookbook.bean.RobotAnswer;
import com.nsx.cookbook.bean.custom.ChatMessage;
import com.nsx.cookbook.interfaces.BeanCallBack;
import com.nsx.cookbook.model.TuringRobotModel;
import com.nsx.cookbook.utils.NetUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/5.
 */

public class ChatActivity extends BaseActivity implements TextView.OnEditorActionListener, TextWatcher {
    TuringRobotModel mOtherNetModel;
    @BindView(R.id.tv_send)
    TextView mTvSend;
    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.chat_listview)
    ListView mChatListview;
    ChatListViewAdapter adapter;
    List<ChatMessage> messageList;

    @Override
    protected void initView() {
        //不让软键盘默认弹出
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //添加默认消息
        messageList = new ArrayList<>();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent("Hi，我是图灵机器人，有什么问题快来问我吧！");
        chatMessage.setMe(false);
        chatMessage.setTime(new Date().getTime());
        messageList.add(chatMessage);

        adapter = new ChatListViewAdapter(this, messageList);
        mChatListview.setAdapter(adapter);
        //选中最后一行
        mChatListview.setSelection(mChatListview.getBottom());

        mEtContent.setOnEditorActionListener(this);
        mEtContent.addTextChangedListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }

    private void requestData(String question) {
        if (NetUtils.isConnected(this)) {
            mOtherNetModel = TuringRobotModel.getInstance();
            mOtherNetModel.getRobotAnswer(question, new BeanCallBack<RobotAnswer>() {
                @Override
                public void onSucceed(RobotAnswer robotAnswer) {
                    //返回的答案
                    String answer = robotAnswer.getResult().getContent();
                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.setContent(answer);
                    chatMessage.setMe(false);
                    chatMessage.setTime(new Date().getTime());
                    messageList.add(chatMessage);
                    //更新数据，并使ListView更新View
                    adapter.setList(messageList);
                    //选中最后一行
                    mChatListview.setSelection(mChatListview.getBottom());
                }

                @Override
                public void onError(String msg) {

                }
            });
        } else {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setContent("您当前未连接网络哦！");
            chatMessage.setMe(false);
            chatMessage.setTime(new Date().getTime());
            messageList.add(chatMessage);
        }
    }

    @OnClick({R.id.chat_back, R.id.tv_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chat_back:
                onBackPressed();
                break;
            case R.id.tv_send:
                sendQuestion();
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        sendQuestion();
        return true;
    }

    /**
     * 发送问题
     */

    private void sendQuestion() {
        //得到问题内容
        String question = mEtContent.getText().toString();
        if (!question.equals("")) {
            //更改聊天的数据
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setContent(question);
            chatMessage.setMe(true);
            chatMessage.setTime(new Date().getTime());
            messageList.add(chatMessage);
            //更新数据，并使ListView更新View
            adapter.setList(messageList);
            //选中最后一行
            mChatListview.setSelection(mChatListview.getBottom());
            //发送数据后，清空输入框
            mEtContent.getEditableText().clear();
            //请求网络
            requestData(question);
        }
    }

    //EditTextView文字变化的事件监听
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 0) {
            mTvSend.setEnabled(true);
        } else {
            mTvSend.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
