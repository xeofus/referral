package store.zor.referral.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.zor.referral.entity.Hits;

@Repository
public interface HitsRepository extends JpaRepository<Hits, Long> {
}
