package com.rfw.jiajia.mail.models;

public class Email {
    /**
     * 发送者
     */
    public String sender;
    /**
     * 接收者
     */
    public String receiver;
    /**
     * 邮件类型
     */
    public Integer sendType;
    /**
     * 主题
     */
    public String subject;
    /**
     * 内容
     */
    public String content;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Email [sender=" + sender + ", receiver=" + receiver + ", sendType=" + sendType + ", subject=" + subject
                + ", content=" + content + "]";
    }

}
