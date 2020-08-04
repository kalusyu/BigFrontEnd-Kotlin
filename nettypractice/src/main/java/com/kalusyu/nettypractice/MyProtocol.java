package com.kalusyu.nettypractice;

public class MyProtocol {

    private int protocolNo;
    private int contentLength;
    private byte[] content;

    public MyProtocol(int protocolNo, byte[] content) {
        this.protocolNo = protocolNo;
        this.content = content;
        this.contentLength = content.length;
    }

    public MyProtocol(int protocolNo, int contentLength, byte[] content) {
        this.protocolNo = protocolNo;
        this.contentLength = contentLength;
        this.content = content;
    }

    public int getProtocolNo() {
        return protocolNo;
    }

    public void setProtocolNo(int protocolNo) {
        this.protocolNo = protocolNo;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

}