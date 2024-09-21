package br.com.loto.service.infra;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements IEmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Override
    @SneakyThrows
    public void sendPasswordResetEmail(String to, String subject, Map<String, Object> templateModel) {

        Context context = new Context();
        context.setVariables(templateModel);
        String htmlContent = templateEngine.process("password-reset", context);

        for (Map.Entry<String, Object> fieldMap : templateModel.entrySet()) {
            htmlContent = htmlContent.replace("{{" + fieldMap.getKey() + "}}", (String) fieldMap.getValue());
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        mimeMessage.setHeader("X-Priority", "1");
        mimeMessage.setSubject(subject);
        mimeMessage.setText(htmlContent);
        mimeMessage.setHeader("Content-Type", "text/html; charset=\"utf-8\"");
        mimeMessage.setHeader("Content-Transfer-Encoding", "quoted-printable");
        mimeMessage.setSentDate(new Date());
        //mimeMessage.setFrom(new InternetAddress(emailTemplate.getConfig().getUsername()));

        MimeBodyPart html = new MimeBodyPart();
        html.setHeader("Content-Type", "text/html; charset=\"utf-8\"");
        html.setContent(htmlContent, "text/html; charset=utf-8");
        html.setHeader("Content-Transfer-Encoding", "quoted-printable");

        mailSender.send(mimeMessage);
    }
}
