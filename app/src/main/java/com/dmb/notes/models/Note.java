package com.dmb.notes.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private String title;
    private String content;
    private String timestampe;

    public Note(String title, String content, String timestampe) {
        this.title = title;
        this.content = content;
        this.timestampe = timestampe;
    }

    public Note() {

    }

    protected Note(Parcel in) {
        title = in.readString();
        content = in.readString();
        timestampe = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestampe() {
        return timestampe;
    }

    public void setTimestampe(String timestampe) {
        this.timestampe = timestampe;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", timestampe='" + timestampe + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(timestampe);
    }
}
