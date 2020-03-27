/*
 * Made With Love
 * Author @Moh Husni Mubaraq
 * Not for Commercial Purpose
 */

package id.husni.covninfo.model;

import com.google.gson.annotations.SerializedName;

public class WorldSummaryModel {
    @SerializedName("lastUpdate")
    private String lastUpdate;
    @SerializedName("confirmed")
    private Confirmed confirmed;
    @SerializedName("deaths")
    private Deaths deaths;
    @SerializedName("recovered")
    private Recovered recovered;

    public WorldSummaryModel(String lastUpdate, Confirmed confirmed, Deaths deaths, Recovered recovered) {
        this.lastUpdate = lastUpdate;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Confirmed getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Confirmed confirmed) {
        this.confirmed = confirmed;
    }

    public Deaths getDeaths() {
        return deaths;
    }

    public void setDeaths(Deaths deaths) {
        this.deaths = deaths;
    }

    public Recovered getRecovered() {
        return recovered;
    }

    public void setRecovered(Recovered recovered) {
        this.recovered = recovered;
    }

    public static class Confirmed {
        @SerializedName("value")
        private int value;
        @SerializedName("detail")
        private String detail;

        public Confirmed(int value, String detail) {
            this.value = value;
            this.detail = detail;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }

    public static class Deaths {
        @SerializedName("value")
        private int value;
        @SerializedName("detail")
        private String detail;

        public Deaths(int value, String detail) {
            this.value = value;
            this.detail = detail;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }

    public static class Recovered {
        @SerializedName("value")
        private int value;
        @SerializedName("detail")
        private String detail;

        public Recovered(int value, String detail) {
            this.value = value;
            this.detail = detail;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }
}
