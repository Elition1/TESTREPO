package com.SistemTicket;
import com.Data.DataUser;

//Class Pewarisan dari Class User
//Untuk Memberikan idUser kepada Objek
public class CreateUser extends User
{
    //Atribut unik untuk setiap objek User
    private int idUser;

    public CreateUser(String name, String email)
    {
        int id = DataUser.userIdCount;
        DataUser.add_id_count();
        super(name, email);
        this.idUser = id;
    } 

    //Untuk "Menghapus" User dengan menggunakan
    //Null dan -1 
    public void delete_user()
    {    
        this.name = null;
        this.email = null;
        this.idUser = -1;
    }

    public int getIdUser() 
    {
        return idUser;
    }
}
