
package com.mycompany.mymoviedatabase.DAO;

import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author NicoIsaia
 */
public abstract class DatabaseDAO {
    protected Connection conn;

    public DatabaseDAO(Connection conn) {
        this.conn = conn;
    }

    public Connection getConnection() {
        return conn;
    }
    
    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
