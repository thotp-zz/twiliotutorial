package com.thotp.twiliotutorial.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class OutboundMessage implements Serializable {

    private static final long serialVersionUID = 2293146270694275750L;

    private int id;
    private String sid;
    private String to;
    private String body;
    private String status;
    private Timestamp createdDate;

    public OutboundMessage() {
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

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

}
