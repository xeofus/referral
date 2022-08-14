package store.zor.referral.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(initialValue=100000, allocationSize=1, name = "url_sequence", sequenceName="url_sequence")
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
