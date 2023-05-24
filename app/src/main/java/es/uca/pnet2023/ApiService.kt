package es.uca.pnet2023
import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent
import io.ktor.http.isSuccess
import kotlinx.serialization.Serializable

class ApiService {
    private val client = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }
    suspend fun getData(): List<MyData> = withContext(Dispatchers.IO) {
        val url = "http://10.0.2.2:8080/reservas"
        Log.d("API_CALL", "Appel API réussi")
        val json = client.get<String>(url)
        val gson = Gson()
        val result = gson.fromJson(json, Array<MyData>::class.java).toList()
        return@withContext result
    }

    suspend fun postData(data: MyData) = withContext(Dispatchers.IO) {
        val url = "http://10.0.2.2:8080/reservas"
        val json = Gson().toJson(data)

        val response = client.post<HttpResponse>(url) {
            body = TextContent(json, ContentType.Application.Json)
        }

        if (response.status.isSuccess()) {
            Log.d("API_CALL", "Envoi de données réussi")
        } else {
            Log.d("API_CALL", "Erreur lors de l'envoi de données: ${response.status}")
        }
    }
}
@Serializable
data class MyData(val _id: String, val centro: String, val pista: String, val personas: Int, val date: String, val duration: String, val hour: String)