package com.example.popularmovies.Database;


import androidx.room.TypeConverter;


public class DataConverter2DArray   {
    @TypeConverter
    public String fromArray(String[][] strings) {
        String data = "";
        for(int i=0;i<strings.length;i++)
           data += ("$|%|"+ strings[i][0] + "%|"+strings[i][1]);

        return data;
    }

    @TypeConverter
    public String[][] toArray(String data) {
        String SplitArr[]=data.split("$|",0);
        String SubSplitArr[]=null;
       String[][] myStrings = new String[SplitArr.length][2];
for(int i=0;i<SplitArr.length;i++)
{
    SubSplitArr=SplitArr[i].split("%|");
    myStrings[i][0]=SubSplitArr[0];
    myStrings[i][1]=SubSplitArr[1];

}


        return myStrings;
    }
}