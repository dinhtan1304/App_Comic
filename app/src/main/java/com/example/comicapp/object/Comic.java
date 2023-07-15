package com.example.comicapp.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Comic implements Serializable {
    @SerializedName("ComicId")
    private String ComicId;
    @SerializedName("ComicName")
    private String ComicName;
    @SerializedName("ChapterName")
    private String ChapterName;
    @SerializedName("ImageLink")
    private String ImageLink;

    public Comic(String comicId, String comicName, String chapterName, String imageLink) {
        ComicId = comicId;
        ComicName = comicName;
        ChapterName = chapterName;
        ImageLink = imageLink;
    }

    public String getComicId() {
        return ComicId;
    }

    public void setComicId(String comicId) {
        ComicId = comicId;
    }

    public String getComicName() {
        return ComicName;
    }

    public void setComicName(String comicName) {
        ComicName = comicName;
    }

    public String getChapterName() {
        return ChapterName;
    }

    public void setChapterName(String chapterName) {
        ChapterName = chapterName;
    }

    public String getImageLink() {
        return ImageLink;
    }

    public void setImageLink(String imageLink) {
        ImageLink = imageLink;
    }
}
