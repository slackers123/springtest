package at.spengergasse.springtest.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoseDto extends RepresentationModel<NoseDto> {
    private Long id;
}
