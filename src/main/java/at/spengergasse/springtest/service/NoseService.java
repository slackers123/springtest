package at.spengergasse.springtest.service;

import at.spengergasse.springtest.domain.Nose;
import at.spengergasse.springtest.domain.persistence.NoseRepository;
import at.spengergasse.springtest.presentation.commands.CreateNoseCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class NoseService {
    private final NoseRepository repository;

    //gets
    @Transactional(readOnly = true)
    public List<Nose> findAll() {return repository.findAll(); }

    //save (can be used for UPDATE and POST unless they have different business logic)
    public Nose save(CreateNoseCommand cmd) {
        Nose nose = Nose.builder().build();
        return repository.save(nose);
    }

    //delete
    public Nose delete(Nose nose) {
        if(nose == null) {
            return null;
        }
        repository.delete(nose);
        return nose;
    }

    public Optional<Nose> findById(Long id) {
        return repository.findById(id);
    }

}
