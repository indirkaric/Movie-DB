package com.indir.moviesdb.service;

import com.indir.moviesdb.domain.User;

public interface MailService {
    void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml);
    void sendPasswordResetMail(User user);
    void sendEmailFromTemplate(User user, String templateName, String titleKey);

}
