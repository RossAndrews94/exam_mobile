package ro.ubbcluj.cs.books.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ro.ubbcluj.cs.books.EventDetailActivity;
import ro.ubbcluj.cs.books.EventDetailFragment;
import ro.ubbcluj.cs.books.books.R;
import ro.ubbcluj.cs.books.domain.Car;

public class MyAdapter
        extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    //  private final List<Book> mValues;
    //private final List<Car> mValues;

    private final List<Car> mValues;
    
    public MyAdapter() {
        mValues = new ArrayList<>();
    }

    public void addData(Car car) {
        mValues.add(car);
        notifyDataSetChanged();
    }

    public void clear() {
        mValues.clear();
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mCar = mValues.get(position);
//        holder.mIdView.setText(String.valueOf(mValues.get(position).getId()));
        holder.mNameView.setText(mValues.get(position).getName());
        holder.mTypeView.setText(String.valueOf(mValues.get(position).getType()));
        holder.mStatusView.setText(String.valueOf(mValues.get(position).getStatus()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, EventDetailActivity.class);
                intent.putExtra(EventDetailFragment.ARG_CAR_ID, String.valueOf(holder.mCar.getId()));
                intent.putExtra(EventDetailFragment.ARG_CAR_NAME, String.valueOf(holder.mCar.getName()));
                intent.putExtra(EventDetailFragment.ARG_CAR_TYPE, String.valueOf(holder.mCar.getType()));
                intent.putExtra(EventDetailFragment.ARG_CAR_STATUS, String.valueOf(holder.mCar.getStatus()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public Car getCarbyId(int id){
        return mValues.get(id);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
//        public final TextView mIdView;
        public final TextView mNameView;
        public final TextView mTypeView;
        public final TextView mStatusView;
        public Car mCar;

        public ViewHolder(View view) {
            super(view);
            mView = view;
//            mIdView = (TextView) view.findViewById(R.id.id);
            mNameView = (TextView) view.findViewById(R.id.name);
            mTypeView = (TextView) view.findViewById(R.id.type);
            mStatusView = (TextView) view.findViewById(R.id.status);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
