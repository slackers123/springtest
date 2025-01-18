package at.spengergasse.springtest.presentation.assemblers;

import at.spengergasse.springtest.domain.Nose;
import at.spengergasse.springtest.presentation.NoseController;
import at.spengergasse.springtest.presentation.dto.NoseDto;
import io.micrometer.common.lang.NonNullApi;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@NonNullApi
@Component
public class NoseModelAssembler implements RepresentationModelAssembler<Nose, NoseDto> {
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public NoseDto toModel(Nose nose) {
        NoseDto noseDto = modelMapper.map(nose, NoseDto.class);
        noseDto.add(linkTo(methodOn(NoseController.class).getNoseById(nose.getId())).withSelfRel());
        return noseDto;
    }
}
