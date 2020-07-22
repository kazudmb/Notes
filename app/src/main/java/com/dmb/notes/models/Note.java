package com.dmb.notes.models;

public class Note {
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
}
