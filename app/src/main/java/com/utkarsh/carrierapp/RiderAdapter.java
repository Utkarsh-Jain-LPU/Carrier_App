package com.utkarsh.carrierapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

class RiderAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Qwerty> list;
    private DataDatabase db;

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
    public View getView(int position, final View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder")
        View view = LayoutInflater.from(context).inflate(R.layout.note_item,parent,false);
        db = new DataDatabase(context);
        final Qwerty curr = list.get(position);
        final String status;
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
                PopupMenu popupMenu = new PopupMenu(context,textView);
                popupMenu.getMenuInflater().inflate(R.menu.mapmenu,popupMenu.getMenu());
                if (curr.getStatus().equals("0")) {
                    popupMenu.getMenu().getItem(0).setTitle("Pick Item to Deliver");
                    popupMenu.show();
                }
                else if (curr.getStatus().equals("1")){
                    popupMenu.getMenu().getItem(0).setTitle("Order Delivered");
                    popupMenu.show();
                }
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.extra:
                                if (curr.getStatus().equals("0")) {
                                    db.UpdateData(curr.getName(),curr.getSpec(),curr.getPick(),curr.getDel(),curr.getNumber(),curr.getFare(),"1",curr.getImage());
                                    Toast.makeText(context,"Item Picked Successfully......",Toast.LENGTH_LONG).show();
                                    ((Activity)context).onBackPressed();
                                }
                                else if (curr.getStatus().equals("1")) {
                                    db.UpdateData(curr.getName(),curr.getSpec(),curr.getPick(),curr.getDel(),curr.getNumber(),curr.getFare(),"2",curr.getImage());
                                    Toast.makeText(context,"Item Delivered Successfully......",Toast.LENGTH_LONG).show();
                                    ((Activity)context).onBackPressed();
                                }
                                break;
                            case R.id.pick:
                                String url = "geo:0,0?q="+curr.getPick();
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                intent.setPackage("com.google.android.apps.maps");
                                if (intent.resolveActivity(context.getPackageManager()) != null) {
                                    context.startActivity(intent);
                                }
                                break;
                            case R.id.drop:
                                String url1 = "geo:0,0?q="+curr.getDel();
                                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(url1));
                                intent1.setPackage("com.google.android.apps.maps");
                                if (intent1.resolveActivity(context.getPackageManager()) != null) {
                                    context.startActivity(intent1);
                                }
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
