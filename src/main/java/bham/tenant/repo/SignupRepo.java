package bham.tenant.repo;

import bham.tenant.entity.Signup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignupRepo extends JpaRepository<Signup, Long> {

    boolean existsByWebAddress(String webAddress);
}
