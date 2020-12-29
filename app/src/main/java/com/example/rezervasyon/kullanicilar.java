package com.example.rezervasyon;

import android.os.Parcel;
import android.os.Parcelable;

public class kullanicilar implements Parcelable {
    private int userid;
    private String username;
    private String usersurname;
    private String usermail;
    private String userpassword;
    private String userphone;

    public kullanicilar(int userid,String ad, String soyad,String email,String password,String ceptel) {
        this.userid=userid;
        this.username = ad;
        this.userpassword = password;
        this.usermail = email;
        this.usersurname = soyad;
        this.userphone = ceptel;
    }

    public kullanicilar() {

    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsersurname() {
        return usersurname;
    }

    public void setUsersurname(String usersurname) {
        this.usersurname = usersurname;
    }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public static Creator<kullanicilar> getCREATOR() {
        return CREATOR;
    }

    protected kullanicilar(Parcel in) {
        userid = in.readInt();
        username = in.readString();
        usersurname = in.readString();
        usermail = in.readString();
        userpassword = in.readString();
        userphone = in.readString();
    }

    public static final Creator<kullanicilar> CREATOR = new Creator<kullanicilar>() {
        @Override
        public kullanicilar createFromParcel(Parcel in) {
            return new kullanicilar(in);
        }

        @Override
        public kullanicilar[] newArray(int size) {
            return new kullanicilar[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userid);
        dest.writeString(username);
        dest.writeString(usersurname);
        dest.writeString(usermail);
        dest.writeString(userpassword);
        dest.writeString(userphone);
    }
}
