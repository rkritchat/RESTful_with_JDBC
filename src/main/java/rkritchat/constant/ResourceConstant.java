package rkritchat.constant;

import com.google.gson.Gson;

public class ResourceConstant {
    private static Gson gson;


    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }
}
