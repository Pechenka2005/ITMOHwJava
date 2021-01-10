package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.PostService;
import ru.itmo.wp.service.CommentService;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class PostPage extends Page {
    private final PostService postService;
    private final UserService userService;

    public PostPage(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @Guest
    @GetMapping({"/post/", "/post"})
    public String index() {
        return "IndexPage";
    }

    @Guest
    @GetMapping("/post/{id}")
    public String post(Model model, @PathVariable("id") String id) {
        long allowId = tryParse(id);
        Post post = postService.findById(allowId);
        model.addAttribute("post", post);
        return "CommentPage";
    }

    long tryParse(String s) {
        long res = 0;
        try {
            res = Long.parseLong(s);
        } catch (Exception ex) { /**/ }
        return res;
    }

    @PostMapping("/post/{id}")
    public String postPost(@ModelAttribute("comment") Comment comment,
                           @PathVariable("id") String id,
                           BindingResult bindingResult,
                           HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "CommentPage";
        }

        long allowId = tryParse(id);
        if (comment.getText().length() == 0) {
            putMessage(httpSession, "Comment can not be empty.");
            return "redirect:/post/" + allowId;
        }
        User user = userService.findById((long)httpSession.getAttribute("userId"));
        postService.writeComment(postService.findById(allowId), user, comment);
        putMessage(httpSession, "You published new comment");

        return "redirect:/post/" + allowId;
    }
}