package com.emc.weather.dao;

import com.emc.weather.model.Weather;

public interface WeatherDao {

	void save(Weather weather);

	void update(Weather weather);

	void delete(Weather weather);

	Weather findByWeatherId(String weatherId);

}
