package co.edu.meli.domain.usecase;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class MessageExtractionUseCase {

    public String process(List<String[]> messages) {
        final var parts = countParts(messages);
        final var sb = new ArrayList<String>();
        for (int i = 0; i < parts; i++) {
            var message = extractMessagePart(messages, i);
            buildEndMessage(sb, message);
        }
        return String.join(" ", sb);
    }

    private int countParts(List<String[]> messages) {
        return messages.stream().mapToInt(msgs -> msgs.length).max().orElse(0);
    }

    private String securedExtraction(final String[] message, final int index) {
        try {
            return message[index];
        } catch (IndexOutOfBoundsException ex) {
            return "";
        }
    }

    private List<String> extractMessagePart(List<String[]> messages, int index) {
        return messages.stream().map(msg -> this.securedExtraction(msg, index)).filter(msg -> !msg.isEmpty()).distinct().collect(Collectors.toList());
    }

    private void buildEndMessage(ArrayList<String> sb, List<String> message) {
        sb.add(message.size() != 1 ? "Part missmatch!" : message.get(0));
    }
}
