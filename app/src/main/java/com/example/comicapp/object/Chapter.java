package com.example.comicapp.object;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Chapter implements Serializable {
    @SerializedName("ChapterId")
    private String Id;
    @SerializedName("ChapterName")
    private String ChapterName;
    @SerializedName("PostedDate")
    private String DatePost;

    @SerializedName("ComicId")
    private String IdComic;


    public Chapter(String id, String chapterName, String datePost, String idComic) {
        Id = id;
        ChapterName = chapterName;
        DatePost = datePost;
        IdComic = idComic;
    }

    public String getId() {
        return Id;
    }

    public String getIdComic() {
        return IdComic;
    }

    public String getChapterName() {
        return ChapterName;
    }

    public void setChapterName(String chapterName) {
        ChapterName = chapterName;
    }

    public String getDatePost() {
        return DatePost;
    }

    public void setDatePost(String datePost) {
        DatePost = datePost;
    }
}
