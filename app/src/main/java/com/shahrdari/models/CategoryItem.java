package com.shahrdari.models;

import java.io.Serializable;

public class CategoryItem implements Serializable {

    private static final long serialVersionUID = 1L;
    public String _photourl = "";
    public String _title = "";
    private String _Id = "";
    private String _name = "";
    private String _haschild = "";
    private String ParentCode = "";
    private String Name = "";
    private String HasChild = "";
    private String ImageUrl = "";
    private String Code = "";

    public CategoryItem() {
    }

    public CategoryItem(String _Id, String _name) {
        this._Id = _Id;
        this._name = _name;
    }

    public String getParentCode() {
        return ParentCode;
    }

    public String getHasChild() {
        return HasChild;
    }

    public void setHasChild(String haschild) {
        _haschild = haschild;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getHaschild() {
        return _haschild;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String get_name() {
        return Name;
    }

    public void set_name(String name) {
        Name = name;
    }


    public String toString() {
        return (_name);
    }

    public String getId() {
        return _Id;
    }

    public void setId(String Id) {
        _Id = Id;
    }

    public String get_photourl() {
        return _photourl;
    }

    public void set_photourl(String _photourl) {
        this._photourl = _photourl;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }
}
