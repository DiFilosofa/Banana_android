package vn.quankundeptrai.banana.data.models.favoriteLocation;

/**
 * Created by TQN on 2/12/18.
 */

public class FavoriteLocationItemModel {
    private String district;
    private String detail;
    private boolean isFavorite = false;

    public FavoriteLocationItemModel(String district, String detail) {
        this.district = district;
        this.detail = detail;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
