package com.gelo.student_management.enums;

public enum RecordType {
    WRITTEN_WORK(0.3),
    PERFORMANCE_TASK(0.5),
    QUARTERLY_ASSESSMENT(0.2);

    private Double rate;

    private RecordType(Double rate) {
        this.rate = rate;
    }

    public Double getRate() {
        return this.rate;
    }
}
