package com.masood.controller;

import com.masood.model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class EventsController {

	//test 1
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public @ResponseBody String printWelcome(/*ModelMap model*/) {
		//model.addAttribute("message", "Hello world!");

		return "hello its changed";
	}


	@RequestMapping(value ="/test", method = RequestMethod.GET)
	@ResponseBody
	public Events test(@RequestParam("event_id") String event_id){
		JdbcEventsDAO jdbcEventsDAO = new JdbcEventsDAO();
		Events event = jdbcEventsDAO.findByEventId(event_id);
		return event;
	}

	@RequestMapping(value ="/createUserRSVPTable/{user_id}", method = RequestMethod.POST)
	public void createUserRSVPTable(@RequestBody ArrayList<Rsvp> rsvpList,
									@PathVariable("user_id") String user_id){

		JdbcUserResponseDAO jdbcUserResponseDAO = new JdbcUserResponseDAO();
		int i = 0;
		Rsvp rsvp = null;
		while(i < rsvpList.size()){
			try {
				rsvp = rsvpList.get(i);
				UserResponse userResponse = new UserResponse(user_id, rsvp.getEvent_id(), rsvp.getResponse());
				jdbcUserResponseDAO.insert(userResponse);
				jdbcUserResponseDAO.updateResponse(userResponse);
			}catch (Exception ex){

			}
			i++;
		}

		try {
			User user = new User(user_id, "", "31 00:00:00.0", 0, 0, 0, 0, 0, 0, 1);
			JdbcUserDAO jdbcUserDAO = new JdbcUserDAO();
			jdbcUserDAO.insert(user);

		}catch (Exception ex){
			ex.printStackTrace();
		}

	}

	@RequestMapping(value ="/updateUserTable", method = RequestMethod.POST)
	@ResponseBody
	public void updateUserTable(@RequestBody User user){
		JdbcUserDAO jdbcUserDAO = new JdbcUserDAO();
		jdbcUserDAO.updateSettings(user);
	}

	@RequestMapping(value="/updateUserResponse", method = RequestMethod.POST)
	@ResponseBody
	public void updateUserResponse(@RequestBody UserResponse userResponse){
		JdbcUserResponseDAO jdbcUserResponseDAO = new JdbcUserResponseDAO();
		jdbcUserResponseDAO.updateResponse(userResponse);
	}



	//2.
	//fetching from events, mapping user_response -> events
	@RequestMapping(value ="/fetchProfile", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Events> fetchProfile(@RequestParam("user_id") String user_id){
		JdbcUserResponseDAO jdbcUserResponseDAO = new JdbcUserResponseDAO();
		ArrayList<Events> eventsList = jdbcUserResponseDAO.findEventsAttendingByUserId(user_id);
		return eventsList;
	}


	//3.
	@RequestMapping(value = "/userSettingEvents", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Events> userSettingEvents(@RequestParam("user_id") String user_id){
		JdbcUserDAO jdbcUsersDAO = new JdbcUserDAO();
		ArrayList<Events> eventsList = jdbcUsersDAO.findEventsSettingsbyUserId(user_id);
		return eventsList;


	}

	//4. get event description
	@RequestMapping(value = "/eventDescription", method = RequestMethod.GET)
	@ResponseBody
	public Events eventDescription(@RequestParam("user_id") String user_id,
								   @RequestParam("event_id") String event_id){
		JdbcUserResponseDAO jdbcUserResponseDAO = new JdbcUserResponseDAO();
		Events event = jdbcUserResponseDAO.findEventDescriptionByUserAndEventId(user_id, event_id);
		return event;
	}

	//5. get discussion
	@RequestMapping(value = "/eventDiscussion", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Discussion> eventDiscussion(@RequestParam("event_id") String event_id){
		JdbcDiscussionDAO jdbcDiscussionDAO = new JdbcDiscussionDAO();
		ArrayList<Discussion> discussionList;
		discussionList =  jdbcDiscussionDAO.findDiscussionByEventId(event_id);
		return discussionList;
	}

	//6. post discussion
	@RequestMapping(value = "/postDiscussion", method = RequestMethod.POST)
	@ResponseBody
	public void postDiscussion(@RequestBody Discussion discussion){
		JdbcDiscussionDAO jdbcDiscussionDAO = new JdbcDiscussionDAO();
		jdbcDiscussionDAO.insert(discussion);
	}


	//7. calendar event
	@RequestMapping(value = "/calendarEvents", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<Events> calendarEvents(@RequestParam("date") String date){
		JdbcEventsDAO jdbcEventsDAO = new JdbcEventsDAO();
		ArrayList<Events> eventsList = jdbcEventsDAO.findEventsByDate(date);
		return eventsList;
	}


}