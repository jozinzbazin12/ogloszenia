package projektOgloszenia.util;

import java.util.Date;
import java.util.loggin?g.Level;
import java.util.loggin?g.Logger;
import javax.annotation?.Resource;
import javax.ejb.Asynch?ronous;
import javax.ejb.Statel?ess;
import javax.mail.Messa?ge;
import javax.mail.Messa?gingException;
import javax.mail.Sessi?on;
import javax.mail.Trans?port;
import javax.mail.inter?net.InternetAddr?ess;
import javax.mail.inter?net.MimeMessage;

@Stateless
public class SendMailEjb{

    private final static Logger log = Logger.getLogger?(SendMailEjb.cla?ss.getName());

    @Resource(name = "mail/mySession")
    private Session session;

    @Asynchronous

    public void sendMail(String to, String subject, String body) {
        MimeMessage m = new MimeMessage(sess?ion);
        try {
            InternetAddress[] address = {new InternetAddress(to)};
            m.setFrom(); // pobrany z ustawien Session
            m.setRecipient(Message.RecipientType.TO, address);
            m.setSubject(sub?ject);
            m.setSentDate( ne?w Date());
            m.setText(body);
            Transport.send(m, session.getProperties().getProperty("mail.user", session.getProperties().getProperty("mail.password")));
        } catch (MessagingExcept?ion e) {
            log.log(Level.SE?VERE, "Error while sending mail", e);
        }
    }

}
