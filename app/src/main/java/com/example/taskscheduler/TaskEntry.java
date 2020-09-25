package com.example.taskscheduler;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;
// COMPLETED (2) Annotate the class with Entity. Use "task" for the table name
@Entity(tableName = "task")
public class TaskEntry {
    // COMPLETED (3) Annotate the id as PrimaryKey. Set autoGenerate to true.
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String description;
    // COMPLETED (1) Make updatedAt match a column named updated_at. Tip: Use the ColumnInfo annotation
    @ColumnInfo(name = "updated_at")
    private Date updatedAt;
    private int priority;
    // COMPLETED (4) Use the Ignore annotation so Room knows that it has to use the other constructor instead
    @Ignore
    public TaskEntry(String description, Date updatedAt, int priority) {
        this.description = description;
        this.updatedAt = updatedAt;
        this.priority = priority;
    }

    public TaskEntry(int id, String description, Date updatedAt, int priority) {
        this.id = id;
        this.description = description;
        this.updatedAt = updatedAt;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
