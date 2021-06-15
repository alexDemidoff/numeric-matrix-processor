package processor;

public class Status {

    private String message = "Error";
    private boolean status;

    public void setMessage(String s) {
        message = String.valueOf(s);
    }

    public String getMessage() {
        return message;
    }

    public void isSuccess(boolean status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return status;
    }
}
