package id.husni.covninfo.model;

import com.google.gson.annotations.SerializedName;

public class ModelData {
    @SerializedName("confirmed")
    private int confirmed;
    @SerializedName("deaths")
    private int death;
    @SerializedName("recovered")
    private int recovered;

    public ModelData(int confirmed, int death, int recovered) {
        this.confirmed = confirmed;
        this.death = death;
        this.recovered = recovered;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getDeath() {
        return death;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }
}
