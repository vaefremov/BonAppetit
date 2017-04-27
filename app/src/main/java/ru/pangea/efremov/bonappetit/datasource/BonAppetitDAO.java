package ru.pangea.efremov.bonappetit.datasource;

import android.net.Uri;

import java.util.List;

/**
 * Created by efremov on 27/04/17.
 */

public interface BonAppetitDAO {
    String getDetailsByName(int categoryNum, String dishName);

    Uri getImageUriByName(int categoryNum, String dishName);

    int getImageResourceId(int categoryNum, String dishName);

    String[] getCategoriesNames();
    Uri getCategoryIconUriByNum(int categoryNum);
    int getCategoryIconResByNum(int categoryNum);
    String[] getCategoryContent(int categoryNum);
}