package xyz.cleangone.message;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import xyz.cleangone.util.CleangoneEnv;

public class EmailSender
{
    private static final String FROM = "noreply@cleangone.xyz";
    private final AmazonSimpleEmailService client;

    public EmailSender()
    {
        client = AmazonSimpleEmailServiceClientBuilder.standard().withRegion(CleangoneEnv.REGION).build();
    }

    public boolean sendEmail(String to, String subject, String htmlBody, String textBody)
    {
        try
        {
            SendEmailRequest request = new SendEmailRequest()
                .withSource(FROM)
                .withDestination(new Destination().withToAddresses(to))
                .withMessage(new Message()
                    .withSubject(new Content().withCharset("UTF-8").withData(subject))
                    .withBody(new Body()
                        .withHtml(new Content().withCharset("UTF-8").withData(htmlBody))
                        .withText(new Content().withCharset("UTF-8").withData(textBody))));

            client.sendEmail(request);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }
}
