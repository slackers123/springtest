package at.spengergasse.springtest.presentation.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.Jackson2HalModule;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoseDto extends RepresentationModel<NoseDto> {
    private Long id;
}
