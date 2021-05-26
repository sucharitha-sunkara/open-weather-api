package com.openweather.api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WeatherForecast {

    public static void main(String[] args) throws Exception {

        /* Get Weather Forecast for next 6 days for Sydney City and temperature in celsius*/
        URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=Sydney&APPID=7e52a1d6f68ade2f5bd8343ff7e49013&units=metric");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();

        /* Format API Response into String*/

        String response= new String();
        Scanner sc = new Scanner(url.openStream());
        while(sc.hasNext()) {
            response+=sc.nextLine();
        }
        sc.close();

        /* Execute when API returns 200 */
        if(responseCode == 200) {
            JSONObject jObject = new JSONObject(response);
            JSONArray list = jObject.getJSONArray("list");
            List<Weather> forecastData = new ArrayList<Weather>();

            for ( int i=0; i< list.length(); i++){
                String datetime = list.getJSONObject(i).getString("dt_txt");
                Weather weather = new Weather();
                weather.date = datetime.substring(0,10);;
                weather.timestamp = datetime.substring(12);
                weather.temperature= list.getJSONObject(i).getJSONObject("main").getDouble("temp");
                weather.weatherCondition = list.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("description");
                forecastData.add(weather);
            }
            System.out.println ("=====================Weather Forecast for next 6 days=========================");
            System.out.println ("\nNumber of Days with 20 degrees or more temperature: " + getNumberOfDaysWithTemperature(forecastData,20.0));
            System.out.println ("==============================================================================");
            System.out.println ("\nNumber of Days with Sunny Weather: " + getNumberOfDaysWithWeather(forecastData,"Clear Sky"));
            System.out.println ("==============================================================================");

        }
        else {
            throw new RuntimeException("HttpResponseCode: " +responseCode);
        }
    }

    private static int getNumberOfDaysWithWeather(List<Weather> forecast,String weatherCondition) {
        int noOfDays =0;
        String currentDate = null;

        for (int i=0; i< forecast.size();i++) {
            Weather weather = forecast.get(i);
            String wCondition = weather.weatherCondition;
            if (currentDate == null || !currentDate.equals(weather.date)) {
                if (wCondition.equalsIgnoreCase(weatherCondition)) {
                    currentDate = weather.date;
                    noOfDays++;
                    System.out.println("Date:" + weather.date + " Timestamp:" + weather.timestamp + " Weather:"+ weather.weatherCondition + " Temperature:" + weather.temperature);
                }
            }
        }
        return  noOfDays;
    }

    private static int  getNumberOfDaysWithTemperature(List<Weather> forecast,Double temp) {
        int noOfDays =0;
        String currentDate = null;

        for (int i=0; i< forecast.size();i++) {
            Weather weather = forecast.get(i);
            Double currTemp = weather.temperature;
            if (currentDate == null || !currentDate.equals(weather.date)) {
                if (currTemp >= temp) {
                    currentDate = weather.date;
                    noOfDays++;
                    System.out.println("Date:" + weather.date + " Timestamp:" + weather.timestamp + " Weather:"+ weather.weatherCondition + " Temperature:" + weather.temperature);
                }
            }
        }
        return  noOfDays;

    }
}
