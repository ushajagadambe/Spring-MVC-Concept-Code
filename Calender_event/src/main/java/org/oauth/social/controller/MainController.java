package org.oauth.social.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import org.oauth.social.model.CalendarObj;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.Calendar.Events;
import com.google.api.services.calendar.model.Event;
  
@Controller
public class MainController {
  
    private static final String APPLICATION_NAME = "Accent guru";
	private static HttpTransport httpTransport;
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static com.google.api.services.calendar.Calendar client;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
	
	GoogleClientSecrets clientSecrets;
	GoogleAuthorizationCodeFlow flow;
	Credential credential;

	@Value("${google.client.client-id}")
	private String clientId;
	@Value("${google.client.client-secret}")
	private String clientSecret;
	@Value("${google.client.redirectUri}")
	private String redirectURI;
	@Value("${google.client.redirectUri.available.slot}")
	private String redirectURIAvailableSlot;
	
	private Set<Event> events = new HashSet<>();
	
	private final int START_HOUR = 8;
	private final int START_MIN = 00;
	private final int END_HOUR = 20;
	private final int END_MIN = 00;
	
	private static boolean isAuthorised = false;

	public void setEvents(Set<Event> events) {
		this.events = events;
	}
  
    private String authorize(String redirectURL) throws Exception {
    	AuthorizationCodeRequestUrl authorizationUrl;
		if (flow == null) {
			Details web = new Details();
			web.setClientId(clientId);
			web.setClientSecret(clientSecret);
			clientSecrets = new GoogleClientSecrets().setWeb(web);
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets,
					Collections.singleton(CalendarScopes.CALENDAR)).build();
		}
		authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectURL);
		
		isAuthorised = true;
		
		return authorizationUrl.build();
	}
    
    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
	public RedirectView googleConnectionStatus(HttpServletRequest request) throws Exception {
    	return new RedirectView(authorize(redirectURI));
	}
    
    @RequestMapping(value = "/calendar", method = RequestMethod.GET, params = "code")
    public String oauth2Callback(@RequestParam(value = "code") String code, Model model) {
    	if(isAuthorised) {
    		try {
    			
    			model.addAttribute("title", "Today's Calendar Events (" +START_HOUR+":"+START_MIN +" - "+END_HOUR+":"+END_MIN +")");
		        //model.addAttribute("calendarObjs", getTodaysCalendarEventList(code, redirectURI));
				model.addAttribute("link", createGoogleCaldendar(code,redirectURI));
		        
			} catch (Exception e) {
				e.printStackTrace();
			}
			
    		return "agenda";
    	} else {
    		return "/";
    	}	
	}
    public Set<Event> getEvents() throws IOException {
		return this.events;
	}
  
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String accessDenied(Model model) {
  
    	model.addAttribute("message", "Not authorised.");	
        return "login";
        
    }
  
    @RequestMapping(value = { "/", "/login", "/logout" }, method = RequestMethod.GET)
    public String login(Model model) {
    	isAuthorised = false;
    	
    	return "login";
    }   
    private String createGoogleCaldendar(String calenderApiCode,String redirectURL)  {
		String link=null;
		try {
			TokenResponse response = flow.newTokenRequest(calenderApiCode).setRedirectUri(redirectURL).execute();
			credential = flow.createAndStoreCredential(response, "userID");
			client = new com.google.api.services.calendar.Calendar.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
			Event event = new Event()
					.setSummary("section english 101 ")
					.setDescription("Grammer");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss", Locale.ENGLISH);
			String dateStart = "2021 01 20 10:00:00";
			java.util.Date start = sdf.parse(dateStart);
			DateTime startDateTime = new DateTime(start);
			EventDateTime eventDateTimestart = new EventDateTime().setDateTime(startDateTime).setTimeZone("Asia/Kolkata");
			event.setStart(eventDateTimestart);
			String dateEnd = "2021 01 20 11:00:00";
			java.util.Date end = sdf.parse(dateEnd);
			DateTime endDateTime = new DateTime(end);
			EventDateTime eventDateTimeEnd = new EventDateTime().setDateTime(endDateTime).setTimeZone("Asia/Kolkata");
			event.setEnd(eventDateTimeEnd);
			event.setRecurrence(Arrays.asList(
					"       RRULE:FREQ=WEEKLY;BYDAY=FR;COUNT=5"));
   /* event.setRecurrence(Arrays.asList(
            "       RRULE:FREQ=WEEKLY;UNTIL=20210131T240000Z"));*/

			EventAttendee[] attendees = new EventAttendee[]{

					//new EventAttendee().setEmail("rhts012@gmail.com"),
					 new EventAttendee().setEmail("rautdarshan83@gmail.com"),


			};
			event.setAttendees(Arrays.asList(attendees));


			EventReminder[] reminderOverrides = new EventReminder[]{
					new EventReminder().setMethod("email").setMinutes(10),
					new EventReminder().setMethod("popup").setMinutes(10),
			};
			Event.Reminders reminders = new Event.Reminders()
					.setUseDefault(false)
					.setOverrides(Arrays.asList(reminderOverrides));
			event.setReminders(reminders);

			String calendarId = "primary";
			event = client.events().insert(calendarId, event).setSendNotifications(true).execute();
			//event =client.events().insert(calendarId, event).setSendUpdates("all").execute();
			System.out.printf("Event created: %s\n", event.getHtmlLink());
			 link = event.getHtmlLink();
			return link;
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return link;
	}
    
    /*private List<CalendarObj> getTodaysCalendarEventList(String calenderApiCode, String redirectURL) {
		try {
			com.google.api.services.calendar.model.Events eventList;

			LocalDateTime localDateTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()); 
			LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN); 
			LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);

			DateTime date1 = new DateTime(Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant()));
			DateTime date2 = new DateTime(Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant()));

			TokenResponse response = flow.newTokenRequest(calenderApiCode).setRedirectUri(redirectURL).execute();
			credential = flow.createAndStoreCredential(response, "user");
			client = new com.google.api.services.calendar.Calendar.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
			Events events = client.events();
			eventList = events.list("primary").setSingleEvents(true).setTimeMin(date1).setTimeMax(date2).setOrderBy("startTime").execute();

			List<Event> items = eventList.getItems();

			CalendarObj calendarObj;
			List<CalendarObj> calendarObjs = new ArrayList<CalendarObj>();

			for (Event event : items) {

				Date startDateTime = new Date(event.getStart().getDateTime().getValue());
				Date endDateTime = new Date(event.getEnd().getDateTime().getValue());
	
	
				long diffInMillies = endDateTime.getTime() - startDateTime.getTime();
				int diffmin = (int) (diffInMillies / (60 * 1000));
	
				calendarObj = new CalendarObj();
	
				if(event.getSummary() != null && event.getSummary().length() > 0) {
					calendarObj.setTitle(event.getSummary());
				} else {
					calendarObj.setTitle("No Title");
				}

				calendarObj.setStartHour(startDateTime.getHours());
				calendarObj.setStartMin(startDateTime.getMinutes());
				calendarObj.setEndHour(endDateTime.getHours());
				calendarObj.setEndMin(endDateTime.getMinutes());
				calendarObj.setDuration(diffmin);
	
				calendarObj.setStartEnd(sdf.format(startDateTime) + " - " + sdf.format(endDateTime));
	
				calendarObjs.add(calendarObj);
			}

			return calendarObjs;
	        
		} catch (Exception e) {
			return new ArrayList<CalendarObj>();
		}
	}*/
    

    
}