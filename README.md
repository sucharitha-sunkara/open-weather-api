# open-weather-api
Collects Weather forecast from open weather API

WeatherForecast.java has the main method. Please run it to get the desired Output
To run it through Command Line:
%MAVEN_HOME%\mvn clean compile test

This class uses following open weather API

http://api.openweathermap.org/data/2.5/forecast?q=Sydney&APPID=7e52a1d6f68ade2f5bd8343ff7e49013&units=metric

It returns Forecast for next 6 days ( Time period for Free subscription) for Sydney City with temperature in Celsius.

Assumptions:

1.Counted the day, if temperature reported '20.0' degrees or more atleast once on that day

2.Counted the day, if weather reported 'Clear Sky'(it means Sunny as per weathercodes) atleast once on that day

Limitations:

1.Hardcoded the parameters(Location,APPID & units) for the API, we can also use String[] args in the main() method to pass in desired parameters


External Libraries:

Org.json -- added to POM.xml

apache-maven-3.6.3 -- Create a Envrionment Variable MAVEN_HOME = C:\apache-maven-3.6.3\bin ( apache-maven-3.6.3 directory added to repository in case it is needed)

Jdk -1.8.0_271 -- create a Envrionment Variable JAVA_HOME=C:\Program Files\Java\jdk1.8.0_271

