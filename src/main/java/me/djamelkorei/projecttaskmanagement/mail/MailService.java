package me.djamelkorei.projecttaskmanagement.mail;

import lombok.RequiredArgsConstructor;
import me.djamelkorei.projecttaskmanagement.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * Service for sending emails.
 *
 * @author Djamel Eddine Korei
 */
@Component
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;
    private final MessageSource messageSource;

    public void sendEmail(String to, String subject, String text, boolean isMultipart, boolean isHtml) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text, isHtml);
            emailSender.send(mimeMessage);
        } catch (Exception ignored) {
        }
    }

    public void sendEmailFromTemplate(User user, String templateName, String titleKey) {
        if (user.getEmail() == null) {
            return;
        }
        Context context = new Context(Locale.ENGLISH);
        context.setVariable("user", user);
        String content = templateEngine.process(templateName, context);
        String subject = messageSource.getMessage(titleKey, null, Locale.getDefault());
        sendEmail(user.getEmail(), subject, content, false, true);
    }

    @Async
    public void sendCreationEmail(User user) {
        sendEmailFromTemplate(user, "mail/creationEmail", "email.creation.title");
    }

    @Async
    public void sendPasswordResetMail(User user) {
        sendEmailFromTemplate(user, "mail/passwordResetEmail", "email.reset.title");
    }

}
