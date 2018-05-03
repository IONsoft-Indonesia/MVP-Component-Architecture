package co.id.ionsoft.data.di;

import javax.inject.Named;
import javax.inject.Singleton;

import co.id.ionsoft.data.api.GithubService;
import co.id.ionsoft.utils.Constant;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */
@Module
public class ApiServiceModule {

    private static final String BASE_URL = "baseUrl";

    @Provides
    @Named(BASE_URL)
    String provideBaseUrl() {
        return Constant.BASE_URL;
    }

    @Provides
    @Singleton
    Interceptor provideInterceptor() {
        return chain -> {
            Request request = chain.request();
            request = request.newBuilder()
                    .addHeader("Accept", "application/json").build();
            return chain.proceed(request);
        };
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideLogginInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Interceptor interceptor,
                                     HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }


    @Provides
    @Singleton
    CallAdapter.Factory provideRxJavaAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@Named(BASE_URL) String baseUrl,
                             Converter.Factory converterFactory,
                             CallAdapter.Factory callAdapterFactory,
                             OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    GithubService provideGithubServide(Retrofit retrofit) {
        return retrofit.create(GithubService.class);
    }

}
