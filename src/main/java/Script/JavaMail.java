package Script;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class JavaMail {
    int n;
    boolean get;

    public JavaMail(String mail) {
        int code = Random();
        boolean check = true;
        while(check != false) {
	        if(checkCode(code) == false) {
	        	code = Random();
	        }else {
	        	check = false;
	        }
        }
        this.n = code;
        final String username = "tranminhquan130804@gmail.com";
        final String password = "uest jeaa tkgn blwe";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tranminhquan130804@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mail));
            message.setSubject("Verification");
            message.setText("your verification code :" + n
                    + "\n\n Please do not spam my email!");
            Transport.send(message);

            System.out.println("Done");
            this.get = true;
        } catch (MessagingException e) {
            this.get = false;
            e.printStackTrace();
        }
    }
    public int Random() {
    	Random rand = new Random();
        int n = rand.nextInt(1000) * 100 + rand.nextInt(99);
        return n;
    }
    public boolean checkCode(int code) {
    	int count = 0;
    	boolean check = true;
    	while(check != false) {
    		if(code != 0) {
    			System.out.println(code);
    			code = code/10;
    			count++;
    		}else {
    			check = false;
    		}
    	}
    	if(count != 4) {
    		return false;
    	}
    	return true;
    }
    

}