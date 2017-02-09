package ro.ubbcluj.cs.books;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ro.ubbcluj.cs.books.books.R;
import ro.ubbcluj.cs.books.domain.Car;

/**
 * A fragment representing a single Book detail screen.
 * This fragment is either contained in a {@link BookListActivity}
 * in two-pane mode (on tablets) or a {@link EventDetailActivity}
 * on handsets.
 */
public class EventDetailFragment extends Fragment {
  /**
   * The fragment argument representing the item ID that this fragment
   * represents.
   */
  public static final String ARG_CAR_ID = "car_id";
  public static final String ARG_CAR_NAME = "car_name";
  public static final String ARG_CAR_TYPE = "car_type";
  public static final String ARG_CAR_STATUS = "car_status";
  /**
   * The dummy content this fragment is presenting.
   */
  private Car mCar;

  /**
   * Mandatory empty constructor for the fragment manager to instantiate the
   * fragment (e.g. upon screen orientation changes).
   */
  public EventDetailFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments().containsKey(ARG_CAR_ID)) {
      // Load the dummy content specified by the fragment
      // arguments. In a real-world scenario, use a Loader
      // to load content from a content provider.
//      mCar = new Car(Integer.parseInt(getArguments().getString(ARG_CAR_ID)), getArguments().getString(ARG_CAR_NAME), getArguments().getString(ARG_CAR_TYPE), "status");
      mCar = new Car(Integer.parseInt(getArguments().getString(ARG_CAR_ID)), getArguments().getString(ARG_CAR_NAME), getArguments().getString(ARG_CAR_TYPE), getArguments().getString(ARG_CAR_STATUS));

      Activity activity = this.getActivity();
      CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
      if (appBarLayout != null) {
        appBarLayout.setTitle(mCar.getName());
      }
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.book_detail, container, false);

    // Show the dummy content as text in a TextView.
    if (mCar != null) {
      ((TextView) rootView.findViewById(R.id.event_detail)).setText(mCar.getName());
    }

    return rootView;
  }
}
