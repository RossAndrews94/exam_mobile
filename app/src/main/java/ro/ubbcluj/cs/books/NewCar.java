package ro.ubbcluj.cs.books;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ro.ubbcluj.cs.books.books.R;
import ro.ubbcluj.cs.books.domain.Car;

public class NewCar extends AppCompatActivity {

    @BindView(R.id.car_name)
    EditText carName;
    @BindView(R.id.car_type)
    EditText carType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_car);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.save)
    public void save(View view) {
        Manager manager = new Manager();
//    manager.save(new Book(0, bookTitle.getText().toString(),new Date()));
//        int group;
        try {
//            group = Integer.parseInt(studentGroup.getText().toString());
            Car car = new Car(0, carName.getText().toString(), carType.getText().toString(), "status");
            manager.save(car);
        } catch (NumberFormatException e) {
//            System.out.println("Wrong number");
            Toast.makeText(this, "Type has to be a string!", Toast.LENGTH_SHORT).show();
        }

        finish();
    }


}
