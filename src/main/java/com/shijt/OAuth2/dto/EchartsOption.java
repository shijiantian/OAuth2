package com.shijt.OAuth2.dto;

import java.io.Serializable;

public class EchartsOption implements Serializable {
    private Object title;
    private Object legend;
    private Object xAxis;
    private Object yAxis;
    private Object series;

    public Object getTitle() {
        return title;
    }

    public void setTitle(Object title) {
        this.title = title;
    }

    public Object getLegend() {
        return legend;
    }

    public void setLegend(Object legend) {
        this.legend = legend;
    }

    public Object getxAxis() {
        return xAxis;
    }

    public void setxAxis(Object xAxis) {
        this.xAxis = xAxis;
    }

    public Object getyAxis() {
        return yAxis;
    }

    public void setyAxis(Object yAxis) {
        this.yAxis = yAxis;
    }

    public Object getSeries() {
        return series;
    }

    public void setSeries(Object series) {
        this.series = series;
    }
}
