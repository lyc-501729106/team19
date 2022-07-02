package pojo;


public class StationInfo {

  private int stationId;
  private String stationName;

  @Override
  public String toString() {
    return "StationInfo{" +
            "stationId=" + stationId +
            ", stationName='" + stationName + '\'' +
            '}';
  }

  public int getStationId() {
    return stationId;
  }

  public void setStationId(int stationId) {
    this.stationId = stationId;
  }

  public String getStationName() {
    return stationName;
  }

  public void setStationName(String stationName) {
    this.stationName = stationName;
  }

}
