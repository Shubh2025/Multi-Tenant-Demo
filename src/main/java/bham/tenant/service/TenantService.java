package bham.tenant.service;

import bham.tenant.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class TenantService {

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPass;

    @Value("${mysql.host}")
    private String mysqlHost;

    @Value("${mysql.port}")
    private String mySqlPort;

    public void createTenantDatabase(String tenantId) {
        String dbName = "tenant_" + tenantId;
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://"+mysqlHost+":"+mySqlPort+"/", dbUser, dbPass)) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE " + dbName);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating DB: " + e.getMessage());
        }

        createProjectTableInTenant(dbName);
    }

    private void createProjectTableInTenant(String dbName) {

        String url = "jdbc:mysql://"+mysqlHost+":"+mySqlPort+"/" + dbName;

        try(Connection connection = DriverManager.getConnection(url,dbUser,dbPass)){
            Statement statement = connection.createStatement();
            String sql = """
                    CREATE TABLE IF NOT EXISTS project (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            name VARCHAR(255),
                                            description TEXT
                                        )
                    """;
            statement.executeUpdate(sql);
        }
        catch (SQLException e){
            throw new RuntimeException("error creating project table: " + e.getMessage());
        }
    }

    public void insertProjectIntoTenant(String tenantId, ProjectDTO projectDTO) {
        String dbName = "tenant_" + tenantId;
        String url = "jdbc:mysql://"+mysqlHost+":"+mySqlPort+"/" + dbName;

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPass)) {
            String sql = "INSERT INTO project (name, description) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, projectDTO.getName());
            stmt.setString(2, projectDTO.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting project: " + e.getMessage());
        }
    }

}
