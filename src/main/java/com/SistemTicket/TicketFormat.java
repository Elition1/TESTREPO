package com.SistemTicket;

//Kelas untuk memformat waktu nanotime untuk
//sesuai dengan waktu standar dunia nyata
public class TicketFormat 
{
    public static String format_waktu(long waktu)
    {
        if(waktu <= 0) return "0";

        long totalDetik = waktu / 1_000_000_000L;
        long menit = totalDetik / 60;
        long detik = totalDetik % 60;

        return String.format("%02d menit %02d detik", menit, detik);
    }
}
