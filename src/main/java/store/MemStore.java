package store;

import model.Candidate;
import model.Post;
import model.User;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Andrey
 * @date 01/11/2020
 */

public class MemStore {

    private static final MemStore INST = new MemStore();
    private Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private final AtomicInteger postID = new AtomicInteger(4);
    private final AtomicInteger candidateID = new AtomicInteger(4);
    private final Map<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger userID = new AtomicInteger(2);

    private Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private MemStore() {
        posts.put(1, new Post(1, "Junior Java Job", "knowledge of java core", LocalDate.of(2020, 10, 8)));
        posts.put(2, new Post(2, "Middle Java Job", "knowledge of java Framework", LocalDate.of(2020, 10, 8)));
        posts.put(3, new Post(3, "Senior Java Job", "knowledge of architecture", LocalDate.of(2020, 10, 8)));
        candidates.put(1, new Candidate(1, "Junior Java"));
        candidates.put(2, new Candidate(2, "Middle Java"));
        candidates.put(3, new Candidate(3, "Senior Java"));
        users.put(1, new User(1, "Andrey", "newemail@mail.com", "password"));
    }

    public static MemStore instOf() {
        return INST;
    }

    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }

    public Collection<User> findAllUsers() {
        return users.values();
    }

    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(postID.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void saveCandidate(Candidate candidate) {
        if (candidate.getId() == 0) {
            candidate.setId(candidateID.incrementAndGet());
        }
        candidates.put(candidate.getId(), candidate);
    }


    public Candidate findCandidateById(int id) {
        return candidates.get(id);
    }

    public void saveUser(User user) {
        if (user.getId() == 0) {
            user.setId(userID.incrementAndGet());
        }
        users.put(user.getId(), user);
    }


    public User findUserById(int id) {
        return users.get(id);
    }

    public void deleteUserById(int id) {
        users.remove(id);
    }

    public void editUserById(int id, String name, String email, String password) {
       User user = users.get(id);
       user.setEmail(email);
       user.setPassword(password);
       user.setName(name);
       users.replace(id, user);
    }
}
