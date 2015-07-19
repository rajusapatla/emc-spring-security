EMC Spring Security
===================

## Technology Stack
1). java
2). spring core
3). jsp/servlet
4). SpringMVC
5). MySQL
6). Hibernate
7). jQuery
8). javascript
9). Spring Security
10). junit/MockMvc

## Implemented Tasks

1). Created a login page with a custom login form using _Spring Security Basic Authentication_.  The login url will be __/login__ and access to any other page should be denied until successfully authenticated.
2). Created a home page with a form select on it.  The dropdown/select should consist of the following option id's and values.

```
<option value="4508722">Cincinnati</option>
<option value="5391959">San Francisco</option>
<option value="4887398">Chicago</option>
<option value="4930956">Boston</option>
<option value="4164138">Miami</option>
<option value="5809844">Seattle</option>
```


When a user selects one of the options in the dropdown menu, an ajax POST request should be made to the url __/weather/check__.  The value of the option selected in the dropdown should be sent as a parameter in the POST request.

3). Created a controller method that accepts the ajax POST request to __/weather/check__.
4). Using the parameter value sent along with the POST request, consume the JSON rest service at this url _http://api.openweathermap.org/data/2.5/weather?id=XXXXXX_ replacing the query string __XXXXXX__ with the value provided in the POST request.

```
Example - http://api.openweathermap.org/data/2.5/weather?id=5809844
```

5). Save the entire _JSON reponse_ as well as the _id_ and the _user's name_ that requested it to a database table.
6). Return a response to the original ajax request that includes:

```
the temperature in Fahrenheit (the temperature returned from the rest service will be in Kelvin)
the main description of the weather
the name of the city
the icon value (example - 10d)
```

7). On the frontend, after a response is returned, display the temperature, name of city, description of the weather and an image/icon that represents the current status of the weather.  See below for an example of how to use the icon value to retrieve an image.

```
Weather Icon = http://openweathermap.org/img/w/{icon value}
Example = http://openweathermap.org/img/w/10d.png
```

8). Added a logout link that upon logout takes the user back to the login screen.
