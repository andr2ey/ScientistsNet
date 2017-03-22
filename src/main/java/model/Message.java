package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created on 19.03.2017.
 */
public class Message implements Comparable<Message> {

    private int id;
    private String from;
    private String to;
    private String txt;
    private LocalDate localDate;
    private LocalTime localTime;

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
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
        int result = localDate.compareTo(o.getLocalDate());
        if (result == 0)
            result = localTime.compareTo(o.getLocalTime());
        if (result == 0)
            result = from.compareTo(o.from);
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

        public Message.Builder setLocalDate(LocalDate localDate) {
            Message.this.localDate = localDate;
            return this;
        }

        public Message.Builder setLocalTime(LocalTime localTime) {
            Message.this.localTime = localTime;
            return this;
        }

        public Message build() {
            return Message.this;
        }

    }
}
