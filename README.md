# 🌦️ Weather Forecast App — Spring Boot + Thymeleaf

Hi! I’m Kushagr Singh, and this is my second Java project — a beginner-friendly weather forecast app built using **Spring Boot**, **Thymeleaf**, **HTML**, and **CSS**.

The app fetches real-time weather data for any city around the world and presents it with a clean, responsive UI that updates dynamically based on weather conditions. From sunrise to sunset, temperature to wind speed — it's all displayed beautifully and accurately.

---

## ✨ Features

- 🔀 **Random City on Startup** — See a random city's weather when the app loads
- 🔍 **Search by City Name** — Enter any city to fetch its current weather
- 🕒 **City-Based Time & Date** — Adjusts based on the searched city’s timezone
- 🌤️ **Detailed Weather Info** — Temperature, feels like, humidity, wind, UV index, visibility, and more
- 🖼️ **Dynamic Backgrounds** — UI changes based on current weather
- 📅 **5-Day Forecast** — Static forecast layout (future API upgrade planned)
- 💡 **Error Handling** — Invalid city names trigger friendly error messages
- 📱 **Fully Responsive** — Looks great on desktop, tablet, and mobile

---

## 📸 Screenshots
<img width="1348" height="633" alt="Screenshot 2025-08-06 021609" src="https://github.com/user-attachments/assets/ba76fb6a-a9f0-4b4d-945b-71dc6b7890dc" />
<img width="1357" height="630" alt="Screenshot 2025-08-06 021643" src="https://github.com/user-attachments/assets/ecdca02e-1dbb-4a36-b43c-a7ca539f5850" />
<img width="1350" height="630" alt="Screenshot 2025-08-06 021657" src="https://github.com/user-attachments/assets/24eddffc-8a3c-4ee7-b908-545d16c2f96d" />

https://github.com/user-attachments/assets/1a74895a-f143-4cbc-8fb4-12683e0f320e

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot**
- **Thymeleaf**
- **HTML & CSS**
- **OpenWeatherMap API**

---

## 🚀 Getting Started

### Requirements

- Java 17+
- Maven
- OpenWeatherMap API Key ([Get one here](https://openweathermap.org/api))

### Setup Instructions

```
# Clone the repository
git clone https://github.com/yourusername/weather-app-springboot.git
cd weather-app-springboot

# Add your API key in application.properties
weather.api.key=YOUR_API_KEY

# Build and run the app
mvn clean install
mvn spring-boot:run
Then open http://localhost:8080 in your browser.
```

## 🧠 What I Learned
- How to integrate REST APIs using Spring Boot
- Server-side rendering with Thymeleaf
- Structuring a full-stack Java web application
- Making responsive, user-friendly layouts with HTML & CSS
- Handling errors and edge cases cleanly

---

## 📁 Project Structure
```
spring-weather-app/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/weatherapp/
│       │       ├── SpringWeatherAppApplication.java
│       │       ├── controller/
│       │       │   └── WeatherController.java
│       │       ├── service/
│       │       │   └── WeatherService.java
│       │       └── model/
│       │           ├── WeatherData.java
│       │           └── WeatherResponse.java
│       └── resources/
│           ├── templates/
│           │   └── weather.html
│           ├── static/
│           │   └── css/
│           │       └── style.css
│           └── application.properties
├── pom.xml
└── README.md
```

---

## 🚧 Future Improvements
- Replace static forecast with dynamic API-based 5-day forecast
- Add support for saving favorite cities
- Improve UI/UX for dark mode and animations

---

Thanks for checking out my project! 😊
Feel free to fork it, suggest improvements, or use it as a base for your own learning.
