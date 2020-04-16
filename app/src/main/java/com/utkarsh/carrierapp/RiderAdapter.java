package com.utkarsh.carrierapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

class RiderAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Qwerty> list;

    RiderAdapter(Context context, ArrayList<Qwerty> list) {
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
        final Qwerty curr = list.get(position);
        final String status;
        if (curr.getStatus().equals("0")) {
            status = "Not Picked";
        }
        else if (curr.getStatus().equals("1")) {
            status = "On the Way";
        }
        else {
            status = "Delivered";
        }
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
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,textView);
                if (curr.getStatus().equals("0")) {
                    popupMenu.getMenuInflater().inflate(R.menu.o_menu,popupMenu.getMenu());
                    popupMenu.show();
                }
                else if (curr.getStatus().equals("1")){
                    popupMenu.getMenuInflater().inflate(R.menu.p_menu,popupMenu.getMenu());
                    popupMenu.show();
                }
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.map_o:
                                Intent intent = new Intent(context,MapsActivity.class);
                                intent.putExtra("Data","1");
                                intent.putExtra("Address",curr.getPick());
                                context.startActivity(intent);
                                break;
                            case R.id.map_p:
                                Intent intent1 = new Intent(context,MapsActivity.class);
                                intent1.putExtra("Data","2");
                                intent1.putExtra("Address",curr.getDel());
                                context.startActivity(intent1);
                                break;
                            case R.id.pick:
                                break;
                            case R.id.deliver:
                                break;
                        }
                        return true;
                    }
                });
            }
        });
        return view;
    }
}
