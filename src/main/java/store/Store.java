package store;

import model.Candidate;
import model.Post;
import java.util.Collection;

public interface Store {
    Collection<Post> findAllPosts();
    Collection<Candidate> findAllCandidates();
    void save(Post post);
    void saveCandidate(Candidate candidate);
    Post findById(int id);
    Candidate findCandidateById(int id);
}