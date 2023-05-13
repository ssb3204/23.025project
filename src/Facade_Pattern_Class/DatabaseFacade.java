package Facade_Pattern_Class;

import Dao_Patten_Class.DaoPatten;

import java.sql.*;

public class DatabaseFacade {
    private String url;
    private String username;
    private String password;
    private Connection connection;

    public DatabaseFacade(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void connect() throws SQLException {
        // 데이터베이스 연결 설정
        connection = DriverManager.getConnection(url, username, password);
    }
    public Connection connection() {
        if (connection == null) {
            try {
                connect();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        // 쿼리 실행 및 결과 반환
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet result = statement.executeQuery();
        return result;
    }

    public class UserDAO implements DaoPatten{
            private DatabaseFacade database;

        public UserDAO(DatabaseFacade database) {
            this.database = database;
        }
            @Override
            public void addUserinfo(String id, String password, String name, String email) throws SQLException{
                try (Connection conn = database.connection();
                     PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (id, password, name, email) VALUES (?, ?, ?, ?)")) {
                    stmt.setString(1, id);
                    stmt.setString(2, password);
                    stmt.setString(3, name);
                    stmt.setString(4, email);
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Boolean UserinfoExist(String id) throws SQLException{
                try (Connection conn = database.connection();
                     PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
                    stmt.setString(1, id);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            return Boolean.TRUE;
                        } else {
                            return Boolean.FALSE;
                        }
                    }
                }
            }


            @Override
            public void saveUserinfo(String id, String password, String name, String email) throws SQLException {
                try (Connection conn = database.connection();
                     PreparedStatement stmt = conn.prepareStatement("UPDATE users SET password = ?, name = ?, email = ? WHERE id = ?")) {
                    stmt.setString(1, password);
                    stmt.setString(2, name);
                    stmt.setString(3, email);
                    stmt.setString(4, id);
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public boolean checkUserinfo(String id, String password) throws SQLException {
                try (Connection conn = database.connection();
                     PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ? AND password = ?")) {
                    stmt.setString(1, id);
                    stmt.setString(2, password);
                    try (ResultSet rs = stmt.executeQuery()) {
                        return rs.next();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void deleteUserinfo(String id) throws SQLException {
                try (Connection conn = database.connection();
                     PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
                    stmt.setString(1, id);
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
}

