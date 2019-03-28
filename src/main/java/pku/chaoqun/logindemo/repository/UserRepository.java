package pku.chaoqun.logindemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pku.chaoqun.logindemo.model.User;

/**
 * @author wuchaoqun
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByUsername(String username);
}
