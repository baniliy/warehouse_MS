package com.baniliy.bean;

public class Material {
    private Integer id;
    private String serial;
    private String name;
    private String kind;
    private  Integer num;
    private String location;
    private String updatetime;
    private Integer status;

    public Material() {
    }

    public Material(Integer id, String serial, String name, String kind, Integer num, String location, String updatetime, Integer status) {
        this.id = id;
        this.serial = serial;
        this.name = name;
        this.kind = kind;
        this.num = num;
        this.location = location;
        this.updatetime = updatetime;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "material{" +
                "id=" + id +
                ", serial='" + serial + '\'' +
                ", name='" + name + '\'' +
                ", kind='" + kind + '\'' +
                ", num=" + num +
                ", location='" + location + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", status=" + status +
                '}';
    }
}
