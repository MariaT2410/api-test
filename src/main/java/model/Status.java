package model;

/**  Статусы тикета, используемые в тестах */
public enum Status {
    OPEN(1),
    CLOSED(4),
    ;

    private final Integer code;

    Status(Integer code) {
        this.code=code;
    }

    public Integer getCode() {
        return code;
    }
}
