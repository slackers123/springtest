package at.spengergasse.springtest.presentation;

import at.spengergasse.springtest.domain.Nose;
import at.spengergasse.springtest.presentation.assemblers.NoseModelAssembler;
import at.spengergasse.springtest.presentation.commands.CreateNoseCommand;
import at.spengergasse.springtest.presentation.dto.NoseDto;
import at.spengergasse.springtest.presentation.dto.UserDto;
import at.spengergasse.springtest.service.NoseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.mediatype.hal.Jackson2HalModule;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NoseController.class)
@AutoConfigureMockMvc
public class NoseControllerTest {
    private @MockBean NoseService noseService;
    private @MockBean NoseModelAssembler assembler;
    private @Autowired MockMvc mockMvc;
    private @Autowired ObjectMapper objectMapper;
    private final NoseModelAssembler noseModelAssembler = new NoseModelAssembler();

    @BeforeEach
    void setUp() {

    }

    @Test
    void postRequestCreatesWithValidData() throws Exception {
        CreateNoseCommand cmd = new CreateNoseCommand();

        Nose nose = Nose.builder().id(1L).build();
        NoseDto dto = noseModelAssembler.toModel(nose);

        when(noseService.save(cmd)).thenReturn(nose);

        when(assembler.toModel(any())).thenReturn(dto);

        var add_path = ((PostMapping) Stream.of(NoseController.class.getMethod("createNose", CreateNoseCommand.class).getDeclaredAnnotations()).filter(a -> a.annotationType() == PostMapping.class).findFirst().get()).path();
        var request = post(NoseController.BASE_ROUTE +  (add_path.length > 0 ? add_path[0] : ""))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cmd));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andDo(print());
    }
}
