package com.example.testapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;
import java.util.List;

/**
 * Create adapter (with 3 primary methods; onCreateVH, onBindViewHolder & getItemC)
 * which populates data into RV, specifying a viewHolder which descibes
 * and provides access to all the views within each item rie
 */
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

//        //Store a member variable for the items of fruit
        private List<Fruit> fruits;  //(fruit)
        private Context context;

    public FruitAdapter(List<Fruit> fruits, Context context) {
        this.fruits = fruits;
        this.context = context;
    }

    @NonNull
    @Override
    public FruitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater inflater = LayoutInflater.from( parent.getContext() );

        View contactView = inflater.inflate( R.layout.display_list_content, parent, false );
        FruitAdapter.ViewHolder viewHolder = new FruitAdapter.ViewHolder( contactView );
        return viewHolder;


    }

    //Involves populating data into the item through the holder
    @Override
    public void onBindViewHolder(@NonNull final FruitAdapter.ViewHolder viewHolder, int position) {

        //Get data model based on position
        final Fruit mValuePos = fruits.get( position );
        viewHolder.content.setText(mValuePos.getType());

            viewHolder.mValues.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Intent intent = new Intent(viewHolder.itemView.getContext(), MainFruitActivity.class);
                    Intent intent = new Intent( viewHolder.itemView.getContext(), MainFruitActivity.class);
                    intent.putExtra("type", mValuePos.getType());
                    intent.putExtra("price", mValuePos.getPrice());
                    intent.putExtra("weight", mValuePos.getWeight());
                    viewHolder.itemView.getContext().startActivity(intent);
                }
            } );


    }

    //Return total count of items in the list
    @Override
    public int getItemCount() {
        return fruits.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        //Declare our views to then populate...
        public View mValues;        //root
        public final TextView content;  //type
        public String mValuePos;


        //Contructor that accepts the entire item row
        public ViewHolder(View itemView) {
            super( itemView );

            mValues = itemView;
           // fruit_list = (RecyclerView) itemView.findViewById( R.id.fruit_list );
            content = (TextView) itemView.findViewById( R.id.content );
        }

        @Override
        public String toString() {
            return super.toString() + " '" + content.getText() + "'";
        }
    }

}