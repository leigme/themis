package me.leig.project.themis.bean;

/**
 * TODO
 *
 * @Author leig
 * @Date 2021/3/14
 **/
public class Message {

    private int status;

    private String message;

    public Message() {}

    public Message(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
