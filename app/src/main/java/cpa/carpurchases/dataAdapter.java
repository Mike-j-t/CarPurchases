package cpa.carpurchases;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class dataAdapter extends ArrayAdapter<Customer>
{


    public dataAdapter(Context context, ArrayList<Customer> customers)
    {
        super(context, R.layout.list_customers, customers);
        Context context1 = context;
        ArrayList<Customer> mcustomer = customers;
    }

    public  class  Holder
    {
        TextView idV;
        TextView nameFV;
        TextView nameLV;
        TextView carmakeV;
        TextView carmodelV;
        TextView carcostV;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent)
    {
        // GET DATA ITEM FOR THIS POSITION

        Customer data = getItem(position);
        // CHECK IF EXISTING VIEW IS BEING REUSED, OTHERWISE INFLATE VIEW

        // VIEW LOOKUP CACHE STORED IN TAG
        Holder viewHolder;

        if (convertView == null)
        {
            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_customers, parent, false);

            /*
            viewHolder.idV = (TextView) convertView.findViewById(R.id.textView3_CustomerID);
            viewHolder.nameFV = (TextView) convertView.findViewById(R.id.textView3_FName);
            viewHolder.nameLV = (TextView) convertView.findViewById(R.id.textView3_LName);
            viewHolder.carmakeV = (TextView) convertView.findViewById(R.id.textView3_CarMake);
            viewHolder.carmodelV = (TextView) convertView.findViewById(R.id.textView3_CarModel);
            viewHolder.carcostV = (TextView) convertView.findViewById(R.id.textView3_CarCost);
            */

            viewHolder.idV = (TextView) convertView.findViewById(R.id.textView3_CustomerID);
            viewHolder.nameFV = (TextView) convertView.findViewById(R.id.textView3_FName);
            viewHolder.nameLV = (TextView) convertView.findViewById(R.id.textView3_LName);
            viewHolder.carmakeV = (TextView) convertView.findViewById(R.id.textView3_CarMake);
            viewHolder.carmodelV = (TextView) convertView.findViewById(R.id.textView3_CarModel);
            viewHolder.carcostV = (TextView) convertView.findViewById(R.id.textView3_CarCost);

            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (Holder) convertView.getTag();
        }

        viewHolder.nameFV.setText("First Name: " + data.getFName());
        viewHolder.nameLV.setText("Last Name: " + data.getLName());
        viewHolder.carmakeV.setText("Car Make: " + data.get_CarMake());
        viewHolder.carmodelV.setText("Car Model: " + data.get_CarModel());
        viewHolder.carcostV.setText("Car Cost: " + data.get_CarCost());

        // RETURN COMPLETED VIEW TO RENDER ON SCREEN
        return convertView;
    }
}
