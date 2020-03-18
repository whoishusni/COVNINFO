package id.husni.covninfo.model;

import com.google.gson.annotations.SerializedName;

public class IdnSummaryModel {
    @SerializedName("lastUpdate")
    private String lastUpdate;
    @SerializedName("confirmed")
    private IdnConfirmed idnConfirmed;
    @SerializedName("recovered")
    private IdnRecovered idnRecovered;
    @SerializedName("deaths")
    private IdnDeaths idnDeaths;

    public IdnSummaryModel(String lastUpdate, IdnConfirmed idnConfirmed, IdnDeaths idnDeaths, IdnRecovered idnRecovered) {
        this.lastUpdate = lastUpdate;
        this.idnConfirmed = idnConfirmed;
        this.idnDeaths = idnDeaths;
        this.idnRecovered = idnRecovered;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public IdnConfirmed getIdnConfirmed() {
        return idnConfirmed;
    }

    public void setIdnConfirmed(IdnConfirmed idnConfirmed) {
        this.idnConfirmed = idnConfirmed;
    }

    public IdnDeaths getIdnDeaths() {
        return idnDeaths;
    }

    public void setIdnDeaths(IdnDeaths idnDeaths) {
        this.idnDeaths = idnDeaths;
    }

    public IdnRecovered getIdnRecovered() {
        return idnRecovered;
    }

    public void setIdnRecovered(IdnRecovered idnRecovered) {
        this.idnRecovered = idnRecovered;
    }

    public class IdnConfirmed {
        @SerializedName("value")
        private int value;
        @SerializedName("detail")
        private String detail;

        public IdnConfirmed(int value, String detail) {
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

    public class IdnDeaths {
        @SerializedName("value")
        private int value;
        @SerializedName("detail")
        private String detail;

        public IdnDeaths(int value, String detail) {
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

    public class IdnRecovered {
        @SerializedName("value")
        private int value;
        @SerializedName("detail")
        private String detail;

        public IdnRecovered(int value, String detail) {
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
