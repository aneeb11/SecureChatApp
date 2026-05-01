package model;

public class Message {
    private String sender;
    private String receiver;
    private String content;
    private String timestamp;

    public Message(String sender, String timestamp, String content, String receiver) {
        this.sender = sender;
        this.timestamp = timestamp;
        this.content = content;
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }

    public String getTimestamp() {
        return timestamp;
    }
}

