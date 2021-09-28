package com.example.MailProj.services;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.search.FlagTerm;
import java.io.File;
import java.util.Properties;

@Service
public class MailReaderService {
    public String getLastMailId() throws Exception {

        try {
            Properties props = new Properties();
            props.setProperty("mail.imap.ssl.enable", "true");
            Session mailSession = Session.getInstance(props);
            mailSession.setDebug(true);
            Store mailStore = mailSession.getStore("imap");
            mailStore.connect("outlook.live.com", "atishmicro@outlook.com", "atish123@");
            if (mailStore.isConnected())
                System.out.println("voila");
            Folder folder = mailStore.getFolder("Inbox");
            folder.open(Folder.READ_WRITE);
            Message[] messages = folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
            for (Message message : messages) {


                message.setFlag(Flags.Flag.SEEN, true);

                Multipart multiPart = (Multipart) message.getContent();
                int numberOfParts = multiPart.getCount();
                for (int partCount = 0; partCount < numberOfParts; partCount++) {
                    MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                    if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                        String file = part.getFileName();
                        part.saveFile("D:\\" + File.separator + part.getFileName());

                    }
                }
            }

            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }
}
