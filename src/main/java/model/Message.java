package model;

import java.time.LocalDateTime;

public class Message implements Comparable<Message> {

    private int id;
    private String from;
    private String to;
    private String txt;
    private LocalDateTime localDateTime;

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Message.Builder builder(){
        return this.new Builder();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Message() {
    }

    @Override
    public int compareTo(Message o) {
        int result = o.getLocalDateTime().compareTo(localDateTime);
        if (result == 0)
            result = from.compareTo(o.from);
        if (result == 0)
            result = to.compareTo(o.to);
        return result;
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Message.Builder setId(int id) {
            Message.this.id = id;
            return this;
        }

        public Message.Builder setFrom(String from) {
            Message.this.from = from;
            return this;
        }

        public Message.Builder setTo(String to) {
            Message.this.to = to;
            return this;
        }

        public Message.Builder setTxt(String txt) {
            Message.this.txt = txt;
            return this;
        }

        public Message.Builder setLocalDateTime(LocalDateTime localDateTime) {
            Message.this.localDateTime = localDateTime;
            return this;
        }

        public Message build() {
            return Message.this;
        }

    }
}
