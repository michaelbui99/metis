package dk.michaelbui.metis.plugin.eventsink.discord;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class DiscordMessage {
    private String username;
    private String content;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    private List<DiscordEmbed> embeds = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<DiscordEmbed> getEmbeds() {
        return embeds;
    }

    public void setEmbeds(List<DiscordEmbed> embeds) {
        this.embeds = embeds;
    }
}
