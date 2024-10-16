import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.model.Main
import com.example.weatherapp.data.model.WeatherInfo
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.model.Wind
import com.example.weatherapp.network.WeatherRepository
import com.example.weatherapp.network.WeatherState
import com.example.weatherapp.ui.WeatherUiState
import com.example.weatherapp.ui.WeatherViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertTrue
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: WeatherViewModel

    @Test
    fun `initial state is Loading`() = runTest {
        assertEquals(WeatherState.Loading, viewModel.weatherState.first())
    }

    @Test
    fun `test fetchWeatherDataByCity success`() = runTest {
        // Mock WeatherRepository
        val repository = mockk<WeatherRepository>()

        val mockResponse = WeatherResponse(
            weatherInfo = listOf(
                WeatherInfo(main = "Clear", icon = "01d", description = "", id = 0)
            ),
            main = Main(
                temp = 25.0,
                temp_min = 20.0,
                temp_max = 30.0,
                pressure = 1012,
                humidity = 60
            ),
            wind = Wind(speed = 5.0),
            name = "Taipei"
        )

        // 模擬 repository 的行為
        coEvery { repository.fetchWeatherDataByCity(any()) } returns Response.success(mockResponse)

        // 注入 mock 的 repository 到 ViewModel
        val viewModel = WeatherViewModel()

        // 執行 fetchWeatherDataByCity
        viewModel.fetchWeatherDataByCity("Taipei")

        // 驗證結果
        assertTrue(viewModel.weatherState.value is WeatherState.Success)
    }


}