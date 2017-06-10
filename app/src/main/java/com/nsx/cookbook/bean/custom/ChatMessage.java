package com.nsx.cookbook.bean.custom;


public class ChatMessage {

    public static final int TYPE_CHAT = 1;
    public static final int TYPE_SYS = 2;

    String content;
    int type;// 聊天消息，系统消息
    String hostName;

    boolean isMe;

    public ChatMessage(String content, int type, String hostName) {
        super();
        this.content = content;
        this.type = type;
        this.hostName = hostName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public boolean isMe() {
        return isMe;
    }

    public void setMe(boolean me) {
        isMe = me;
    }

    long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public ChatMessage() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", type=" + type +
                ", hostName='" + hostName + '\'' +
                ", isMe=" + isMe +
                '}';
    }
}
