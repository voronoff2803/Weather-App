package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.androimads.retrolin.WeatherResponse
import com.androimads.retrolin.WeatherService
import kotlinx.android.synthetic.main.activity_main.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    var isDarkTheme: Boolean = false

    var baseUrl = "https://api.openweathermap.org/"
    var apiKey = "5b02059971f9bdd9d9f2bc3918b5bf67"
    var lang = "ru"
    var lat = "59.92285455155549"
    var lon = "30.29312550684746"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        if (savedInstanceState != null) {
            isDarkTheme = savedInstanceState.getBoolean("theme")
        }

        if (isDarkTheme) {
            theme.applyStyle(R.style.AppThemeDark, true)
        } else {
            theme.applyStyle(R.style.AppTheme, true)
        }

        setContentView(R.layout.activity_main)

        getCurrentData()
    }

    fun changeTheme(view: View) {
        isDarkTheme = !isDarkTheme
        recreate()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("theme", isDarkTheme)
    }

    internal fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(lat, lon, apiKey, lang)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!

                    val stringBuilder = "Country: " +
                            weatherResponse.sys!!.country +
                            "\n" +
                            "Temperature: " +
                            weatherResponse.main!!.temp +
                            "\n" +
                            "Temperature(Min): " +
                            weatherResponse.main!!.temp_min +
                            "\n" +
                            "Temperature(Max): " +
                            weatherResponse.main!!.temp_max +
                            "\n" +
                            "Humidity: " +
                            weatherResponse.main!!.humidity +
                            "\n" +
                            "Pressure: " +
                            weatherResponse.main!!.pressure +
                            "\n" +
                            "Pressure: " +
                            weatherResponse.weather[0].id.toInt().toString()

                    textView3.text = weatherResponse.name + ", " + weatherResponse.sys!!.country
                    textView4.text = weatherResponse.weather[0].description
                    val temp = weatherResponse.main!!.temp - 273
                    textView5.text = "%.2f".format(temp) + "℃"
                    textView6.text = weatherResponse.wind!!.speed.toString() + " м/c"
                    textView7.text = weatherResponse.main!!.pressure.toString() + " hPa"
                    textView8.text = weatherResponse.main!!.humidity.toString() + " %"

                    val watherId = weatherResponse.weather[0].id.toInt()
                    val image = when (watherId) {
                        in 500..700 -> R.drawable.rainy
                        else -> R.drawable.sun
                    }

                    imageView3.setImageResource(image)
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                textView4.text = t.message
            }
        })
    }
}