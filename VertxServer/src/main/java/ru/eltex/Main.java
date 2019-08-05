package ru.eltex;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<User> users = new ArrayList<User>();
        users.add(new User(1, "Max"));
        users.add(new User(2, "Sam"));
        users.add(new User(3, "Alex"));
        users.add(new User(4, "Mark"));
        ObjectMapper mapper = new ObjectMapper();
        Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40)); // количество обработчиков
        vertx.createHttpServer().requestHandler(request -> {
            System.out.println(request.uri()); // что было вызвано
            String[] input_str = request.uri().split("/");
            if (input_str[1].equals("get_users")){
                try {
                    String output_str = mapper.writeValueAsString(users);
                    request.response()
                            .putHeader("Content-Type", "application/json")
                            .end(output_str); // завершение и отправка данных
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (input_str[1].equals("get_user")){
                try {
                    Integer id = Integer.parseInt(input_str[2]);
                    String output_str = mapper.writeValueAsString(users.get(id - 1));
                    request.response()
                            .putHeader("Content-Type", "application/json")
                            .end(output_str); // завершение и отправка данных
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            /*request.response()
                    .putHeader("Content-Type", "application/json")
                    .end("{\"text\":\"Hello\"}"); // завершение и отправка данных*/
        }).listen(8081);
    }
}
