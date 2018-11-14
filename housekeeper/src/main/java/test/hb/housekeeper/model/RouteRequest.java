package test.hb.housekeeper.model;

public class RouteRequest {
    private String text;
    private String sender;
    private String desctination;

    public RouteRequest() {}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDesctination() {
        return desctination;
    }

    public void setDesctination(String desctination) {
        this.desctination = desctination;
    }
}
