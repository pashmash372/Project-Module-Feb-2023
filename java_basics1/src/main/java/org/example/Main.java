package org.example;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.Consumer;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {

//        Arrays.stream(args).forEach((Consumer<? super String>) System.out::println);
        if (args.length == 0) {
            System.out.println(Lib.getGreeting());
        } else {
            if (args[0].equals("example")) {
                var httpLib = new HttpLib();
                System.out.println(httpLib.getExampleDotCom());
            }
            if (args[0].equals("posts")) {

                var api = JSONPlaceholderAPI.getInstance();

                api.getPosts().execute().body().forEach(post -> {
                    System.out.println(post.getTitle());
                });
            }

            if (args[0].equals("photos")) {

                var api = JSONPlaceholderAPI.getInstance();

                api.getPhotos().execute().body().forEach(photo -> {
                    System.out.println(photo.getUrl());
                });
            }
        }
    }
}