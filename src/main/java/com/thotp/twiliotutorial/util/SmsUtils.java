package com.thotp.twiliotutorial.util;

import com.thotp.twiliotutorial.entity.OutboundMessage;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Message;

public class SmsUtils {

    private static final String ACCOUNT_SID = "<your Account SID>";
    private static final String AUTH_TOKEN = "<your Auth Token>";
    private static final String SMS_CENTER = "<your Twilio Number>";
    private static final String STATUS_CALLBACK_URL =
            "http://<your-application>.rhcloud.com/statusCallback";
    private static final TwilioRestClient client;

    static {
        client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
    }

    public static OutboundMessage sendMessage(String toNumber, String body)
            throws TwilioRestException {
        List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        params.add(new BasicNameValuePair("Body", body));
        params.add(new BasicNameValuePair("To", toNumber));
        params.add(new BasicNameValuePair("From", SMS_CENTER));
        params.add(new BasicNameValuePair("StatusCallback", STATUS_CALLBACK_URL));

        Message msg = client.getAccount().getMessageFactory().create(params);

        if (msg != null) {
            OutboundMessage out = new OutboundMessage();
            out.setSid(msg.getSid());
            out.setTo(toNumber);
            out.setBody(body);
            out.setStatus(msg.getStatus());
            return out;
        }
        return null;
    }
}
