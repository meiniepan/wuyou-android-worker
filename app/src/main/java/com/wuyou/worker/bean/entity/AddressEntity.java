package com.wuyou.worker.bean.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DELL on 2018/3/25.
 */

public class AddressEntity implements Parcelable {
    public String address_id;
    public String city_name;
    public String city;
    public String district;
    public String area;
    public String address;
    public String lng;
    public String lat;
    public String name;
    public String mobile;

    public AddressEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address_id);
        dest.writeString(this.city_name);
        dest.writeString(this.city);
        dest.writeString(this.district);
        dest.writeString(this.area);
        dest.writeString(this.address);
        dest.writeString(this.lng);
        dest.writeString(this.lat);
        dest.writeString(this.name);
        dest.writeString(this.mobile);
    }

    protected AddressEntity(Parcel in) {
        this.address_id = in.readString();
        this.city_name = in.readString();
        this.city = in.readString();
        this.district = in.readString();
        this.area = in.readString();
        this.address = in.readString();
        this.lng = in.readString();
        this.lat = in.readString();
        this.name = in.readString();
        this.mobile = in.readString();
    }

    public static final Creator<AddressEntity> CREATOR = new Creator<AddressEntity>() {
        @Override
        public AddressEntity createFromParcel(Parcel source) {
            return new AddressEntity(source);
        }

        @Override
        public AddressEntity[] newArray(int size) {
            return new AddressEntity[size];
        }
    };
}
