package com.SistemLayanan;
import com.Algorithm.*;
import com.SistemTicket.*;

//Kelas untuk menampilkan riwayat Ticket yang sudah
//diproses
public class Riwayat {
    //Pembuatan Linkedlist Riwayat tiket yang sudah diproses
    public static LinkedList dataRiwayat = new LinkedList();

    //tambah tiket yang sudah proses ke LinkedList
    public static void tambahRiwayat(Ticket t) {
        if (t == null) return;
        dataRiwayat.insertFirst(t);
    }

    //Method update riwayat tiket yang dikasih
    public static void updateRiwayat(Ticket tiket) {
        if (tiket == null) return;

        //Mengambil node linked list riwayat dari pertama
        ListNode bantu = dataRiwayat.getFirst();
        
        //Iterasi node linked list sampai ketemu tiket tersebut
        while (bantu != null) 
        {
            Ticket data = (Ticket) bantu.getData();
            
            // Mencari tiket tersebut dan menggantikan dengan yang baru
            if (data.getNomorSeri() == tiket.getNomorSeri()) 
            {
                bantu.setData(tiket);
                return;
            }
            bantu = bantu.getNext();
        }
    }

    //tampil 1 tiket berdasarkan nomor seri yang diberikan
    public static void tampil(int nomorSeri) 
    {
        ListNode dataCari = SearchData.search(dataRiwayat, nomorSeri);
        if(dataCari == null)
        {
            System.out.println("Hasil Pencarian Tidak Ditemukan");
            return;
        }
        else
        {    
            Ticket dataAmbil = dataCari.getData();
            User user = dataCari.getData().getUser();
            System.out.println("===== RIWAYAT TIKET =====");
            System.out.println("--- Info User ---");
            System.out.println("Nama    : " + user.getName());
            System.out.println("Email   : " + user.getEmail());
            System.out.println("ID User : " + ((CreateUser) user).getIdUser());
            System.out.println("--- Info Tiket ---");
            System.out.println("Nomor Seri : " + dataAmbil.getNomorSeri());
            System.out.println("Status     : " + dataCari.getData().getStatus());
            System.out.println("Deadline   : " + TicketFormat.format_waktu(dataAmbil.getDeadLine()));
            System.out.println("Dibuat     : " + TicketFormat.format_waktu(dataAmbil.getWaktuDibuat()));
            System.out.println("Waktu Tersisa : " + TicketFormat.format_waktu(dataAmbil.getWaktuTersisaAkhir()));
        }
    }

    // method untuk menampilkan semua tiket yang sudah diproses
    // yang disusun berdasarkan waktu deadline terpendek 
    public static void tampilRiwayat() {
        InsertionLinkedList.sortList(dataRiwayat);
        if (dataRiwayat.isEmpty()) {
            System.out.println("[RIWAYAT] Belum ada riwayat.");
            return;
        }


        System.out.println("===== RIWAYAT TIKET (Diurutkan by Waktu Tersisa) =====");

        ListNode iterasi = dataRiwayat.getFirst();

        while (iterasi != null)
        {
            long detikTersisa = iterasi.getData().getWaktuTersisa() / 1_000_000_000L;

            System.out.println("--------------------------------------------------");
            System.out.println("Nomor Seri        : " + iterasi.getData().getNomorSeri());
            System.out.println("Nama User         : " + iterasi.getData().getUser().getName());
            System.out.println("Status            : " + iterasi.getData().getStatus());

            if (detikTersisa <= 0) {
                System.out.println("Waktu Tersisa     : EXPIRED / MELEWATI DEADLINE");
            } else {
                System.out.println("Waktu Tersisa     : " + TicketFormat.format_waktu(iterasi.getData().getWaktuTersisaAkhir()));
            }

            iterasi = iterasi.getNext();
        }

        System.out.println("==================================================");
    }
} 

