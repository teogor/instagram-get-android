package com.dolphpire.api.instance;

public class DolphPireInstance {

    private String jsonPackage = "", appPackage = "";
    private String apiKey = "", secretKey = "";

    public void initialize(String jsonPackageN, String appPackageN, String apiKeyN, String secretKeyN) {
        this.jsonPackage = jsonPackageN;
        this.appPackage = appPackageN;
        this.apiKey = apiKeyN;
        this.secretKey = secretKeyN;
    }

    public String getJsonPackage() {
        return this.jsonPackage;
    }

    public String getPackage() {
        return appPackage;
    }

    public String getKey() {
        return apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
