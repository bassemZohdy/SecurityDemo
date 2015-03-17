package demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entity.Activation;

public interface ActivationRepo extends JpaRepository<Activation, Long> {
	public Activation findByToken(String token);
}
