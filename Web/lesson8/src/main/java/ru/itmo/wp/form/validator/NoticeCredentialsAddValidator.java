package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.NoticeCredentials;
import ru.itmo.wp.service.NoticeService;

@Component
public class NoticeCredentialsAddValidator implements Validator {
    private final NoticeService noticeService;

    public NoticeCredentialsAddValidator(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    public boolean supports(Class<?> clazz) {
        return NoticeCredentials.class.equals(clazz) || User.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            NoticeCredentials noticeForm = (NoticeCredentials) target;
            if (noticeForm.getContent() == null) {
                errors.rejectValue("content", "content.invalid-content", "invalid content");
            }
        }
    }
}
