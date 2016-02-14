package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.model.History;

public interface HistoryComponent {
    void insertEvent(History.Event event);
}
