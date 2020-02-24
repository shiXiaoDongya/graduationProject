package com.luoxd.graduation_project.domain;

import lombok.Data;

@Data
public class Message {
    private int id;
    private String fromUserId;
    private String msg;
    private String toUserId;
    private Long time;
    private int isRead;

    public Message(int id, String fromUserId,String msg, String toUserId, long time, int isRead) {
        this.id=id;
        this.fromUserId = fromUserId;
        this.msg = msg;
        this.toUserId = toUserId;
        this.time = time;
        this.isRead = isRead;
    }
}
