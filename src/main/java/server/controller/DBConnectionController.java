package server.controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.dao.CommonEntityDao;
import server.dao.entities.Roles;
import server.dao.entities.UsersEntity;

import javax.sql.DataSource;
import java.sql.SQLException;

@RestController
@RequestMapping("/db")
public class DBConnectionController {
    @Autowired
    private DataSource dataSource;
    private CommonEntityDao<UsersEntity> dao;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/isconnected")
    public String isConnected(){
        try {
            if (dataSource.getConnection() != null) {
                return "ok!";
            }
            else return "not ok!";
        }
        catch (SQLException e){
            logger.error(e.getMessage());
            return e.getMessage();
        }
    }

    @GetMapping("/testadduser")
    public HttpStatus addUser() {
        UsersEntity usersEntity = new UsersEntity(
                "Name",
                "0000",
                "qwer1234QWER",
                Roles.ADMINISTRATOR.toString());
        dao = new CommonEntityDao<>(UsersEntity.class);
        try {
            dao.add(usersEntity);
            return HttpStatus.OK;
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @GetMapping("/testgetuser/{id}")
    public String getUserById(@PathVariable int id) {
        try {
            dao = new CommonEntityDao<>(UsersEntity.class);
            UsersEntity user = dao.getById(id);
            Gson json = new Gson();
            return json.toJson(user);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return HttpStatus.INTERNAL_SERVER_ERROR.toString();
        }
    }
}
