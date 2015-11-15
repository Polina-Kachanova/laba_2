package com.lazycook.mvc;
import sun.plugin.javascript.navig.Image;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "RECIPE")
public class Recipe implements Serializable {

    private static final long serialVersionUID = -5527566248002296042L;

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer Id;

    @Column(name = "NAME")

    private String Name;

    @Column(name = "DESCRIPTION")
    private String Description;

    @Column(name = "NUM_LIKES")
    private Integer Num_Likes;

    @Column(name = "TYPE")
    private String Type;

    @Column(name = "PHOTO")
    private Image Photo;

    @Column(name = "TAG")
    private String Tag;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public String getDescrition() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public Integer getNum_Likes() {
        return Num_Likes;
    }

    public void setNum_Likes(Integer num_likes) {
        this.Num_Likes = num_likes;
    }

    public Image getPhoto() {
        return Photo;
    }

    public void setPhoto(Image photo) {
        this.Photo = photo;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        this.Tag = tag;
    }
}

