package az.turing.userappinspringboot.domain.repository;

public final class UserQuery {
    public final static String findAll = "select*from users";
    public final static String insert = "insert into users (username,userpassword,userstatus) values(?,?,?)";
    public final static String update = "update users set username=?,userpassword=?,userstatus=? where userid=?";

}
