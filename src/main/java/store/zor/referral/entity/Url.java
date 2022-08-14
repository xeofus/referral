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
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "URL_SQ")
    @SequenceGenerator(name = "URL_SQ", sequenceName = "URL_SQ", allocationSize = 1,initialValue=100000)
    private long id;

    @Column(nullable = false, unique = true)
    private Long userId;

    @Column(nullable = false)
    private String longUrlWeb;

    @Column(nullable = false)
    private String longUrlAndroid;

    @Column(nullable = false)
    private String longUrlIos;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(length = 15)
    private String remoteAddr;
}
