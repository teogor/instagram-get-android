package com.dolphpire.api.instance;

public class DolphPireInstance {

    private String jsonPackage = "", appPackage = "";
    private String apiKey = "";

    public void initialize(String jsonPackageN, String appPackageN, String apiKeyN) {
        this.jsonPackage = jsonPackageN;
        this.appPackage = appPackageN;
        this.apiKey = apiKeyN;
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

}
