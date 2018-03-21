package com.wjb.model;

import javax.persistence.*;

@Entity
@Table(name = "history_item", schema = "cloudnote", catalog = "")
public class HistoryItemEntity {
    private int history__id;
    private int notes_id;
    private String update_time;
    private int update_type;
    private String before_title;
    private String after_title;
    private String before_content;
    private String after_content;

    public HistoryItemEntity(int history__id, int notes_id, String update_time, int update_type, String before_title, String after_title, String before_content, String after_content) {
        this.history__id = history__id;
        this.notes_id = notes_id;
        this.update_time = update_time;
        this.update_type = update_type;
        this.before_title = before_title;
        this.after_title = after_title;
        this.before_content = before_content;
        this.after_content = after_content;
    }

    public HistoryItemEntity() {
    }

    @Id
    @Column(name = "history_id")
    public int getHistory__id() {
        return history__id;
    }

    public void setHistory__id(int history__id) {
        this.history__id = history__id;
    }

    @Basic
    @Column(name = "notes_id")
    public int getNotes_id() {
        return notes_id;
    }

    public void setNotes_id(int notes_id) {
        this.notes_id = notes_id;
    }

    @Basic
    @Column(name = "update_time")
    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    @Basic
    @Column(name = "update_type")
    public int getUpdate_type() {
        return update_type;
    }

    public void setUpdate_type(int update_type) {
        this.update_type = update_type;
    }

    @Basic
    @Column(name = "before_title")
    public String getBefore_title() {
        return before_title;
    }

    public void setBefore_title(String before_title) {
        this.before_title = before_title;
    }

    @Basic
    @Column(name = "after_title")
    public String getAfter_title() {
        return after_title;
    }

    public void setAfter_title(String after_title) {
        this.after_title = after_title;
    }

    @Basic
    @Column(name = "before_content")
    public String getBefore_content() {
        return before_content;
    }

    public void setBefore_content(String before_content) {
        this.before_content = before_content;
    }

    @Basic
    @Column(name = "after_content")
    public String getAfter_content() {
        return after_content;
    }

    public void setAfter_content(String after_content) {
        this.after_content = after_content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HistoryItemEntity that = (HistoryItemEntity) o;

        if (history__id != that.history__id) return false;
        if (notes_id != that.notes_id) return false;
        if (update_type != that.update_type) return false;
        if (update_time != null ? !update_time.equals(that.update_time) : that.update_time != null) return false;
        if (before_title != null ? !before_title.equals(that.before_title) : that.before_title != null) return false;
        if (after_title != null ? !after_title.equals(that.after_title) : that.after_title != null) return false;
        if (before_content != null ? !before_content.equals(that.before_content) : that.before_content != null)
            return false;
        if (after_content != null ? !after_content.equals(that.after_content) : that.after_content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = history__id;
        result = 31 * result + notes_id;
        result = 31 * result + (update_time != null ? update_time.hashCode() : 0);
        result = 31 * result + update_type;
        result = 31 * result + (before_title != null ? before_title.hashCode() : 0);
        result = 31 * result + (after_title != null ? after_title.hashCode() : 0);
        result = 31 * result + (before_content != null ? before_content.hashCode() : 0);
        result = 31 * result + (after_content != null ? after_content.hashCode() : 0);
        return result;
    }
}
