package com.university;

import javafx.concurrent.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardCountTask extends Task<Integer> {

    @Override
    protected Integer call() throws Exception {

        String sql = "SELECT COUNT(*) FROM students";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            rs.next();
            return rs.getInt(1);
        }
    }
}
