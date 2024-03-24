package com.example.javafx_plus_willpower.record;

public record SignUpConfig(
        String fxmlFilePath,
        String title
){
    private SignUpConfig() {
        this(
                "/com/example/javafx_plus_willpower/Sign_Up.fxml",
                "Sign Up"
        );
    }
    public static SignUpConfig createWithDefaults() {
        return new SignUpConfig();
    }
}
