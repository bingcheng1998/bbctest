package com.example.bingchenghu.listviewtest;

import android.view.View;

/**
 * Created by bingchenghu on 18/3/8.
 */

public class Fruit {

    private String name;

    private int imageId;



    //构造方法

    public Fruit(String name,int imageId){

        this.name=name;

        this.imageId=imageId;

    }

    //内部方法

    public String getName(){

        return name;

    }

    //内部方法

    public int getImageId(){

        return imageId;

    }

}