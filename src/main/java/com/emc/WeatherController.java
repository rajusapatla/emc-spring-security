package com.emc;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.emc.weather.model.Weather;
import com.emc.weather.service.WeatherService;

@Controller
@RequestMapping("/")
public class WeatherController {

	@Autowired
	private WeatherService weatherService;

	@RequestMapping(value = { "/", "/hello" }, method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

	@RequestMapping(value = "/home**", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Weather Check Home Page");
		model.addObject("message",
				"This home page is protected and Authenticated");
		model.setViewName("home");

		return model;

	}

	@RequestMapping(value = "/login**", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

	@RequestMapping(value = "/weather/check**", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
	public @ResponseBody String saveWeather(HttpServletRequest request) {
		System.out.println(request.getParameter("outputData"));
		String json = request.getParameter("outputData");
		Weather weather = fromJson(json);
		boolean opSts = saveWeatherInfoToDB(weather);
		if(opSts)
			return "success";
		else
			return "fail";
	}

	public Weather fromJson(String json) {
		Weather weather = null;
		try {
			weather = new ObjectMapper().readValue(json, Weather.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return weather;
	}

	public WeatherService getWeatherService() {
		return weatherService;
	}

	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	public boolean saveWeatherInfoToDB(Weather weather) {
		boolean status = weatherService.saveWeather(weather);
		return status;
	}
}