package GUI;

public class ConfigureScheduleComboBox {
    private String key;
    private String value;

    public ConfigureScheduleComboBox(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return key;
    }
}
