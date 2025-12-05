package com.example.healthifyme.service;

import com.example.healthifyme.config.MailProperties;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    private final Resend resend;
    private final MailProperties props;

    public void sendMail(String to, String subject, String html) throws ResendException {
        CreateEmailOptions createEmailOptions = CreateEmailOptions.builder()
                .from(props.getFrom())
                .to(to)
                .subject(subject)
                .html(html)
                .build();
        resend.emails().send(createEmailOptions);
    }
}

