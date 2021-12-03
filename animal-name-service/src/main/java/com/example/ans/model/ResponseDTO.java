package com.example.ans.model;

import java.io.Serializable;

public class ResponseDTO implements Serializable {
    private String timestamp;
    private String fromTopic;
    private String name;

    public ResponseDTO(String timestamp, String fromTopic, String name) {
        this.timestamp = timestamp;
        this.fromTopic = fromTopic;
        this.name = name;
    }

    public ResponseDTO(String timestamp,  String name) {
        this.timestamp = timestamp;
        this.name = name;
    }

    public ResponseDTO() {}

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromTopic() {
        return fromTopic;
    }

    public void setFromTopic(String fromTopic) {
        this.fromTopic = fromTopic;
    }
}
