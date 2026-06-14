package com.SistemTicket;
import java.util.Random;

import com.Algorithm.Queue;
import com.Data.DataUser;

//Kelas untuk membuat dan menambahkan
//Tiket kedalam queue
//Cara Logika pemrosesan adalah
//menggunakan boolean untuk proses
//Tiket apakah gagal atau tidak\
//serta mempunyai method pemulihan
//tiket yang udah terkena deadline 
//Dimana diberikan deadline baru
//Untuk pemrosesan tertentu
public class CreateTicket {
    public static Random randomProcessGenerator = new Random(); 
    public static Queue<Ticket> queueTiket; //Pembuatan Queue

    //Method Membuat tiket baru dengan User
    public static Ticket create_tiket(User user) {
        if (user == null) {
            System.out.println("Tidak ada User");
            return null;
        }

        // Waktu deadLine per tiket
        long deadLine = 60_000_000_000L;
        int seri = DataUser.serialTicketCount;
        DataUser.add_id_ticket_count();

        //Objek Tiket Baru
        Ticket ticket = new Ticket(seri, user, deadLine);

        //Menjadi tiket terbuka untuk proses
        ticket.setStatus(Ticket.TERBUKA);

        return ticket;
    }

    //Method menambahkan Tiket kedalam Queue
    public static void add_tiket(Ticket tiket) {
        if(tiket.getWaktuTersisa() < 0)
        {
            return;
        }

        if(tiket.getUser() != null)
        {
            queueTiket.enqueue(tiket);
        }
    }

    //Method pemrosesan tiket
    public static Ticket process_ticket(Ticket ticket)
    {   
        if(ticket == null) return null;

        Ticket ticketProses = ticket;
        
        //Penggunaan probabilitas 50 % berhasil
        //apakah berhasil diproses atau tidak
        boolean randomProcess = randomProcessGenerator.nextBoolean();

        //Penggunaan Proses for loop kosong untuk 
        //memperlihatkan proses asli dalam
        //dunia nyata
        long process = Math.abs(randomProcessGenerator.nextLong()) % 100_000_000_000L;

        for (long i = 0; i < process; i++) {}
        
        //Mencetak waktu tersisa pemrosesan tiket
        ticketProses.simpanWaktuTersisaAkhir();

        //Kasih Status jika tiket berhasil atau tidak
        if (randomProcess) 
        {
            ticketProses.setStatus(Ticket.DEADLINE);
        } 
        else 
        {
            ticketProses.setStatus(Ticket.TUTUP);
        }
        return ticketProses;
    }

    // Method pemulihan satu tiket
    public static Ticket pemulihan_ticket(Ticket ticket) {
        if (ticket == null) return null;
        
        //Memberikan deadline baru untuk proses ulang
        long pemulihan = 60_000_000_000L;
        ticket.setDeadLine(System.nanoTime() + pemulihan);
        
        //Pemrosesan kembali tiket
        return process_ticket(ticket);
    }
}
