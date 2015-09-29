package com.weathershow.app.util;

import android.text.TextUtils;

import com.weathershow.app.db.WeatherShowDB;
import com.weathershow.app.model.City;
import com.weathershow.app.model.Country;
import com.weathershow.app.model.Province;

public class Utility {
	/**
	 * 解析和处理服务器返回的省级数据
	 * @param weatherShowDB
	 * @param response
	 * @return
	 */
	public synchronized static boolean handleProvincesResponse(WeatherShowDB weatherShowDB,String response){
		if(!TextUtils.isEmpty(response)){
			String[] allProvinces=response.split(",");
			if(allProvinces!=null&&allProvinces.length>0){
				for(String p:allProvinces){
					String[] array=p.split("\\|");
					Province province=new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					weatherShowDB.saveProvince(province);
					
				}
				return true;
			}
		}
		return false;
		
	}
	/**
	 * 解析和处理服务器返回的市级数据
	 * @param weatherShowDB
	 * @param response
	 * @param provinceId
	 * @return
	 */
	public synchronized static boolean handleCitiesResponse(WeatherShowDB weatherShowDB,String response,int provinceId){
		if(!TextUtils.isEmpty(response)){
			String[] allCities=response.split(",");
			if(allCities!=null&&allCities.length>0){
				for(String c:allCities){
					String[] array=c.split("\\|");
					City city=new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					weatherShowDB.saveCity(city);
					
				}
				return true;
			}
		}
		return false;
		
	}
	/**
	 * 解析和处理服务器返回的县级数据
	 * @param weatherShowDB
	 * @param response
	 * @param cityId
	 * @return
	 */
	public synchronized static boolean handleCountriesResponse(WeatherShowDB weatherShowDB,String response,int cityId){
		if(!TextUtils.isEmpty(response)){
			String[] allCountries=response.split(",");
			if(allCountries!=null&&allCountries.length>0){
				for(String c:allCountries){
					String[] array=c.split("\\|");
					Country country=new Country();
					country.setCountryCode(array[0]);
					country.setCountryName(array[1]);
					country.setCityId(cityId);
					weatherShowDB.saveCountry(country);
					
				}
				return true;
			}
		}
		return false;
		
	}

}
