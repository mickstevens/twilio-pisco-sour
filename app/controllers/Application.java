package controllers;

import play.*;
import play.mvc.*;
import com.twilio.sdk.verbs.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    // Handle an incoming SMS using the Twilio Twiml Message verb
    public static Result messages() {

      //Use the TwimlResponse object to build up the XML reply.
      TwiMLResponse twiml = new TwiMLResponse();
      Message message = new Message("Thanks for sending me an SMS!");

      try {
        twiml.append(message);
      }
      catch (Exception e) {
        Logger.error("An error occurred trying to append the message verb to the response verb.",e);
      }
      return ok(twiml.toXML()).as("text/xml");
    }
    // Handle an incoming Call using the Twilio Twiml Say verb
    public static Result calls() {

      //Use the TwimlResponse object to build up the XML reply.
      TwiMLResponse twiml = new TwiMLResponse();
      Say message = new Say("Thanks for calling my Play Framework application.");
      message.setVoice("alice");
      message.setLanguage("en-gb");

      try {
      twiml.append(message);
      }
      catch (Exception e) {
      Logger.error("An error occured trying to append the say verb to the response verb.",e);
      }
      return ok(twiml.toXML()).as("text/xml"); 
    }
      // Hey, why not try adding new actions below using the other Twiml verbs; Dial, Record, Gather, Sms,
      // Hangup, Queue, Redirect, Pause & Reject see https://www.twilio.com/docs/api/twiml for more info
}
