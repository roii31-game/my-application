package com.example.hiring_json;

public class ListReportModel {

    private int id;
    private int listId;
    private String name;

    public ListReportModel(int id, int listId, String name) {
        this.id = id;
        this.listId = listId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ListReportModel{" +
                "id=" + id +
                ", listId=" + listId +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
