package com.utkarsh.carrierapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class ListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Qwerty> list;

    ListAdapter(Context context, ArrayList<Qwerty> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder")
        View view = LayoutInflater.from(context).inflate(R.layout.note_item,parent,false);
        Qwerty curr = list.get(position);
        String status;
        switch (curr.getStatus()) {
            case "0":
                status = "Not Picked";
                break;
            case "1":
                status = "On the Way";
                break;
            case "2":
                status = "Payment Pending";
                break;
            default:
                status = "Delivered";
                break;
        }
        ImageView imageView = view.findViewById(R.id.note_image);
        imageView.setImageBitmap(BitmapUtility.getImage(curr.getImage()));
        TextView textView = view.findViewById(R.id.note_text_view);
        String data = "Name :-  "+curr.getName()+"\n"
                +"Specification :-  "+curr.getSpec()+"\n"
                +"Pick Address :-  "+curr.getPick()+"\n"
                +"Delivery Address :-  "+curr.getDel()+"\n"
                +"Phone Number :-  "+curr.getNumber()+"\n"
                +"Fare :-  "+curr.getFare()+"\n"
                +"Status :-  "+status;
        textView.setText(data);
        return view;
    }
}
