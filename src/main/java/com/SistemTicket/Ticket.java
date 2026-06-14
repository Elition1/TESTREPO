package com.SistemTicket;

//Kelas inti untuk sistem proses
//Dengan Menggunakan nanotime() untuk
//Melihat waktu Objek
public class Ticket {
    //Atribut untuk mnegkategorisasi
    //Status Objek Tiket
    public static final int TERBUKA = 0;
    public static final int SEDANGPROSES = 1;
    public static final int TUTUP = 2;
    public static final int DEADLINE = 3;   
    
    //Atribut yang butuhkan untuk sistem
    //Dengan User objek tertanam dalam
    //Satu Tiket
    private int nomorSeri;
    private User user;
    private long waktuDibuat;
    private long deadLine;
    private long waktuTersisaAkhir;
    private String status;
    
    //Pembuatan Objek Tiket
    public Ticket(int nomorSeri, User user, long deadLine) {
        this.nomorSeri = nomorSeri;
        this.user = user;
        this.status = "";
        this.waktuDibuat = System.nanoTime();
        this.deadLine = deadLine + this.waktuDibuat;
    }
    
    public long getWaktuTersisaAkhir() {
        return waktuTersisaAkhir;
    }

    public void setWaktuTersisaAkhir(long waktuTersisaAkhir) {
        this.waktuTersisaAkhir = waktuTersisaAkhir;
    }
    
    public int getNomorSeri() {
        return nomorSeri;
    }

    public void setNomorSeri(int nomorSeri) {
        this.nomorSeri = nomorSeri;
    }

    public long getDeadLine() {
        return deadLine;
    }

    //dibuat untuk mencetak waktu sekarang yang tersisa saat
    //Pemrosesan tiket
    public long simpanWaktuTersisaAkhir()
    {
        return this.waktuTersisaAkhir = getWaktuTersisa();
    }

    public long getWaktuTersisa() {
        return this.deadLine - System.nanoTime();
    }

    public void setDeadLine(long deadLine) {
        this.deadLine = deadLine;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getStatus()
    {
        return status;
    }

    //Cara kategorikan Status adalah
    //Mengkasih parameter integer untuk 
    //set status objek
    public void setStatus(int set)
    {
        switch (set){
            case TERBUKA:
                this.status = "Terbuka";
                break;
            case SEDANGPROSES:
                this.status = "Sedang Proses";
                break;
            case TUTUP:
                this.status = "Tutup";
                break;
            case DEADLINE: 
                this.status = "Kena DeadLine";
                break;
        }
    }

    public long getWaktuDibuat() {
        return waktuDibuat;
    }

    public void setWaktuDibuat(long waktuDibuat) {
        this.waktuDibuat = waktuDibuat;
    }
}
