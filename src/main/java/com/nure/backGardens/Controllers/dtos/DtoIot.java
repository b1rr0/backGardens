package com.nure.backGardens.Controllers.dtos;

import javax.persistence.Column;

public class DtoIot {

    private double xCoordinate;

    private double yCoordinate;

    private double maxTmp;

    private double minTmp;

    private double minHumidity;

    private double maxHumidity;

    private String description;

    private  int idArea;

    public double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public double getMaxTmp() {
        return maxTmp;
    }

    public void setMaxTmp(double maxTmp) {
        this.maxTmp = maxTmp;
    }

    public double getMinTmp() {
        return minTmp;
    }

    public void setMinTmp(double minTmp) {
        this.minTmp = minTmp;
    }

    public double getMinHumidity() {
        return minHumidity;
    }

    public void setMinHumidity(double minHumidity) {
        this.minHumidity = minHumidity;
    }

    public double getMaxHumidity() {
        return maxHumidity;
    }

    public void setMaxHumidity(double maxHumidity) {
        this.maxHumidity = maxHumidity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }
}
