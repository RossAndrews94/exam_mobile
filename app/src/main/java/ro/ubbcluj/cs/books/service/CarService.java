package ro.ubbcluj.cs.books.service;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import ro.ubbcluj.cs.books.domain.Car;
import rx.Observable;

public interface CarService {
  String SERVICE_ENDPOINT = "http://192.168.1.104:4000";


  @GET("cars")
  Observable<List<Car>> getCars();

  @POST("register")
  Observable<Car> registerCar(@Body Car e);

  @POST("rent")
  Observable<Car> rentCar(@Body Car e);

  @POST("release")
  Observable<Car> releaseCar(@Body Car e);
}
