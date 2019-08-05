package ru.eltex;

import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/get_users")
public class Servlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        response.setContentType("application/json");

        ArrayList<User> users = new ArrayList<User>();
        users.add(new User(1, "Max"));
        users.add(new User(2, "Sam"));
        users.add(new User(3, "Alex"));
        users.add(new User(4, "Mark"));

        ObjectMapper mapper = new ObjectMapper();

        PrintWriter writer = response.getWriter();
        try {
            //String output_str = "HTTP/1.1 200 OK\nContent-Type:application/json\n\n" + mapper.writeValueAsString(users);
            String output_str = mapper.writeValueAsString(users);

            writer.println(output_str);
        } finally {
            writer.close();
        }
    }
}
