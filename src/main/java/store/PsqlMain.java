package store;

import model.Post;

/**
 * @author Andrey
 * @date 01/11/2020
 */

public class PsqlMain {
    public static void main(String[] args) {
        Store store = PsqlStore.instOf();
        store.save(new Post(0, "Java Job"));
        for (Post post : store.findAllPosts()) {
            System.out.println(post.getId() + " " + post.getName());
        }
        System.out.println(store.findById(3));
    }
}
