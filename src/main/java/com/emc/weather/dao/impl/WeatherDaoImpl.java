package com.emc.weather.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import com.emc.weather.dao.WeatherDao;
import com.emc.weather.model.Weather;


public class WeatherDaoImpl implements WeatherDao {
	private SessionFactory sessionFactory;

	@Override
	public void save(Weather weather) {
		getSessionFactory().getCurrentSession().save(weather);
	}

	@Override
	public void update(Weather weather) {
		getSessionFactory().getCurrentSession().update(weather);
	}

	@Override
	public void delete(Weather weather) {
		getSessionFactory().getCurrentSession().delete(weather);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Weather findByWeatherId(String weatherId) {
		List<Weather> weathers = new ArrayList<Weather>();
		weathers = getSessionFactory().getCurrentSession()
				.createQuery("from Weather where weatherId=?")
				.setParameter(0, weatherId).list();
		if (weathers.size() > 0) {
			return weathers.get(0);
		} else {
			return null;
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
