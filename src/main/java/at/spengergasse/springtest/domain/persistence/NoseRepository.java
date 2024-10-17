package at.spengergasse.springtest.domain.persistence;

import at.spengergasse.springtest.domain.Nose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoseRepository  extends JpaRepository<Nose, Long> {


}
