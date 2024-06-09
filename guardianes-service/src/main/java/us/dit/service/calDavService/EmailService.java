package us.dit.service.calDavService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Properties;

/**
 * Esta clase se encarga de enviar los calendarios individuales a los {@link Doctor}
 *
 * @param username usuario del servicio de email
 * @author carcohcal
 * @version 1.0
 * @date 12 feb. 2022
 */
@Slf4j
@Service
public class EmailService {

    private final Properties properties = new Properties();
    @Value("${email.loggin}")
    private String username;
    @Value("${email.password}")
    private String password;
    @Value("${email.host}")
    private String host;
    @Value("${email.asunto}")
    private String asunto;
    @Value("${email.mensaje}")
    private String mensaje;
    private Session session;

    /**
     * Método que configura las propiedades del servicio
     */
    private void setProperties() {
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.user", username);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", host);
        log.info("Configuracion servicio email completad");
    }

    /**
     * Método que envía los emails de forma asíncrona
     * Este email incluye como archivo adjunto el {@link CalendariosIndivuales} de cada {@link Doctor}
     *
     * @param emailTo direccion de email del destinatario
     * @param nomFich nombre del fichero a enviar como archivo adjunto
     * @throws MessagingException
     * @throws AddressException
     * @author carcohcal
     * @date 12 feb. 2022
     */
    @Async
    public void enviarEmail(String emailTo, String nomFich) throws AddressException, MessagingException {
        setProperties();
        session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });


        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(emailTo));
        message.setSubject(asunto);

        //Texto del mensaje
        MimeBodyPart mimeBodyPart1 = new MimeBodyPart();
        mimeBodyPart1.setContent(mensaje, "text/html");

        //Fichero adjunto
        DataSource source = new FileDataSource(nomFich);
        MimeBodyPart messageBodyPart2 = new MimeBodyPart();
        messageBodyPart2.setDataHandler(new DataHandler(source));
        messageBodyPart2.setFileName(nomFich);

        //Envio de ambas partes
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart1);
        multipart.addBodyPart(messageBodyPart2);

        message.setContent(multipart);
        Transport transport = session.getTransport("smtp");
        transport.connect();
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();

        log.info("Email enviado a: " + emailTo);
        eliminaFichero(nomFich);

    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    private void eliminaFichero(String nombre) {
        File file = new File(nombre);
        if (file.exists()) {
            file.delete();
        }
    }
}