package projektOgloszenia.beansy;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

@Singleton
public class EmailSender implements Serializable {

	private static final long serialVersionUID = -5632376112554239949L;

	private static Logger log = Logger.getLogger(EmailSender.class.getName());

	@Resource(name = "mail/mySession")
	private Session session;

	@Asynchronous
	public void sendMail(String to, String subject, String body) {
		MimeMessage m = new MimeMessage(session);
		try {
			InternetAddress[] address = { new InternetAddress(to) };
			m.setFrom(); // pobrany z ustawien Session
			m.setRecipients(Message.RecipientType.TO, address);
			m.setSubject(subject);
			m.setSentDate(new Date());
			m.setText(body);
			Transport.send(m, session.getProperties().getProperty("mail.user"), session.getProperties().getProperty("mail.password"));
			log.info("Wys³ano maila do "+to);
		} catch (MessagingException e) {
			log.error("B³¹d podczas wysy³ania maila", e);
		}
	}

	public EmailSender() {
	}
}