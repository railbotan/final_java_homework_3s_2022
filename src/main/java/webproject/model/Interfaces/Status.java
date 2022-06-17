package webproject.model.Interfaces;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Status {
    CREATED("СОЗДАН"),
    EXECUTED("ИСПОЛНЕН");

    @Getter
    private final String value;
}
