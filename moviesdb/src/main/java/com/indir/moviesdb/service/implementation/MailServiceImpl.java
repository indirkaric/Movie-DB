package com.indir.moviesdb.service.implementation;

import com.indir.moviesdb.domain.User;
import com.indir.moviesdb.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.context.Context;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;


@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {

    private static final String USER = "user";
    private static final String BASE_URL = "baseUrl";
    private static final String BASE_URL_VALUE = "http://localhost:4200/#/login/forgot-password";
    private static final String MOVIE_DB_EMAIL = "ahmoahmic386@gmail.com";
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Override
    @Async
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
            message.setTo(to);
            message.setFrom(MOVIE_DB_EMAIL);
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            log.info("Working on thread '{}'", Thread.currentThread().getName());
            log.debug("Sent email to User '{}'", to);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    public void sendPasswordResetMail(User user) {
        sendEmailFromTemplate(user, "mail/passwordResetEmail", "Password reset");
    }

    @Override
    public void sendEmailFromTemplate(User user, String templateName, String titleKey) {
        Context context = new Context();
        context.setVariable(USER, user);
        context.setVariable(BASE_URL, BASE_URL_VALUE);
        String content = templateEngine.process(templateName, context);
        String subject = "Forgot password";
        sendEmail(user.getEmail(), subject, content, false, true);

    }
}
