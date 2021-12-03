package com.example.ngs.model;

import java.io.Serializable;
import java.util.Date;

public class RequestDTO implements Serializable {
    private String timestamp;
    private String requestTopic;

    public RequestDTO() { }
    public RequestDTO(String timestamp, String requestTopic) {
        this.timestamp = timestamp;
        this.requestTopic = requestTopic;
    }

    public String getRequestTopic() {
        return requestTopic;
    }

    public void setRequestTopic(String requestTopic) {
        this.requestTopic = requestTopic;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String  timestamp) {
        this.timestamp = timestamp;
    }
}
