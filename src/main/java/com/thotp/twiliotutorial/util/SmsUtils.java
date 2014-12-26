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

    private static final String ACCOUNT_SID = "ACd5aeb1a060512a4ea09364a0a478dfe8";
    private static final String AUTH_TOKEN = "0b011302dec1f9382e156422c5e15f67";
    private static final String SMS_CENTER = "+15204132927";
    private static final String STATUS_CALLBACK_URL =
            "http://twiliotutorial-trphuctho.rhcloud.com/statusCallback";
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
