package tests;

import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

public class ReadOutlookEmail {
    public static void main(String[] args) {
        String host = "outlook.office365.com"; // IMAP server for Outlook
        String username = "Ajay@glideinsurance.com";
        String password = "Glide!@#2023";

        try {
            Properties properties = new Properties();
            properties.put("mail.imaps.host", host);
            properties.put("mail.imaps.port", "993");
            properties.put("mail.imap.tls.enable","true");
//            properties.put("mail.smtp.auth", "true");
//            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.debug", "true");

            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore("imaps");
            store.connect(host, username, password);

            Folder emailFolder = store.getFolder("Drafts");
            emailFolder.open(Folder.READ_ONLY);

            Message[] messages = emailFolder.getMessages();

            for (Message message : messages) {
                String subject = message.getSubject();
                String content = message.getContent().toString();

                if (subject.contains("Your OTP Subject")) {
                    // Extract the OTP from the email content
                    // You may use regular expressions or other methods
                    System.out.println("OTP Email Content: " + content);
                }
            }

            emailFolder.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

