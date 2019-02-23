package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.configs.constants.LocalConstants;
import com.devcomanda.easymeetup.model.entity.User;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@Service
public class MailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${emails.from-user}")
    private String emailFrom;

    @Value("${emails.activation-base-url}")
    private String activationBaseUrl;

    @Autowired
    public MailService(final JavaMailSender javaMailSender, final SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendEmail(final String to, final String subject,
                          final String content, final boolean isMultipart, final boolean isHtml
    ) {
        final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        try {
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
            message.setTo(to);
            message.setFrom(this.emailFrom);
            message.setSubject(subject);
            message.setText(content, isHtml);
            this.javaMailSender.send(mimeMessage);
        } catch (final Exception e) {
        }
    }

    @Async
    public void sendEmailFromTemplate(
            final String to, final String subject, final String templateName,
            final Map<String, Object> context
    ) {
        final Context templateContext = new Context(Locale.getDefault());
        templateContext.setVariables(context);
        final String content = this.templateEngine.process(templateName, templateContext);
        this.sendEmail(to, subject, content, false, true);
    }


    public void sendActivationEmail(User user){
        final Map<String, Object> context = new HashMap<>(2);
        context.put("user", user);
        context.put("baseUrl", this.activationBaseUrl);
        this.sendEmailFromTemplate(user.getEmail(), LocalConstants.ACTIVATE_ACCOUNT_SUBJECT
                , "mails/activationEmail", context);
    }
}
