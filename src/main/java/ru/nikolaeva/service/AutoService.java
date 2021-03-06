package ru.nikolaeva.service;

import ru.nikolaeva.domain.Auto;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AutoService {
    private final DataSource ds;

    public AutoService() throws NamingException {
        var context = new InitialContext();
        ds = (DataSource) context.lookup("java:/comp/env/jdbc/db");
    }

    public List<Auto> getAll() {
        try (var conn = ds.getConnection()) {
            try (var stmt = conn.createStatement()) {
                try (var rs = stmt.executeQuery("SELECT id, nsme, description, image from auto")) {
                    var list = new ArrayList<Auto>();
                    while (rs.next()) {
                        var id = rs.getString("id");
                        var name = rs.getString("name");
                        var description = rs.getString("description");
                        var image = rs.getString("image");
                        list.add(new Auto(id, name, description, image));
                    }
                    return list;

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void create(String name, String description, Part file) throws IOException {
        var fileName = UUID.randomUUID().toString();
        file.write(fileName);

        try (var con = ds.getConnection()) {
            try (var stmt = con.prepareStatement(
                    "INSERT INTO autos\n" + "(id, name, description, image)\n" + "VALUE (?, ?, ?, ?);"
            )) {
                stmt.setString(1, UUID.randomUUID().toString());
                stmt.setString(2, name);
                stmt.setString(3, description);
                stmt.setString(4, fileName);
                stmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public Auto getById(String id) {
        try (var conn = ds.getConnection()) {
            try (var stmt = conn.prepareStatement("SELECT id, name, description, image FROM autos WHERE id = ?")) {
                stmt.setString(1,id);
                try (var rs = stmt.executeQuery()){
                    if (!rs.next()) {
                        throw new RuntimeException("404");
                    }
                    var name = rs.getString("name");
                    var description = rs.getString("description");
                    var image = rs.getString("image");
                    return new Auto(id, name, description, image);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();        }
    }
    public List<Auto> findByName (String q) {
        try (var conn = ds.getConnection()) {
            try (var stmt = conn.prepareStatement("SELECT id, name, description, image FROM autos WHERE  name = ?")) {
                stmt.setString(1, q);
                try (var rs = stmt.executeQuery()){
                    var list = new ArrayList<Auto>();

                    while (rs.next()){
                        var id = rs.getString("id");
                        var name = rs.getString("name");
                        var description = rs.getString("description");
                        var image = rs.getString("image");
                        list.add(new Auto(id, name,description, image));
                    }
                    return list;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
