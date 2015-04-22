package com.masood.retrofit;

import com.masood.model.*;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Test {
    private static final String API_URL = "http://localhost:8080";

    interface PublicEvents {

        @GET("/welcome")
        String printWelcome();

        @GET("/test")
        public Events test(@Query("event_id") String event_id);

        @POST("/createUserRSVPTable/{user_id}")
        public void createUserRSVPTable(@Body ArrayList<Rsvp> rsvpList,
                                 @Path("user_id") String user_id,
                                 Callback<Rsvp> callback);

        @POST("/updateUserTable")
        public void updateUserTable(@Body User user,
                                    Callback<User> callback);

        @POST("/updateUserResponse")
        public void updateUserResponse(@Body UserResponse userResponse,
                                       Callback<UserResponse> callback);

        @GET("/fetchProfile")
        public ArrayList<Events> fetchProfile(@Query("user_id") String user_id);

        @GET("/userSettingEvents")
        public ArrayList<Events> userSettingEvents(@Query("user_id") String user_id);

        @GET("/eventDescription")
        public Events eventDescription(@Query("user_id") String user_id,
                                       @Query("event_id") String event_id);

        @GET("/eventDiscussion")
        public ArrayList<Discussion> eventDiscussion(@Query("event_id") String event_id);

        @POST("/postDiscussion")
        public void postDiscussion(@Body Discussion discussion,
                                   Callback<Discussion> callback);

        @POST("/calendarEvents")
        public ArrayList<Events> calendarEvents(@Query("date") String date);


    }


    public static void main(String[] args) throws JSONException {
        // Build the Retrofit REST adaptor pointing to the URL specified

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestInterceptor.RequestFacade request) {
                request.addHeader("Content-Type", "application/json");
                request.addHeader("Accept", "application/json");
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        PublicEvents publicEvents = restAdapter.create(PublicEvents.class);
/*

        ArrayList<Rsvp> rsvpList = new ArrayList<Rsvp>();
        for(int i=0; i <= 1; i++){
            Rsvp rsvp = new Rsvp("123" + i, "yes");
            rsvpList.add(rsvp);
        }
        String user_id = "abcd";

        publicEvents.createUserRSVPTable(rsvpList, user_id, new Callback<Rsvp>(){
            public void failure(RetrofitError arg0) {
                // TODO Auto-generated method stub
            }
            public void success(Rsvp arg0, Response arg1) {
                // TODO Auto-generated method stub
                System.out.println("successretorift");

            }

        });

        System.out.println("get test: " );
        Events event = publicEvents.test("994611637230193");
        System.out.println(event.getDescription());

        System.out.println("get fetch profile: ");
        ArrayList<Events> profileEventsList = publicEvents.fetchProfile("user_1");
        System.out.println("event id: " + profileEventsList.get(0).getEvent_id());

        System.out.println("************POST - updateUserTable*************");
        User user = new User("abcd", "", "31 00:00:00.0", 1, 1, 1, 0, 0, 0, 1);
        publicEvents.updateUserTable(user, new Callback<User>(){
            public void failure(RetrofitError arg0) {
                // TODO Auto-generated method stub
            }
            public void success(User arg0, Response arg1) {
                // TODO Auto-generated method stub
                System.out.println("*************User callback success**************");

            }
        });

        */
        UserResponse userResponse = new UserResponse("10205182668978894", "994611637230193", "unsure");
        publicEvents.updateUserResponse(userResponse, new Callback<UserResponse>(){
            public void failure(RetrofitError arg0) {
                // TODO Auto-generated method stub
            }
            public void success(UserResponse arg0, Response arg1) {
                // TODO Auto-generated method stub
                System.out.println("*************User Response callback success**************");

            }
        });

        /*

        System.out.println("***************** get user Setting Events:*************** ");
        ArrayList<Events> settingsEventsList = publicEvents.userSettingEvents("user_1");
        System.out.println(settingsEventsList);

        Events eventDes =  publicEvents.eventDescription("user_1", "994611637230193");
        System.out.println(eventDes.getDescription());
        System.out.println(eventDes.getResponse());

        System.out.println("*************eventDiscussion************");
        ArrayList<Discussion> discussionList = publicEvents.eventDiscussion("event_2");
        System.out.println("discussion: " + discussionList);

        Discussion discussion = new Discussion("event_2", "masood", "posting event", "");

        publicEvents.postDiscussion(discussion, new Callback<Discussion> (){
            public void failure(RetrofitError arg0) {
                // TODO Auto-generated method stub
            }
            public void success(Discussion arg0, Response arg1) {
                // TODO Auto-generated method stub
                System.out.println("*************User callback success**************");

            }
        });

        String dateCalendar = "2015-04-21";
        ArrayList<Events> calendarEventsList = publicEvents.calendarEvents(dateCalendar);
        System.out.println(calendarEventsList);
*/
    }//main

}