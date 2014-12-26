package com.thotp.twiliotutorial.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class InboundMessage implements Serializable {

    private static final long serialVersionUID = 7136207962952889031L;

    private int id;
    private String sid;
    private String from;
    private String body;
    private Timestamp receivedDate;

    public InboundMessage() {
    }
    
//    ...  
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Timestamp receivedDate) {
        this.receivedDate = receivedDate;
    }

}
