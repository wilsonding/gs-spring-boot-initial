package hello;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {

	public User findByName(String userName);

}
