package br.com.loto.service.infra;

import java.util.Map;

public interface IEmailService {

    void sendPasswordResetEmail(String to, String subject, Map<String, Object> templateModel);
}
