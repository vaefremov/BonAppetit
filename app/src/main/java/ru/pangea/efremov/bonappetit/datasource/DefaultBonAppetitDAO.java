package ru.pangea.efremov.bonappetit.datasource;

import android.content.Context;
import android.net.Uri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import ru.pangea.efremov.bonappetit.R;

/**
 * Created by efremov on 27/04/17.
 */

public class DefaultBonAppetitDAO implements BonAppetitDAO {

    // Naive singleton implementation
    public static BonAppetitDAO instance = null;
    public static BonAppetitDAO getInstance() {
        return instance;
    }
    public static void initializeForContext(Context context) {
        instance = new DefaultBonAppetitDAO(context);
    }

    private HashMap<DishKey, DishItem> dishesDb = new HashMap<>();
    private String[] categories;
    private Uri[] categoiesIconsUri;
    private HashMap<Integer, String[]> categoriesContent = new HashMap<>();
    private Context context;

    DefaultBonAppetitDAO(Context context) {
        this.context = context;
        populateDb();
    }

    @Override
    public String getDetailsByName(int categoryNum, String dishName) {
        DishKey key = new DishKey(categoryNum, dishName);
        if(dishesDb.containsKey(key)) {
            return dishesDb.get(key).getDetails();
        } else {
            return dishName;
        }
    }

    @Override
    public Uri getImageUriByName(int categoryNum, String dishName) {
        DishKey key = new DishKey(categoryNum, dishName);
        if(dishesDb.containsKey(key)) {
            return dishesDb.get(key).getImageUri();
        } else {
            return null;
        }
    }

    @Override
    public String[] getCategoriesNames() {
        return categories;
    }

    @Override
    public Uri getCategoryIconUriByNum(int categoryNum) {
        return categoiesIconsUri[categoryNum];
    }

    @Override
    public int getCategoryIconResByNum(int categoryNum) {
        return R.mipmap.chinglish_dish2;
    }

    @Override
    public String[] getCategoryContent(int categoryNum) {
        if(categoriesContent.containsKey(categoryNum)) {
            return categoriesContent.get(categoryNum);
        }
        return new String[0];
    }

    @Override
    public int getImageResourceId(int categoryNum, String dishName) {
        DishKey key = new DishKey(categoryNum, dishName);
        if(dishesDb.containsKey(key)) {
            return dishesDb.get(key).getResourceId();
        } else {
            return R.mipmap.chinglish_dish2;
        }
    }

    private void populateDb() {
        categories = context.getResources().getStringArray(R.array.categories);
        int[] categoriesContentRes = new int[] {R.array.fishes, R.array.meats, R.array.others};

        if (categoriesContentRes.length != categories.length) throw new AssertionError();
        categoiesIconsUri = new Uri[categories.length];
        for (int i = 0; i < categories.length; i++) {
            categoriesContent.put(i, context.getResources().getStringArray(categoriesContentRes[i]));
            switch (i) {
                case 0:
                    categoiesIconsUri[i] = Uri.parse("http://pngimg.com/uploads/fish/fish_PNG25151.png");
                    break;
                case 1:
                    categoiesIconsUri[i] = Uri.parse("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr9bacuBPTUsPmug6XFBaZz0k1rxUI7rkVdOfYYIeFmwgtXrU");
                    break;
//                case 3:
//                    categoiesIconsUri[i]  = Uri.parse("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQbX9GWf3OmCUQF7xG3PXEEF_QRxww5USxaQSPRyEOAlmxgr1Ue");
//                    break;
            }
        }
        // Category others content:
        int currentCategory = 2;
        String[] otherDishesNames = context.getResources().getStringArray(R.array.others);
        for (int i = 0; i < otherDishesNames.length; i++) {
            if(i == 1) {
                DishItem item = new DishItem(otherDishesNames[i],
                        categories[currentCategory],
                        context.getResources().getString(R.string.fried_ficks_description),
                        null,
                        R.mipmap.chinglish_dish2);
                dishesDb.put(new DishKey(currentCategory, otherDishesNames[i]), item);
            }
        }
    }

    private static class DishKey {
        private final int categoryNum;
        private final String name;

        public DishKey(int categoryNum, String name) {
            this.categoryNum = categoryNum;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DishKey dishKey = (DishKey) o;

            if (categoryNum != dishKey.categoryNum) return false;
            return name.equals(dishKey.name);

        }

        @Override
        public int hashCode() {
            int result = categoryNum;
            result = 31 * result + name.hashCode();
            return result;
        }
    }

    private static class DishItem {
        private final String name;
        private final String category;
        private final String details;
        private final Uri imageUri;
        private final int resourceId;

        public DishItem(String name, String category, String details, Uri imageUri, int resourceId) {
            this.name = name;
            this.category = category;
            this.details = details;
            this.imageUri = imageUri;
            this.resourceId = resourceId;
        }

        public String getName() {
            return name;
        }

        public String getCategory() {
            return category;
        }

        public String getDetails() {
            return details;
        }

        public Uri getImageUri() {
            return imageUri;
        }

        public int getResourceId() {
            return resourceId;
        }
    }
}
