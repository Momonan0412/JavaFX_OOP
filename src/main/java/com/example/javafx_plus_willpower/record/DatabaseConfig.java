package com.example.javafx_plus_willpower.record;

public record DatabaseConfig(
        String jdbcUrl, String username, String password, String tableName
){
}
