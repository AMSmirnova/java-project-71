package hexlet.code;

public class Diffs {
    private String status;
    private Object key;
    private Object valueOld;
    private Object valueNew;

    public Diffs(String status, Object key, Object valueOld, Object valueNew) {
        this.status = status;
        this.key = key;
        this.valueOld = valueOld;
        this.valueNew = valueNew;
    }

    public final String getStatus() {
        return status;
    }

    public final Object getKey() {
        return key;
    }

    public final Object getValueOld() {
        return valueOld;
    }

    public final Object getValueNew() {
        return valueNew;
    }
}

