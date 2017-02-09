package ro.ubbcluj.cs.books;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.Toast;

import ro.ubbcluj.cs.books.adapter.MyAdapter;
import ro.ubbcluj.cs.books.books.R;
import ro.ubbcluj.cs.books.domain.Car;


/**
 * An activity representing a single Book detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link BookListActivity}.
 */
public class EventDetailActivity extends AppCompatActivity {

    private MyAdapter adapter;

//    @BindView(R.id.progress)
//    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
//        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();

                rentCar();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(EventDetailFragment.ARG_CAR_ID,
                getIntent().getStringExtra(EventDetailFragment.ARG_CAR_ID));
            arguments.putString(EventDetailFragment.ARG_CAR_NAME,
                    getIntent().getStringExtra(EventDetailFragment.ARG_CAR_NAME));
            arguments.putString(EventDetailFragment.ARG_CAR_TYPE,
                    getIntent().getStringExtra(EventDetailFragment.ARG_CAR_TYPE));
            arguments.putString(EventDetailFragment.ARG_CAR_STATUS,
                    getIntent().getStringExtra(EventDetailFragment.ARG_CAR_STATUS));
            EventDetailFragment fragment = new EventDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                .add(R.id.event_detail_container, fragment)
                .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, BookListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void rentCar() {
        int id = Integer.parseInt(getIntent().getStringExtra(EventDetailFragment.ARG_CAR_ID));
        String status = getIntent().getStringExtra(EventDetailFragment.ARG_CAR_STATUS);

//        Toast.makeText(this, "STATUS " + status, Toast.LENGTH_SHORT).show();

//        String idd = getIntent().getStringExtra(EventDetailFragment.ARG_ITEM_ID);

        Car car = new Car(id);
        Manager manager = new Manager();
//        manager.rentCar(car);

        if(status.equals("Available")){
            manager.rentCar(car);
        }
        else {
            //release Car
            manager.releaseCar(car);
        }

//        Toast.makeText(this, "ITEM ID" + idd, Toast.LENGTH_SHORT).show();
        finish();
    }

}
