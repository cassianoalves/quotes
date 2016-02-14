package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.model.History;
import com.cassianoalves.quotes.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HistoryComponentImpl implements HistoryComponent{
    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public void insertEvent(History.Event event) {
        History history = new History();

    }
}
