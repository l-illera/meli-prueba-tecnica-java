package co.edu.meli.domain.usecase;

import co.edu.meli.domain.model.Result;
import co.edu.meli.domain.model.SpaceshipReference;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ExtractInformationUseCase {

    private final CalculatePositionUseCase positionUseCase;
    private final MessageExtractionUseCase messageUseCase;

    @Inject
    public ExtractInformationUseCase(CalculatePositionUseCase positionUseCase, MessageExtractionUseCase messageUseCase) {
        this.positionUseCase = positionUseCase;
        this.messageUseCase = messageUseCase;
    }

    public Result process(final SpaceshipReference reference, List<String[]> messages) {
        return Result.builder()
                .position(positionUseCase.process(reference))
                .message(messageUseCase.process(messages))
                .build();
    }
}
