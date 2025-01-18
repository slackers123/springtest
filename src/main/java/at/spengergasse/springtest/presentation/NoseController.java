package at.spengergasse.springtest.presentation;

import at.spengergasse.springtest.domain.Nose;
import at.spengergasse.springtest.presentation.assemblers.NoseModelAssembler;
import at.spengergasse.springtest.presentation.commands.CreateNoseCommand;
import at.spengergasse.springtest.presentation.dto.NoseDto;
import at.spengergasse.springtest.service.NoseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

import static at.spengergasse.springtest.presentation.APIBase.API;

@RequiredArgsConstructor
@RestController
@RequestMapping(NoseController.BASE_ROUTE)
public class NoseController {
    public static final String BASE_ROUTE = API + "/nose";
    private final NoseService noseService;
    private final NoseModelAssembler assembler;

    @PostMapping
    public ResponseEntity<NoseDto> createNose(@RequestBody CreateNoseCommand cmd) {
        Nose new_nose = noseService.save(cmd);

        return ResponseEntity.ok(assembler.toModel(new_nose));
    }

    @GetMapping(path ="/{noseId}")
    public ResponseEntity<NoseDto> getNoseById(@PathVariable Long noseId) {
        Optional<Nose> nose = noseService.findById(noseId);
        return nose.map(value -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(assembler.toModel(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
