package at.spengergasse.springtest.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Nose {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "nose_hairs", joinColumns = @JoinColumn(name = "nose_id"))
    private List<NoseHair> hairs;
}
