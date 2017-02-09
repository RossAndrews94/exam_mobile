package ro.ubbcluj.cs.books;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ro.ubbcluj.cs.books.books.R;

/**
 * Created by Ross Andrews on 09-Feb-17.
 */

public class MainMenuActivity extends AppCompatActivity {

    @BindView(R.id.Clerk_Button)
    Button clerkButton;

    @BindView(R.id.Employee_Button)
    Button employeeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.Clerk_Button)
    public void onClerkClick(View view) {
        Intent intent = new Intent(getApplication(), BookListActivity.class);
        Toast.makeText(this, "Welcome Clerk!", Toast.LENGTH_SHORT).show();
        intent.putExtra("rank", "clerk");
        startActivityForResult(intent, 10000);
    }

    @OnClick(R.id.Employee_Button)
    public void onEmployeeClick(View view) {
        Intent intent = new Intent(getApplication(), BookListActivity.class);
        Toast.makeText(this, "Welcome Employee!", Toast.LENGTH_SHORT).show();
        intent.putExtra("rank", "employee");
        startActivityForResult(intent, 10000);
    }
}
