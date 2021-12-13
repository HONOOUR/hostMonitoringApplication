package jieun.hostMonitoringApplication.modules.host;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Host {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String address;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime lastAliveAt;

    private boolean isAlive;

}
