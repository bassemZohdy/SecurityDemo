package demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entity.User;

public interface UserRepo extends JpaRepository<User, String> {

}
