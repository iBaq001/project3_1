package com.amr.project.service.abstracts;

public interface MailSenderRealization {
    void send(String emailTo, String subject, String message);
}
