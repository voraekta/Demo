package com.example.demo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MasterModel {
    @SerializedName("success")
    @Expose
    boolean success;

    @SerializedName("data")
    @Expose
    DataListModel data;

    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataListModel getData() {
        return this.data;
    }

    public void setData(DataListModel data) {
        this.data = data;
    }

    public static class DataListModel {
        @SerializedName("memes")
        @Expose
        ArrayList<Memes> memes;

        public ArrayList<Memes> getMemes() {
            return this.memes;
        }

        public void setMemes(ArrayList<Memes> memes) {
            this.memes = memes;
        }
    }

    public static class Memes {
        @SerializedName("id")
        @Expose
        String id;

        @SerializedName("name")
        @Expose
        String name;

        @SerializedName("url")
        @Expose
        String url;

        @SerializedName("width")
        @Expose
        int width;

        @SerializedName("height")
        @Expose
        int height;

        @SerializedName("box_count")
        @Expose
        int box_count;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getBox_count() {
            return box_count;
        }

        public void setBox_count(int box_count) {
            this.box_count = box_count;
        }
    }
}

