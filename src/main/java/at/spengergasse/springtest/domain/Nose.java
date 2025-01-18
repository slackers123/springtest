package at.spengergasse.springtest.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Nose {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ElementCollection
    @CollectionTable(name = "nose_hairs", joinColumns = @JoinColumn(name = "nose_id"))
    List<NoseHair> hairs;
}
