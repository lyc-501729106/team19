package pojo;


public class LineInfo {

  private int lineId;
  private String lineName;
  private String cost;
  private String lineInfo;
  private String departureTime;
  private String collectionTime;
  private int departureInterval;

  @Override
  public String toString() {
    return "LineInfo{" +
            "lineId=" + lineId +
            ", lineName='" + lineName + '\'' +
            ", cost='" + cost + '\'' +
            ", lineInfo='" + lineInfo + '\'' +
            ", departureTime='" + departureTime + '\'' +
            ", collectionTime='" + collectionTime + '\'' +
            ", departureInterval=" + departureInterval +
            '}';
  }


  public int getLineId() {
    return lineId;
  }

  public void setLineId(int lineId) {
    this.lineId = lineId;
  }

  public String getLineName() {
    return lineName;
  }

  public void setLineName(String lineName) {
    this.lineName = lineName;
  }


  public String getCost() {
    return cost;
  }

  public void setCost(String cost) {
    this.cost = cost;
  }


  public String getLineInfo() {
    return lineInfo;
  }

  public void setLineInfo(String lineInfo) {
    this.lineInfo = lineInfo;
  }

  public String getDepartureTime() {
    return departureTime;
  }

  public void setDepartureTime(String departureTime) {
    this.departureTime = departureTime;
  }

  public String getCollectionTime() {
    return collectionTime;
  }

  public void setCollectionTime(String collectionTime) {
    this.collectionTime = collectionTime;
  }

  public int getDepartureInterval() {
    return departureInterval;
  }

  public void setDepartureInterval(int departureInterval) {
    this.departureInterval = departureInterval;
  }
}
