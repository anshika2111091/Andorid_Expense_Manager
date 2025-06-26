package com.example.expensemanager.models;

public class Category {
    private String categoryName;
     private int categoryImage;
     private int categoryColor;
     public Category(int categoryColor){

         this.categoryColor = categoryColor;
     }
     public Category(String categoryName, int categoryImage, int categoryColor){
         this.categoryImage=categoryImage;
         this.categoryName=categoryName;
         this.categoryColor = categoryColor;
     }

     public int getCategoryColor(){
         return categoryColor;
     }
     public void setCategoryColor(int categoryColor){
         this.categoryColor=categoryColor;
     }
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(int categoryImage) {
        this.categoryImage = categoryImage;
    }
}
