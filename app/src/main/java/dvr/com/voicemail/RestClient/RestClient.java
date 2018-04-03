package dvr.com.voicemail.RestClient;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import dvr.com.voicemail.Response.LoginResponse.ResponseLogin;
import dvr.com.voicemail.Response.SignUpResponse.ResponseSignUp;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class RestClient {
    private static GitApiInterface gitApiInterface;
    private static String baseUrl="http://smartit.ventures/";

    public static GitApiInterface getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//The logging interceptor will be added to the http client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
//The Retrofit builder will have the client attached, in order to get connection logs
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .build();

        gitApiInterface = retrofit.create(GitApiInterface.class);
       return gitApiInterface;
    }

    public interface GitApiInterface {
        @POST("voicemail/voicemailAPI/v1/userRegister")
        Call<ResponseSignUp> SignUp(@Body HashMap<String, String> hashMap);

        @POST("voicemail/voicemailAPI/v1/userLogin")
        Call<ResponseLogin> LoginInterface(@Body HashMap<String, String> hashMap);
    }
}
