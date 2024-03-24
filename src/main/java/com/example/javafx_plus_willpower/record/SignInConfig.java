package com.example.javafx_plus_willpower.record;

public record SignInConfig(
        String fxmlFilePath,
        String title
){
    private SignInConfig() {
        this(
                "/com/example/javafx_plus_willpower/Sign_In.fxml",
                "Sign In"
        );
    }
    public static SignInConfig createWithDefaults() {
        return new SignInConfig();
    }
}