package pojo;


public class AdminInfo {

    private int adminId;
    private String users;
    private String pwd;
    private String sex;
    private String phone;

    public AdminInfo() {
    }

    public AdminInfo(int adminId, String users, String pwd, String sex, String phone) {
        this.adminId = adminId;
        this.users = users;
        this.pwd = pwd;
        this.sex = sex;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return  "{"+
                "\"adminId\":\"" + adminId + '\"' +","+
                "\"users\":\"" + users + '\"' +","+
                "\"pwd\":\"" + pwd + '\"' +","+
                "\"sex\":\"" + sex + '\"' +","+
                "\"phone\":\"" + phone + '\"'+
                "}"
                ;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }


    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
