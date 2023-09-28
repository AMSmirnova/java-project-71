package hexlet.code;

public class Diffs {
    private final String status;
    private final Object key;
    private final Object valueOld;
    private Object valueNew;

    public Diffs(String status, Object key, Object valueOld) {
        this.status = status;
        this.key = key;
        this.valueOld = valueOld;
    }

    public Diffs(String status, Object key, Object valueOld, Object valueNew) {
        this.status = status;
        this.key = key;
        this.valueOld = valueOld;
        this.valueNew = valueNew;
    }

    public String getStatus() {
        return status;
    }

    public Object getKey() {
        return key;
    }
    public Object getValueOld() {
        return valueOld;
    }
    public Object getValueNew() {
        return valueNew;
    }

}

