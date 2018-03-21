package com.wjb.model;

import javax.persistence.*;

@Entity
@Table(name = "note", schema = "cloudnote", catalog = "")
public class NoteEntity {
    private int id;
    private String title;
    private String content;
    private String creatingDate;
    private int userId;
    private int isCollected;

    public NoteEntity(int id, String title, String content, String creatingDate, int userId, int isCollected) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creatingDate = creatingDate;
        this.userId = userId;
        this.isCollected = isCollected;
    }

    public NoteEntity() {
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "creating_date")
    public String getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(String creatingDate) {
        this.creatingDate = creatingDate;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



    @Basic
    @Column(name = "isCollected")
    public int getIsCollected() {
        return isCollected;
    }

    public void setIsCollected(int isCollected) {
        this.isCollected = isCollected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteEntity that = (NoteEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (isCollected != that.isCollected) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (creatingDate != null ? !creatingDate.equals(that.creatingDate) : that.creatingDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (creatingDate != null ? creatingDate.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + isCollected;
        return result;
    }
}
