/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.technique;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URISyntaxException;

/**
 *
 * @author wister
 */
public class SmsSender {
    
    
     public static final String ACCOUNT_SID = "AC5af7d09d79b77e10c0b941098b17bcf9";
    public static final String AUTH_TOKEN = "a50f14e51320b21a3b49654cc398db26";

    public static void send(String nom) throws URISyntaxException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+21695356493"),  // to
                         new PhoneNumber("+18583527665"),  // from
                         nom)
                .create();
        System.out.println(message.getSid());
;
    
}
}
