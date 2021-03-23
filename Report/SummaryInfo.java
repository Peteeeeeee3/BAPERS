package Report;

public class SummaryInfo {
    private String location;
    private int value;
    private int shift;
    private int date;

    public int getShift() {
        return shift;
    }
    public int getValue() {
        return value;
    }
    public String getLocation() {
        return location;
    }

    public SummaryInfo(String location, int value, int shift, int date) {
        this.location = location;
        this.value = value;
        this.shift = shift;
        this.date = date;
    }
}
