package com.Data;

// Kelas untuk Memastikan Nomor Seri Tiket adalah Unik
public class DataUser 
{
    // Atribut Tiket dan User untuk melihat angka nomor sekarang
    public static int serialTicketCount = 0;
    public static int userIdCount = 0;

    public static void add_id_count()
    {
        userIdCount++;
    }

    public static void add_id_ticket_count()
    {
        serialTicketCount++;
    }
}
