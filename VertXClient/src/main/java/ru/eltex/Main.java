package ru.eltex;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        HttpClientOptions options = new HttpClientOptions().
                setProtocolVersion(HttpVersion.HTTP_2)
                .setSsl(true)
                .setUseAlpn(true)
                .setTrustAll(true);

        HttpClient client = vertx.createHttpClient(options);

        Scanner in = new Scanner(System.in);
        System.out.print("Input id to get info: ");
        String id = in.nextLine();
        System.out.print("Input field to get info: ");
        String field = in.nextLine();
        String request = "https://api.vk.com/method/users.get?user_ids=" + id + "&fields=" + field +
                "&access_token=af1a3d3daf1a3d3daf1a3d3d76af762efaaaf1aaf1a3d3df2531b6cccb0c8e1acccf02f&v=5.101";
        System.out.println("Your request " + request);
        client.requestAbs(HttpMethod.GET, request,
                        result -> {
                            System.out.println(result.statusCode());
                            result.bodyHandler(body -> {
                                System.out.println(body.toString());
                            });
                        }).end();
    }
}
