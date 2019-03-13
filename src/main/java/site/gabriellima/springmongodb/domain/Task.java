package site.gabriellima.springmongodb.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class Task {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String title;
    private String description;
}
