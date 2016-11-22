package com.shubin.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sshubin on 21.11.2016.
 */

@SequenceGenerator(
        name = "MessageLogIdGenerator",
        sequenceName = "MESSAGE_LOG_SEQ",
        allocationSize=50)

@Entity
@Table(name = "MESSAGE_LOG", catalog = "MSGTRACK", schema = "PUBLIC")
public class MessageLog implements Serializable {

    @Id
    @Column(name = "LOG_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MessageLogIdGenerator")
    private Long logId;

    @Column(name = "MESSAGE_IDENTIFIER")
    private String messageIdentifier;

    @Column(name = "STATUS")
    private Integer status;

    public MessageLog() {

    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getMessageIdentifier() {
        return messageIdentifier;
    }

    public void setMessageIdentifier(String messageIdentifier) {
        this.messageIdentifier = messageIdentifier;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return super.toString() + " => [logId: " + logId + ", " +
                            "messageIdentifier: " + messageIdentifier + ", " +
                            "status: " + status + "]";
    }
}
