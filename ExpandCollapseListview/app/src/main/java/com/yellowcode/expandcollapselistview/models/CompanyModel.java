package com.yellowcode.expandcollapselistview.models;

import java.util.List;

/**
 * Created by Yellow Code Books on 11/3/16.
 */

public class CompanyModel {

    private String name;
    private List<DeviceModel> devices;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DeviceModel> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceModel> devices) {
        this.devices = devices;
    }
}
