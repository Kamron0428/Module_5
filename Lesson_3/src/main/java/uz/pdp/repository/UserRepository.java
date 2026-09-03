package uz.pdp.repository;

import uz.pdp.model.User;
import java.util.UUID;

public interface UserRepository extends BaseRepository<User, UUID> {
    User findByUsername(String username);
}