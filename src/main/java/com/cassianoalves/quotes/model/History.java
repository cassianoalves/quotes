package com.cassianoalves.quotes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class History {
    @Id
    private String id;
    private Event event;
    @DBRef
    private User user;
    private String originInfo;
    private Object object;

    public enum Event {
        INVITE,
        CHECK_INVITE,
        SIGN_UP,
        LOGIN,
        LIST_QUOTES,
        INSERT_QUOTE,
        CHANGE_USER_DATA
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOriginInfo() {
        return originInfo;
    }

    public void setOriginInfo(String originInfo) {
        this.originInfo = originInfo;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
