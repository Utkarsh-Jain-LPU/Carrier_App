package com.utkarsh.carrierapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class RecieverAdapter extends BaseAdapter {

    private Context context;
    private DataDatabase db;
    private ArrayList<Qwerty> list;

    RecieverAdapter(Context context, ArrayList<Qwerty> list) {
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
        db = new DataDatabase(context);
        final Qwerty curr = list.get(position);
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
        final TextView textView = view.findViewById(R.id.note_text_view);
        String data = "Name :-  "+curr.getName()+"\n"
                +"Specification :-  "+curr.getSpec()+"\n"
                +"Pick Address :-  "+curr.getPick()+"\n"
                +"Delivery Address :-  "+curr.getDel()+"\n"
                +"Phone Number :-  "+curr.getNumber()+"\n"
                +"Fare :-  "+curr.getFare()+"\n"
                +"Status :-  "+status;
        textView.setText(data);
        textView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ViewHolder")
            @Override
            public void onClick(View v) {
                if (curr.getStatus().equals("2")) {
                    PopupMenu popupMenu = new PopupMenu(context,textView);
                    popupMenu.getMenuInflater().inflate(R.menu.abc,popupMenu.getMenu());
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getItemId() == R.id.pay_fare) {
                                db.UpdateData(curr.getName(),curr.getSpec(),curr.getPick(),curr.getDel(),curr.getNumber(),curr.getFare(),"3",curr.getImage());
                                Toast.makeText(context,"Payment Paid Successfully......",Toast.LENGTH_LONG).show();
                                ((Activity)context).onBackPressed();
                            }
                            return true;
                        }
                    });
                }
            }
        });
        return view;
    }
}
