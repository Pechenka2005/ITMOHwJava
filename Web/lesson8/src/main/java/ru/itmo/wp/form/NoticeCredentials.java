package ru.itmo.wp.form;

import ru.itmo.wp.domain.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("unused")
public class NoticeCredentials {
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 64)
    private String content;

    private User user;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
