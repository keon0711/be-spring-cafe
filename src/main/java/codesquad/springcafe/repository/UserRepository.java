package codesquad.springcafe.repository;

import codesquad.springcafe.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {

    private final Logger log = LoggerFactory.getLogger(UserRepository.class);
    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    public void saveUser(User user) {
        user.setId(sequence.incrementAndGet());
        users.put(user.getId(), user);
        log.debug("user saved: {}", user.getUserId());
    }

    public List<User> findAllUsers() {
        return new ArrayList<User>(users.values());
    }
}
