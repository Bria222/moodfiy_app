package dao;

import Utils.SQLUtils;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserLogin {

    public String _user_name;
    public String _info;

    public User(String userName, String info) {
        _user_name = userName;
        _info = info;
    }

    public static boolean addUser(SQLUtils conn, String userName, String password, String info) {
        String q = "INSERT INTO users (username, password, info) VALUES (?, ?, ?)";

        try {
            List<Integer> keys = new ArrayList<>();
            return conn.runPreparedInsert(q, keys, userName, BCrypt.hashpw(password, BCrypt.gensalt(12)), info);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean validateLogin(SQLUtils conn, String userName, String password) {

        String q = "SELECT password FROM users WHERE username = ?";

        try {
            Optional<ResultSet> oRs = conn.runPreparedQuery(q, userName);
            if (oRs.isPresent()) {
                ResultSet rs = oRs.get();
                if (rs.next()) {
                    String dbPassword = rs.getString("password");
                    return BCrypt.checkpw(password, dbPassword);
                } else {
                    System.out.println("Username not found");
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String createSession(SQLUtils conn, String userName) {
        String q = "UPDATE users SET session = ?, login_time = UNIX_TIMESTAMP(NOW()) WHERE username = ?";

        String session = generateSession();
        try {
            if (conn.runPreparedUpdate(q, session, userName) == 1) {
                return session;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static boolean updateProfile(SQLUtils conn, String userName, String newUserName, String newInfo) {

        if (newUserName.isEmpty() && newInfo.isEmpty()) {
            return true;
        }

        List<String> params = new ArrayList<>();

        StringBuilder q = new StringBuilder();
        q.append("UPDATE users SET ");

        if (!newUserName.isEmpty()) {
            q.append("username = ?,");
            params.add(newUserName);
        }

        if (!newInfo.isEmpty()) {
            q.append("info = ?,");
            params.add(newInfo);
        }

        // remove last comma
        q.setLength(q.length() - 1);

        q.append(" WHERE username = ?");
        params.add(userName);

        try {
            return conn.runPreparedUpdate(q.toString(), params.toArray()) == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean validateSession(SQLUtils conn, String session, String userName, long maxSessionDuration) {
        String q = "SELECT 1 FROM users WHERE username = ? AND session = ? AND (UNIX_TIMESTAMP(NOW()) - login_time) <= ?"
                + " AND session IS NOT NULL AND login_time IS NOT NULL";

        try {
            Optional<ResultSet> oRs = conn.runPreparedQuery(q, userName, session, maxSessionDuration);
            if (oRs.isPresent()) {
                ResultSet rs = oRs.get();
                return rs.next();
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean deleteUser(SQLUtils conn, String userName) {
        String q = "DELETE FROM users WHERE username = ?";

        try {
            return conn.runPreparedUpdate(q, userName) == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean changePassword(SQLUtils conn, String userName, String password) {
        String q = "UPDATE users SET password = ? WHERE username = ?";

        try {
            return conn.runPreparedUpdate(q, BCrypt.hashpw(password, BCrypt.gensalt(12)), userName) == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Optional<User> getPublicUserInfo(SQLUtils conn, String userName) {
        String q = "SELECT username, info FROM users WHERE username = ?";

        try {
            Optional<ResultSet> oRs = conn.runPreparedQuery(q, userName);
            if (oRs.isPresent()) {
                ResultSet rs = oRs.get();
                if (rs.next()) {
                    return Optional.of(new User(userName, rs.getString("info")));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    private static String generateSession() {
        return String.valueOf(UUID.randomUUID());
    }


}