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
import io.ktor.client.request.delete
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent
import io.ktor.http.isSuccess
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class ApiService {
    private val client = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }
    suspend fun getData(): List<Reservas> = withContext(Dispatchers.IO) {
        val url = "http://10.0.2.2:8080/reservas"
        Log.d("API_CALL", "API call success")
        val json = client.get<String>(url)
        val gson = Gson()
        val result = gson.fromJson(json, Array<Reservas>::class.java).toList()
        return@withContext result
    }

    suspend fun deleteData(id: String) = withContext(Dispatchers.IO) {
        val url = "http://10.0.2.2:8080/reservas/$id"
        val response = client.delete<HttpResponse>(url)

        if (response.status.isSuccess()) {
            Log.d("API_CALL", "Delete success")
        } else {
            Log.d("API_CALL", "Erreur while deleting: ${response.status}")
        }
    }

    suspend fun putData(_id: String, data: Reservas) = withContext(Dispatchers.IO) {
        val url = "http://10.0.2.2:8080/reservas/$_id"
        val client = OkHttpClient()

// Création du corps de la requête
        val json = Gson().toJson(data)
        val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), json)

// Création de la requête
        val request = Request.Builder()
            .url(url)
            .put(requestBody)
            .build()

// Envoi de la requête
        val response = client.newCall(request).execute()
    }

    suspend fun postData(data: Reservas) = withContext(Dispatchers.IO) {
        val url = "http://10.0.2.2:8080/reservas"
        val json = Gson().toJson(data)

        val response = client.post<HttpResponse>(url) {
            body = TextContent(json, ContentType.Application.Json)
        }

        if (response.status.isSuccess()) {
            Log.d("API_CALL", "Success post")
        } else {
            Log.d("API_CALL", "Error post: ${response.status}")
        }
    }
}