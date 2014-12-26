package com.thotp.twiliotutorial.util;

import com.thotp.twiliotutorial.entity.InboundMessage;
import com.thotp.twiliotutorial.entity.OutboundMessage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbUtils {

    private static final String DB_NAME = "<your database name>";
    private static Connection connection = null;

    static {
        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE,
                    "Failed to establish db connection", e);
        } catch (SQLException e) {
            Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE,
                    "Failed to establish db connection", e);
        }
    }

    private static Connection getConnection()
            throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = String.format("jdbc:mysql://%s:%s/%s",
                System.getenv("OPENSHIFT_MYSQL_DB_HOST"),
                System.getenv("OPENSHIFT_MYSQL_DB_PORT"), DB_NAME);
        return DriverManager.getConnection(url,
                System.getenv("OPENSHIFT_MYSQL_DB_USERNAME"),
                System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD"));
    }

    public static List<OutboundMessage> getOutboundMessages(int skip, int limit)
            throws SQLException {
        List<OutboundMessage> list = new LinkedList<OutboundMessage>();
        if (connection == null) {
            throw new SQLException("No db connection!");
        }
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            rs = statement
                    .executeQuery(String
                            .format("SELECT * FROM `OutboundMessage`"
                                    + " ORDER BY id DESC"
                                    + " LIMIT %d OFFSET %d",
                                    limit, skip));
            if (rs != null) {
                while (rs.next()) {
                    OutboundMessage msg = new OutboundMessage();
                    msg.setId(rs.getInt("id"));
                    msg.setSid(rs.getString("sid"));
                    msg.setTo(rs.getString("to"));
                    msg.setBody(rs.getString("body"));
                    msg.setStatus(rs.getString("status"));
                    msg.setCreatedDate(rs.getTimestamp("createdDate"));
                    list.add(msg);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return list;
    }

    public static OutboundMessage getOutboundMessageBySid(String sid)
            throws SQLException {
        if (connection == null) {
            throw new SQLException("No db connection!");
        }
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection
                    .prepareStatement("SELECT * FROM `OutboundMessage` WHERE `sid` = ?");
            statement.setString(1, sid);
            rs = statement.executeQuery();
            if (rs != null && rs.next()) {
                OutboundMessage msg = new OutboundMessage();
                msg.setId(rs.getInt("id"));
                msg.setSid(rs.getString("sid"));
                msg.setTo(rs.getString("to"));
                msg.setBody(rs.getString("body"));
                msg.setStatus(rs.getString("status"));
                msg.setCreatedDate(rs.getTimestamp("createdDate"));
                return msg;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return null;
    }

    public static boolean insert(OutboundMessage msg) throws SQLException {
        if (connection == null) {
            throw new SQLException("No db connection!");
        }
        PreparedStatement statement = null;
        try {
            statement = connection
                    .prepareStatement("INSERT INTO OutboundMessage(`sid`, `to`, `body`, `status`)"
                            + " VALUES(?,?,?,?)");
            statement.setString(1, msg.getSid());
            statement.setString(2, msg.getTo());
            statement.setString(3, msg.getBody());
            statement.setString(4, msg.getStatus());
            int n = statement.executeUpdate();
            return n > 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public static boolean update(OutboundMessage msg) throws SQLException {
        if (connection == null) {
            throw new SQLException("No db connection!");
        }
        PreparedStatement statement = null;
        try {
            statement = connection
                    .prepareStatement("UPDATE `OutboundMessage`"
                            + " SET `sid`=?, `to`=?, `body`=?, `status`=?"
                            + " WHERE `id`=?");
            statement.setString(1, msg.getSid());
            statement.setString(2, msg.getTo());
            statement.setString(3, msg.getBody());
            statement.setString(4, msg.getStatus());
            statement.setInt(5, msg.getId());
            int n = statement.executeUpdate();
            return n > 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public static boolean insert(InboundMessage msg) throws SQLException {
        if (connection == null) {
            throw new SQLException("No db connection!");
        }
        PreparedStatement statement = null;
        try {
            statement = connection
                    .prepareStatement("INSERT INTO InboundMessage(`sid`, `from`, `body`)"
                            + " VALUES(?,?,?)");
            statement.setString(1, msg.getSid());
            statement.setString(2, msg.getFrom());
            statement.setString(3, msg.getBody());
            int n = statement.executeUpdate();
            return n > 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public static List<InboundMessage> getInboundMessages(int skip, int limit)
            throws SQLException {
        List<InboundMessage> list = new LinkedList<InboundMessage>();
        if (connection == null) {
            throw new SQLException("No db connection!");
        }
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            rs = statement
                    .executeQuery(String
                            .format("SELECT * FROM `InboundMessage`"
                                    + " ORDER BY id DESC"
                                    + " LIMIT %d OFFSET %d",
                                    limit, skip));
            if (rs != null) {
                while (rs.next()) {
                    InboundMessage msg = new InboundMessage();
                    msg.setId(rs.getInt("id"));
                    msg.setSid(rs.getString("sid"));
                    msg.setFrom(rs.getString("from"));
                    msg.setBody(rs.getString("body"));
                    msg.setReceivedDate(rs.getTimestamp("receivedDate"));
                    list.add(msg);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return list;
    }

}
