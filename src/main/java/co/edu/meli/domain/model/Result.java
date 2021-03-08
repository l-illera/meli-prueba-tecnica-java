package co.edu.meli.domain.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Result {
    private final Point position;
    private final String message;
}
