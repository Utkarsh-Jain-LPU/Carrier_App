package com.utkarsh.carrierapp;

import android.os.Parcel;
import android.os.Parcelable;

class Qwerty implements Parcelable {

    private String name,spec,pick,del,number,fare,status;

    Qwerty(String name, String spec, String pick, String del, String number, String fare, String status) {
        this.name = name;
        this.spec = spec;
        this.pick = pick;
        this.del = del;
        this.number = number;
        this.fare = fare;
        this.status = status;
    }

    private Qwerty(Parcel in) {
        name = in.readString();
        spec = in.readString();
        pick = in.readString();
        del = in.readString();
        number = in.readString();
        fare = in.readString();
        status = in.readString();
    }

    public static final Creator<Qwerty> CREATOR = new Creator<Qwerty>() {
        @Override
        public Qwerty createFromParcel(Parcel in) {
            return new Qwerty(in);
        }

        @Override
        public Qwerty[] newArray(int size) {
            return new Qwerty[size];
        }
    };

    String getName() {
        return name;
    }

    String getSpec() {
        return spec;
    }

    String getPick() {
        return pick;
    }

    String getDel() {
        return del;
    }

    String getNumber() {
        return number;
    }

    String getFare() {
        return fare;
    }

    String getStatus() {
        return status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(spec);
        dest.writeString(pick);
        dest.writeString(del);
        dest.writeString(number);
        dest.writeString(fare);
        dest.writeString(status);
    }
}
