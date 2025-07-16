package dk.michaelbui.metis.server.api;

import dk.michaelbui.metis.core.domain.Engine;
import dk.michaelbui.metis.core.domain.event.Event;
import dk.michaelbui.metis.plugin.MetisPluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/engine")
public class EngineController {
    private final Engine engine;
    private final MetisPluginManager pluginManager;

    @Autowired
    public EngineController(Engine engine, MetisPluginManager pluginManager) {
        this.engine = engine;
        this.pluginManager = pluginManager;
    }

    @PostMapping("input")
    public ResponseEntity<List<Event>> input(@RequestBody String jsonString) {
        List<Event> events = engine.input(jsonString);
        pluginManager.getEventSinks().forEach(sink -> sink.sendEvents(events));
        return ResponseEntity.ok(events);
    }
}
