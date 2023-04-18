package com.unsha.app.abstraction;

public interface Event {
    Long getTimeStamp();
    void process();
}
