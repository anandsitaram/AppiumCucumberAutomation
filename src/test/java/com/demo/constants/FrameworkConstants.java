package com.demo.constants;

public final class FrameworkConstants {

    private FrameworkConstants() {

    }

    public static final String USERDIR = System.getProperty("user.dir");
    public static final String TEST_RESOURCE_PATH = USERDIR + "/src/test/resources";
    public static final String TESTDATA_PATH = TEST_RESOURCE_PATH + "/testdata";

    public static final String PROPERTY_PATH = TEST_RESOURCE_PATH + "/config.properties";

    public static final String EXTENTREPORT_PATH = USERDIR + "/Reports/";

    public static final String SCREENSHOT_PATH = USERDIR + "/Screenshots/";

    public static final String YML_PATH = TEST_RESOURCE_PATH + "/devices.yml";
}
