package com.example.CelisParcial1;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {
    private String id;
    private String name;
    private String lastname;
    private String phone;
    private String email;
    private String bday;
    private String group;

    public Contact(String id, String name, String lastname, String phone, String email, String bday, String group) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.bday = bday;
        this.group = group;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBday() { return bday; }

    public void setBday(String bday) { this.bday = bday; }

    public String getGroup() { return group; }

    public void setGroup(String group) { this.group = group; }

    @Override
    public String toString() {
        return "Contacto{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", bday='" + bday + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(lastname);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(bday);
        dest.writeString(group);
    }
    private void readFromParcel(Parcel in){
        id = in.readString();
        name= in.readString();
        lastname = in.readString();
        phone = in.readString();
        email = in.readString();
        bday = in.readString();
        group = in.readString();
    }
    public static final Parcelable.Creator<Contact> CREATOR
            = new Parcelable.Creator<Contact>() {
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
    public Contact(){

    }
    public Contact (Parcel in){
        readFromParcel(in);
    }
}
