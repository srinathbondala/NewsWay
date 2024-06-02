package com.example.newsway;

public class CatagoryRV {
    private String category;
    private String categotyimg;
    public CatagoryRV(String category, String categotyimg) {
        this.category = category;
        this.categotyimg = categotyimg;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategotyimg() {
        return categotyimg;
    }

    public void setCategotyimg(String categotyimg) {
        this.categotyimg = categotyimg;
    }
}
