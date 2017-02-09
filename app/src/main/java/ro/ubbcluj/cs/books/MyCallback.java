package ro.ubbcluj.cs.books;

import ro.ubbcluj.cs.books.domain.Car;

public interface MyCallback {
    void add(Car car);

    void showError(String message);

    void clear();

}
