package dk.michaelbui.metis.server.api;

import dk.michaelbui.metis.server.domain.Engine;
import dk.michaelbui.metis.server.domain.event.Event;
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

    @Autowired
    public EngineController(Engine engine) {
        this.engine = engine;
    }

    @PostMapping("input")
    public ResponseEntity<List<Event>> input(@RequestBody String jsonString){
        List<Event> events = engine.input(jsonString);
        return ResponseEntity.ok(events);
    }
}
