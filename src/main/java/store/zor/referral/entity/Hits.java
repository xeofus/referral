package store.zor.referral.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "hits")
public class Hits {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private Long urlId;
    @Column(nullable = false)
    private String userAgent;
    @Column(nullable = false)
    private LocalDateTime hitDateTime;
    @Column(length = 15, nullable = false)
    private String remoteAddr;
}
