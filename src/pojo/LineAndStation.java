package pojo;


public class LineAndStation {

  private int lineId;
  private int stationId;

  @Override
  public String toString() {
    return "LineAndStation{" +
            "lineId=" + lineId +
            ", stationId=" + stationId +
            '}';
  }

  public int getLineId() {
    return lineId;
  }

  public void setLineId(int lineId) {
    this.lineId = lineId;
  }

  public int getStationId() {
    return stationId;
  }

  public void setStationId(int stationId) {
    this.stationId = stationId;
  }
}
