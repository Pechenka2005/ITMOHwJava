package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.*;
import ru.itmo.wp.repository.PostRepository;
import ru.itmo.wp.repository.TagRepository;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    /** @noinspection FieldCanBeLocal, unused */
    private final TagRepository tagRepository;

    public PostService(PostRepository postRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;

        this.tagRepository = tagRepository;
        for (Tag.Name name : Tag.Name.values()) {
            if (tagRepository.countByName(name) == 0) {
                tagRepository.save(new Tag(name));
            }
        }
    }

    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreationTimeDesc();
    }

    public Post findById(long id) {
        return postRepository.findById(id);
    }

    public void writeComment(Post post, User user, Comment comment) {
        post.addComment(comment);
        comment.setUser(user);
        postRepository.save(post);
    }
}
