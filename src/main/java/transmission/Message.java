package transmission;


public class Message {
    enum Side {
        NONE,
        BUY,
        SELL
    }

    int id;
    Side side;
    String fixString;
    String customString;
    long longValue;
    int intValue;
    boolean valid;

    public Message() {
        this.id = -1;
        this.side = Side.NONE;
        setFixString("");
        this.customString = "";
        this.longValue = 0;
        this.intValue = 0;
        valid = false;
    }

    public Message(int id, Side side, String fixString, String customString, long longValue, int intValue, boolean valid) {
        this.id = id;
        this.side = side;
        setFixString(fixString);
        this.customString = customString;
        this.longValue = longValue;
        this.intValue = intValue;
        this.valid = valid;
    }

    public void setMessage(Message message) {
        this.id = message.id;
        this.side = message.side;
        this.fixString = message.fixString;
        this.customString = message.customString;
        this.longValue = message.longValue;
        this.intValue = message.intValue;
        this.valid = message.valid;
    }

    public void setFixString(String val) {
        this.fixString = val.length() == 8 ? val : (val.length() >= 8 ? val.substring(0, 8) : String.format("%1$" + 8 + "s", val).replace(' ', '0'));
    }

    public void setValid(boolean val) {
        this.valid = val;
    }

    @Override
    public String toString() {
        return String.format("[%d, %s, %s, %s, %d, %d, %s]", id, side, fixString, customString, longValue, intValue, valid);
    }
}
