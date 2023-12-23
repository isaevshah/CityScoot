package kz.project.moped.domain.enums;

public enum ProductType{
    ACTIVE(true),
    PASSIVE(false);

    private final boolean status;

    ProductType(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }
}
