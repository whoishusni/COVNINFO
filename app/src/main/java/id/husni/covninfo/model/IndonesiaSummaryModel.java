package id.husni.covninfo.model;

import com.google.gson.annotations.SerializedName;

public class IndonesiaSummaryModel {
    @SerializedName("positif")
    private String positifIdn;
    @SerializedName("sembuh")
    private String sembuhIdn;
    @SerializedName("meninggal")
    private String meninggalIdn;

    public String getPositifIdn() {
        return positifIdn;
    }

    public void setPositifIdn(String positifIdn) {
        this.positifIdn = positifIdn;
    }

    public String getSembuhIdn() {
        return sembuhIdn;
    }

    public void setSembuhIdn(String sembuhIdn) {
        this.sembuhIdn = sembuhIdn;
    }

    public String getMeninggalIdn() {
        return meninggalIdn;
    }

    public void setMeninggalIdn(String meninggalIdn) {
        this.meninggalIdn = meninggalIdn;
    }
}
