# 天氣 App

![Uploading WeatherApp.gif…]()

一個使用 **Kotlin** 開發的簡單天氣 App，用來顯示特定地點的即時天氣資訊。這個專案示範了如何運用 **Android Jetpack** 元件、**Coroutines** 和 **Flow** 來處理非同步操作並管理 UI 生命週期。

## 功能特色

- 使用外部天氣 API 獲取並顯示即時天氣資訊。
- 顯示特定地點的天氣資料，如溫度、濕度和風速等。
- 友善的使用者介面。

## 技術

### **程式語言 & 框架：**
- **Kotlin:** Android app 開發的核心程式語言。
- **Android Jetpack:** ViewModel、Flow

### **使用的函式庫：**
- **Retrofit** - 用於向天氣 API 發送網路請求。
- **Coroutines** - 處理背景任務，避免阻塞主執行緒。
- **Flow** - 有效地觀察和回應即時資料流。
- **Coil** - 高效率載入圖片（例如天氣圖示）。
- **Material Design** - 打造現代化的使用者介面。

## 專案設置

### 前置需求：
- **Android Studio**: 4.0 或更高版本
- **API 金鑰**: 你需要在天氣資訊提供商（如 [OpenWeatherMap](https://openweathermap.org/api)）註冊取得 API 金鑰。

### 安裝步驟：

1. 複製專案儲存庫：
   ```bash
   git clone https://github.com/YourUsername/WeatherApp.git
   ```

2. 在 local.properties 檔案中加入你的 API 金鑰：
   ```bash
   WEATHER_API_KEY = "你的_api_金鑰"
   ```


# Weather App

A simple Weather App built with **Kotlin** to display current weather information for a given location. This project demonstrates the use of **Android Jetpack** components, **Coroutines**, and **Flow** to handle asynchronous operations and manage UI lifecycle.

## Features
- Fetches and displays current weather information using an external weather API.
- Displays location-specific weather data such as temperature, humidity, and wind speed.
- User-friendly UI.

## Tech Stack

### **Languages & Frameworks:**
- **Kotlin:** Core programming language for Android app development.
- **Android Jetpack:** ViewModel, Flow
  
### **Libraries Used:**
- **Retrofit** - For making network requests to the weather API.
- **Coroutines** - To handle background tasks and avoid blocking the main thread.
- **Flow** - To observe and react to live data streams efficiently.
- **Coil** - For loading images efficiently (e.g., weather icons).
- **Material Design** - For creating a modern, user-friendly UI.
  
## Project Setup

### Prerequisites:
- **Android Studio**: 4.0 or higher
- **API Key**: You'll need to register for an API key from a weather provider such as [OpenWeatherMap](https://openweathermap.org/api).
  
### Instructions:
1. Clone the repository:
   ```bash
   git clone https://github.com/YourUsername/WeatherApp.git

2. Add your API key in the local.properties file:
   ```bash
   WEATHER_API_KEY = "your_api_key_here"
