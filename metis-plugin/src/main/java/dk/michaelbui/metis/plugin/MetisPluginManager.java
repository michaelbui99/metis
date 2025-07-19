package dk.michaelbui.metis.plugin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MetisPluginManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetisPluginManager.class);
    private final PluginManager pluginManager;
    private final Path pluginDir;
    private final Path pluginConfigFile;
    private final ObjectMapper mapper;
    private MetisPluginsConfiguration pluginsConfiguration;
    private Map<String, EventSink> initializedEventSinks;

    public MetisPluginManager(Path pluginDir, Path pluginConfigFile) {
        this.pluginManager = new DefaultPluginManager(pluginDir);
        this.pluginDir = pluginDir;
        this.pluginConfigFile = pluginConfigFile;
        this.mapper = new ObjectMapper(new YAMLFactory());
        this.initializedEventSinks = new ConcurrentHashMap<>();

        mapper.findAndRegisterModules();
        try {
            LOGGER.info("Reading plugins configuration from {}", pluginConfigFile);
            this.pluginsConfiguration = mapper.readValue(pluginConfigFile.toFile(), MetisPluginsConfiguration.class);
        } catch (IOException e) {
            LOGGER.error("Failed to read plugins configuration from {}. Defaulting to empty configuration", pluginConfigFile, e);
            this.pluginsConfiguration = new MetisPluginsConfiguration();
            this.pluginsConfiguration.setEventSinks(new HashMap<>());
        }
    }

    public void initializePlugins() {
        LOGGER.info("Loading Metis plugins");
        pluginManager.loadPlugins();

        LOGGER.info("Starting Metis plugins");
        pluginManager.startPlugins();


        LOGGER.info("Initializing Metis plugins");
        List<EventSink> eventSinks = pluginManager.getExtensions(EventSink.class);
        LOGGER.info("Detected {} event sinks", eventSinks.size());

        eventSinks.forEach(sink -> {
            EventSinkConfiguration config = pluginsConfiguration.getEventSinks().get(sink.getId());
            if (config == null) {
                LOGGER.warn("Event sink configuration {} not found. Skipping initialization", sink.getId());
                return;
            }

            LOGGER.info("Initializing event sink {}", sink.getId());
            if (initializedEventSinks.containsKey(sink.getId())) {
                LOGGER.warn("An Event Sink with id {} has already initialized. The Event Sink will be overridden", sink.getId());
            }
            sink.initialize(config.getParams());
            initializedEventSinks.put(sink.getId(), sink);
        });

        LOGGER.info("Finished loading Metis plugins. Event sinks initialized: {}/{}", initializedEventSinks.size(), eventSinks.size());
    }

    public synchronized List<EventSink> getEventSinks() {
        return new ArrayList<>(initializedEventSinks.values());
    }
}
