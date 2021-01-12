
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;
import org.apache.tools.ant.util.DateUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Calender {
    private static final String APPLICATION_NAME = "Quickstart1";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";



    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    //private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);
    private static final List<String> SCOPES = Arrays.asList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws Exception {
        // Load client secrets.
        InputStream in = Calender.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
    public static Calendar getcalendar() throws Exception {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar calendarService = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        return  calendarService;
    }
public static  void createCalendarEvent(Calendar calendarService) throws IOException, ParseException {
    Event event = new Event()
            .setSummary("section weekly ")
            .setDescription("Grammer");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss", Locale.ENGLISH);
    String dateStart = "2021 01 12 22:00:00";
    java.util.Date start =sdf.parse(dateStart);
    DateTime startDateTime = new DateTime(start);
    EventDateTime eventDateTimestart = new EventDateTime().setDateTime(startDateTime).setTimeZone("Asia/Kolkata");
    event.setStart(eventDateTimestart);
    String dateEnd = "2021 01 12 23:00:00";
    java.util.Date end =sdf.parse(dateEnd);
    DateTime endDateTime = new DateTime(end);
    EventDateTime eventDateTimeEnd = new EventDateTime().setDateTime(endDateTime).setTimeZone("Asia/Kolkata");
    event.setEnd(eventDateTimeEnd);
    event.setRecurrence(Arrays.asList(
            "       RRULE:FREQ=WEEKLY;COUNT=5"));

    EventAttendee[] attendees = new EventAttendee[] {
            new EventAttendee().setEmail("jagadameusha@gmail.com"),
    };
    event.setAttendees(Arrays.asList(attendees));

    EventReminder[] reminderOverrides = new EventReminder[] {
            new EventReminder().setMethod("email").setMinutes(24 * 60),
            new EventReminder().setMethod("popup").setMinutes(10),
    };
    Event.Reminders reminders = new Event.Reminders()
            .setUseDefault(false)
            .setOverrides(Arrays.asList(reminderOverrides));
    event.setReminders(reminders);

    String calendarId = "primary";
    event = calendarService.events().insert(calendarId, event).execute();
    System.out.printf("Event created: %s\n", event.getHtmlLink());
}
public static void getTop10UpcomingEvents(Calendar calendarService) throws IOException {
    DateTime now = new DateTime(System.currentTimeMillis());
    Events events = calendarService.events().list("primary")
            .setSingleEvents(true)
            .setTimeMin(now)
            .setOrderBy("startTime")
             .setMaxResults(10)
            .execute();
    List<Event> eventList = events.getItems();
    if (eventList.isEmpty()) {
        System.out.println("No upcoming events found.");
    } else {
        System.out.println("Upcoming events");
        for(Event event:eventList)
        {
            Date startDateTime = new Date(event.getStart().getDateTime().getValue());
            Date endDateTime = new Date(event.getEnd().getDateTime().getValue());
          System.out.println(event.getSummary()+"---"+startDateTime+"-----"+endDateTime);
        }
    }
}
public static void UpdateEvent(Calendar calendarService)
{

}

    public static void main(String... args) throws Exception {
        Calendar calendarService=getcalendar();
        createCalendarEvent(calendarService);
        getTop10UpcomingEvents(calendarService);
        /*java.util.Date start = java.util.Date.from(java.time.Instant.parse("2021-01-05T12:00:00.000Z"));
        System.out.println(start);
        */
        // Build a new authorized API client service.
        /*final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();*/
        /*Event event = new Event()
                .setSummary("section 1 ")
                .setDescription("Grammer");
       java.util.Date start=new java.util.Date(2021,01,05,10,00,00);
        DateTime startDateTime = new DateTime(start);
        EventDateTime eventDateTimestart = new EventDateTime().setDateTime(startDateTime).setTimeZone("GMT+5:30)India Standard Time_Kolkata");
        event.setStart(eventDateTimestart);
        java.util.Date end=new java.util.Date(2021,01,05,11,20,00);
        DateTime endDateTime = new DateTime(end);
        EventDateTime eventDateTimeEnd = new EventDateTime().setDateTime(endDateTime).setTimeZone("GMT+5:30)India Standard Time_Kolkata");
        event.setEnd(eventDateTimeEnd);
        String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
        event.setRecurrence(Arrays.asList(recurrence));

        EventAttendee[] attendees = new EventAttendee[] {
                new EventAttendee().setEmail("jagadameusha@gmail.com"),
        };
        event.setAttendees(Arrays.asList(attendees));

        EventReminder[] reminderOverrides = new EventReminder[] {
                new EventReminder().setMethod("email").setMinutes(24 * 60),
                new EventReminder().setMethod("popup").setMinutes(10),
        };
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);

        String calendarId = "primary";
        event = service.events().insert(calendarId, event).execute();
        System.out.printf("Event created: %s\n", event.getHtmlLink());
*/
        // List the next 10 events from the primary calendar.
        /*DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event event1 : items) {
                DateTime start1 = event1.getStart().getDateTime();
                if (start1 == null) {
                    start1 = event1.getStart().getDate();
                }
                System.out.printf("%s (%s)\n", event1.getSummary(), start1);
            }
        }*/






    }
}
