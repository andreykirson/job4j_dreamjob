package store;

import model.Candidate;
import model.City;
import model.Post;
import model.User;

import java.util.Collection;

public interface Store {
    Collection<Post> findAllPosts();
    Collection<Candidate> findAllCandidates();
    void save(Post post);
    void saveCandidate(Candidate candidate);
    Post findById(int id);
    Candidate findCandidateById(int id);
    void setCandidatePhoto(String photoSource, int id);
    void deleteCandidate(int id);
    User createUser(User user);
    void saveUser(User user);
    User findUserById(int id);
    User findUserByEmail(String email);
    Collection<User> findAllUser();
    void updateUser(User user);
    void deleteUser(int id);
    Collection<City> findAllCities();
}