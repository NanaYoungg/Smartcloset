package com.example.actionbar;

public class PredictionItem {
    double prediction1;
    double prediction2;
    double prediction3;

    public PredictionItem(double prediction1, double prediction2, double prediction3) {
        this.prediction1 = prediction1;
        this.prediction2 = prediction2;
        this.prediction3 = prediction3;
    }

    public double getPrediction1() {
        return prediction1;
    }

    public void setPrediction1(double prediction1) {
        this.prediction1 = prediction1;
    }

    public double getPrediction2() {
        return prediction2;
    }

    public void setPrediction2(double prediction2) {
        this.prediction2 = prediction2;
    }

    public double getPrediction3() {
        return prediction3;
    }

    public void setPrediction3(double prediction3) {
        this.prediction3 = prediction3;
    }
}
