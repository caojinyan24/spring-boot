package caojinyan.examples.db;

import java.util.Objects;

public class DatabaseConf {
    String user;
    String password;
    String host;
    Integer port;
    String dbname;
    String markKey;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DatabaseConf)) return false;
        DatabaseConf that = (DatabaseConf) o;
        return Objects.equals(user, that.user) && Objects.equals(password, that.password) && Objects.equals(host, that.host) && Objects.equals(port, that.port) && Objects.equals(dbname, that.dbname) && Objects.equals(markKey, that.markKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, password, host, port, dbname, markKey);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DatabaseConf{");
        sb.append("user='").append(user).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", host='").append(host).append('\'');
        sb.append(", port=").append(port);
        sb.append(", dbname='").append(dbname).append('\'');
        sb.append(", markKey='").append(markKey).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
