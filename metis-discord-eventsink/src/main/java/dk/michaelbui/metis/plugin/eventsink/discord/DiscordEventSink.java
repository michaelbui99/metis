package dk.michaelbui.metis.plugin.eventsink.discord;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dk.michaelbui.metis.core.domain.event.Event;
import dk.michaelbui.metis.plugin.EventSink;
import org.pf4j.Extension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@Extension
public class DiscordEventSink implements EventSink {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscordEventSink.class);
    private static final String WEBHOOK_URL_PARAM = "webhook_url";
    private static final String USERNAME_PARAM = "username";
    private static final String AVATAR_URL_PARAM = "avatar_url";
    private static final String CONTENT_PARAM = "content";

    private String webhookUrl;
    private String username;
    private String avatarUrl;
    private String content;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String getId() {
        return "metis_eventsink_builtin_discord";
    }

    @Override
    public void initialize(Map<String, Object> params) {
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());

        this.webhookUrl = (String) params.get(WEBHOOK_URL_PARAM);
        this.username = (String) params.getOrDefault(USERNAME_PARAM, "Metis");
        this.avatarUrl = (String) params.getOrDefault(AVATAR_URL_PARAM, "");
        this.content = (String) params.getOrDefault(CONTENT_PARAM, "New Metis Events");

        LOGGER.info("Discord Event Sink initialized.");
    }

    @Override
    public void sendEvents(List<Event> events) {
        DiscordMessage message = new DiscordMessage();
        message.setUsername(username);
        message.setAvatarUrl(avatarUrl);
        message.setContent(content);

        for (Event event : events) {
            DiscordEmbed embed = new DiscordEmbed();
            embed.setTitle(String.format("Metis event: %s", event.getName()));
            try {
                embed.setDescription(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(event));
            } catch (JsonProcessingException e) {
                LOGGER.error("Failed to serialize event", e);
                continue;
            }
            message.getEmbeds().add(embed);
        }

        try (HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = null;
            try {
                request = HttpRequest.newBuilder()
                        .uri(URI.create(webhookUrl))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(message)))
                        .build();
            } catch (JsonProcessingException e) {
                LOGGER.error("Failed to serialize discord message", e);
                return;
            }

            try {
                client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                if (e instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
                LOGGER.error("Failed to send events to discord", e);
            }
        }
    }
}
