package ru.eltex;




import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import org.bson.Document;


import java.net.UnknownHostException;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.sql.*; 

public class Main{

    public static void mongo(ArrayList<User> u) throws Exception {
        FileInputStream fis;
        Properties prop = new Properties();
        fis = new FileInputStream("src/main/resources/db.properties");
        prop.load(fis);
        MongoClient mongoClient = MongoClients.create(prop.getProperty("db.host"));
        MongoDatabase db = mongoClient.getDatabase(prop.getProperty("db.name"));
        MongoCollection<Document> table = db.getCollection(prop.getProperty("db.table"));
// создание записи

        u.forEach(us -> {
                Integer check = 0;
                Document doc = new Document("_id", us.getId())
                .append("name", us.getFio())
                .append("phone", us.getPhone())
                .append("email", us.getEmail());
                for (Document cur : table.find()) {
                    if(doc.get("_id") == cur.get("_id")) {
                        if(!doc.equals(cur)) {
                            BasicDBObject newData = new BasicDBObject();
                            newData.append("$set", new BasicDBObject().append("_id", us.getId())
                                    .append("name", us.getFio())
                                    .append("phone", us.getPhone())
                                    .append("email", us.getEmail()));
                            BasicDBObject searchQuery = new BasicDBObject().append("_id", us.getId());
                            table.updateOne(searchQuery, newData);
                        }
                        check = 1;
                        break;
                    }
                }
                if (check == 0)
                    table.insertOne(doc);
                }); // table.insertMany(userList);
        for (Document cur : table.find()) {
            System.out.println(cur.toJson());
        }
    }

    public static void main(String args[]) {


        ArrayList<User> users = new ArrayList<User>();

        try {

            FileReader fr = new FileReader("files/users.csv");
            Scanner scan = new Scanner(fr);

            for (int j = 0; scan.hasNextLine(); ++j) {
                String input_str;
                input_str = scan.nextLine();
                System.out.println(input_str);
                User temp = new User();
                Integer check = temp.fromCSV(input_str);
                if (check == 0) {
                    users.add(temp);
                } else
                    System.out.println("Wrong format of string: " + temp);
            }
            System.out.println();
        } catch (IOException error) {
            System.out.println("Failed open file");
            System.err.print(error.getMessage());
        }

        try {
            users.forEach(us -> { System.out.println();
                us.printInf();
                System.out.println();});
            mongo(users);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
