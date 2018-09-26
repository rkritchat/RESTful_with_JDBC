package rkritchat.dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import rkritchat.model.UserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public List<UserInfo> findAll() {
        List<UserInfo> data = new ArrayList<>();

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("055090323");
        dataSource.setDatabaseName("javaee");
        dataSource.setServerName("localhost");
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement("SELECT * FROM user");
            rs = ps.executeQuery();
            while (rs.next()) {
                data.add(new UserInfo(rs.getString("id"), rs.getString("name"), rs.getString("last_name")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (rs != null) con.close();
            } catch (Exception e) {}

        }
        return data;
    }

    public String createUser(UserInfo userInfo) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("055090323");
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("javaee");


        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO user VALUES (?,?,?)");
            ps.setString(1, userInfo.getId());
            ps.setString(2, userInfo.getName());
            ps.setString(3, userInfo.getLastName());
            ps.execute();
            con.commit();
        } catch (SQLException error) {
            System.out.println(error);
        }finally {
            try {
                if(ps!=null) ps.close();
                if(con!=null) con.close();
            } catch (Exception e) {}
        }
        return "Create user Successfully";
    }

    public String updateUser(UserInfo userInfo) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("055090323");
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("javaee");

        PreparedStatement ps = null;
        Connection con = null;

        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE user SET name = ?, last_name = ? WHERE id = ?");
            ps.setString(1, userInfo.getName());
            ps.setString(2, userInfo.getLastName());
            ps.setString(3, userInfo.getId());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }finally {
            try {
                if(ps!=null) ps.close();
                if(con!=null) con.close();
            } catch (Exception e) {}
        }
        return "Update user successfully";
    }

    public String deleteUser(UserInfo userInfo) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("055090323");
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("javaee");

        PreparedStatement ps = null;
        Connection con = null;

        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("DELETE FROM user WHERE id = ?");
            ps.setString(1, userInfo.getId());
            ps.executeUpdate();
            con.commit();

        } catch (SQLException e) {
            System.out.println(e);
        }finally {
            try {
                if(ps!=null) ps.close();
                if(con!=null) con.close();
            } catch (Exception e) {}
        }
        return "Delete user successfully";
    }

}
