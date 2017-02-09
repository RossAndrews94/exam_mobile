package ro.ubbcluj.cs.books;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmResults;
import ro.ubbcluj.cs.books.domain.Car;
import ro.ubbcluj.cs.books.service.CarService;
import ro.ubbcluj.cs.books.service.ServiceFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class Manager {


    private CarService service;
    private Realm realm = Realm.getDefaultInstance();

    public Manager() {
        service = ServiceFactory.createRetrofitService(CarService.class, CarService.SERVICE_ENDPOINT);
    }

    public boolean networkConnectivity(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    public void loadEvents(final ProgressBar progressBar, final MyCallback callback) {

        service.getCars()
                .timeout(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Car>>() {
                    @Override
                    public void onCompleted() {
                        Timber.v("Car Service completed");
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "Error while loading the events");
                        callback.clear();
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                RealmResults<Car> result = realm.where(Car.class).findAll();
                                List<Car> cars = realm.copyFromRealm(result);
                                for (Car car : cars) {
                                    callback.add(car);
                                }
                            }
                        });
                        callback.showError("Not able to retrieve the data. Displaying local data!");
                    }

                    @Override
                    public void onNext(final List<Car> cars) {
                        callback.clear();
                        for (Car car : cars)
                            callback.add(car);
                        realm.executeTransactionAsync(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                realm.copyToRealmOrUpdate(cars);
                                Timber.v("Cars persisted");
                            }
                        });
                    }
                });
        ;
    }


    public void save(Car car) {
        service.registerCar(car)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Car>() {
                    @Override
                    public void onCompleted() {
                        Timber.v("Car Service completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "Error while persisting a car");
                    }

                    @Override
                    public void onNext(Car car) {
                        Timber.v("Car persisted");
                    }
                });
    }
    public void rentCar(Car car) {
        service.rentCar(car)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Car>() {
                    @Override
                    public void onCompleted() {
                        Timber.v("Car Service completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "Error while persisting a car");
                    }

                    @Override
                    public void onNext(Car car) {
                        Timber.v("Car persisted");
                    }
                });
    }

    public void releaseCar(Car car) {
        service.releaseCar(car)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Car>() {
                    @Override
                    public void onCompleted() {
                        Timber.v("Car Service completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "Error while persisting a car");
                    }

                    @Override
                    public void onNext(Car car) {
                        Timber.v("Car persisted");
                    }
                });
    }
}
