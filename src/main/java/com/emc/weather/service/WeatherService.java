package com.emc.weather.service;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import com.emc.weather.dao.WeatherDao;
import com.emc.weather.model.Weather;

public class WeatherService {

	private WeatherDao weatherDao;

	public boolean saveWeather(Weather weather) {

		try {
			weatherDao.save(weather);
		} catch (DataIntegrityViolationException e) {
			System.out.println("history already exist");
			return false;
		} catch (ConstraintViolationException e) {
			System.out.println("history already exist dao");
		} 
		return true;
	}

	public WeatherDao getWeatherDao() {
		return weatherDao;
	}

	public void setWeatherDao(WeatherDao weatherDao) {
		this.weatherDao = weatherDao;
	}

}