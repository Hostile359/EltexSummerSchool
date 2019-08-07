package ru.eltex;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import ru.eltex.database.tables.Developer;
import ru.eltex.database.tables.Languages;
import ru.eltex.database.tables.records.DeveloperRecord;
import ru.eltex.database.tables.records.LanguagesRecord;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties properties = new Properties();
        DSLContext context = null;
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/db.properties");
            properties.load(fis);
            Connection connection = DriverManager.getConnection(properties.getProperty("db.host"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password"));
            context = DSL.using(connection, SQLDialect.MYSQL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Result<LanguagesRecord> languages = context.selectFrom(Languages.LANGUAGES).fetch();
        System.out.println("\nLanguages:");
        for (LanguagesRecord lang : languages) {
            System.out.print(lang.getId() + ")");
            System.out.println(lang.getName());
        }

        System.out.println("\nDevelopers 1st select:");
        Result<DeveloperRecord> developers = context.selectFrom(Developer.DEVELOPER).fetch();
        for (DeveloperRecord dev : developers) {
            System.out.print(dev.getId() + ")");
            System.out.println(dev.getFio());
            System.out.println(dev.getPhone());
        }

        context.insertInto(Developer.DEVELOPER).set(Developer.DEVELOPER.ID, 3)
                .set(Developer.DEVELOPER.FIO, "Ivan")
                .set(Developer.DEVELOPER.PHONE, "900")
                .set(Developer.DEVELOPER.EMAIL, "Ivan@mail.ru")
                .execute();

        System.out.println("\nDevelopers 2nd select(Insert Ivan[id 3]):");
        developers = context.selectFrom(Developer.DEVELOPER).fetch();
        for (DeveloperRecord dev : developers) {
            System.out.print(dev.getId() + ")");
            System.out.println(dev.getFio());
            System.out.println(dev.getPhone());
        }

        context.update(Developer.DEVELOPER)
                .set(Developer.DEVELOPER.ID, 65)
                .where(Developer.DEVELOPER.ID.eq(3))
                .execute();

        System.out.println("\nDevelopers 3rd select(Update Ivan id[3->65]):");
        developers = context.selectFrom(Developer.DEVELOPER).fetch();
        for (DeveloperRecord dev : developers) {
            System.out.print(dev.getId() + ")");
            System.out.println(dev.getFio());
            System.out.println(dev.getPhone());
            System.out.println(dev.getEmail());
        }
        context.delete(Developer.DEVELOPER)
                .where(Developer.DEVELOPER.FIO.eq("Ivan"))
                .execute();

        System.out.println("\nDevelopers 4th select(Delete Ivan):");
        developers = context.selectFrom(Developer.DEVELOPER).fetch();
        for (DeveloperRecord dev : developers) {
            System.out.print(dev.getId() + ")");
            System.out.println(dev.getFio());
            System.out.println(dev.getPhone());
            System.out.println(dev.getEmail());
        }

        context.close();
    }
}
