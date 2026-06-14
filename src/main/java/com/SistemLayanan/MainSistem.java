package com.SistemLayanan;

import java.util.Scanner;

import com.SistemTicket.*;
import com.Algorithm.*;
import com.Data.*;

//Kelas Main untuk menjalankan Projek File
//Sistem HelpDesk
public class MainSistem 
{
    static boolean berjalan = true;
    static Scanner input = new Scanner(System.in);
    //Pembuatan LinkedList untuk mempisahkan Ticket yang
    //tutup dan yang deadline
    static LinkedList listDeadLine = new LinkedList();
    static LinkedList listSelesai = new LinkedList();
    static int counter = 0;

    //Pembuatan Tampilan Utama Sistem Tiket
    public static void main_tampilan()
    {
        System.out.println("========================");
        System.out.println("\n=== SISTEM SIMULASI TICKETING ===");
        System.out.println("1. Mulai Proses Tiket");
        System.out.println("2. Tampilkan Riwayat");
        System.out.println("3. Perbaiki Tiket Bermasalah");
        System.out.println("4. Exit"); 
        System.out.println("========================");
        System.out.print("Pilih Menu: ");
    }

    //Pembuatan Tampilan Riwayat Sistem Tiket
    public static void riwayat_tampilan()
    {
        System.out.println("========================");
        System.out.println("Pilihan Tampilan Riwayat");
        System.out.println("1. Semua");
        System.out.println("2. Satu");
        System.out.println("========================");
        System.out.print("Pilihan : ");
        int pilih = input.nextInt();
        input.nextLine();

        //Case Pilihan untuk Tampil Riwayat
        switch (pilih) {
            case 1:
                Riwayat.tampilRiwayat();
                input.nextLine();
                break;
            case 2:
                System.out.println("Input nomor seri Tiket :");
                int nomor = input.nextInt();
                input.nextLine();
                Riwayat.tampil(nomor);
                input.nextLine();
                break;
            default:
                System.out.println("Pilihan Salah, Kembali Ke main Tampilan");
                return;
        }
    }

    //Otomatis Pembuatan Data untuk pengetesan
    public static void test_sistem ()
    {   
        DataUser.userIdCount = 0;
        DataUser.serialTicketCount = 0;
        DataTest.isi_data_user();
        DataTest.buat_ticket();
    }

    //Opsi 1 untuk memulaikan sistem ticket
    public static void mulai_sistem()
    {
        test_sistem();
        //Pembuatan Queue baru untuk mengisi data baru
        if (CreateTicket.queueTiket == null) 
        {
            CreateTicket.queueTiket = new Queue<>(10);
        }

        //Membuat referensi ke ProsesTIket dari queueTiket
        Queue<Ticket> ProsesTiket = CreateTicket.queueTiket;

        //Output Queue Kosong jika belum ada tiket yang ada
        if(ProsesTiket.isEmpty())
        {
            System.out.println("Queue Tidak Ada Tiket");
        }

        //Menampilkan Ticket yang ada untuk melakukan pemrosesan
        System.out.printf("%-8s | %-15s | %-20s | %-10s%n",
        "No.Seri",
        "User",
        "Waktu Tersisa",
        "Status"
        );
        System.out.println("------------------------------------------");
        while(!ProsesTiket.isFull() && counter < DataTest.testUser.length)
        {
            //Pengambilan Tiket dari DataTest
            Ticket tiket = DataTest.testUser[counter];

            if (tiket == null)
            { 
                counter++; 
                continue;
            }

            //Menjaga Riwayat DataTest 
            counter++;

            //Method penambahan tiket kedalam queue
            CreateTicket.add_tiket(tiket);

            //Mengubah status tiket menjadi Sedang Proses
            tiket.setStatus(Ticket.SEDANGPROSES);
        }
        //Penyortiran Queue untuk pengupayaan proses dari
        //Waktu tersisa terpendek
        InsertionQueue.sortQueue(ProsesTiket);

        //Penampilan status tiket sekarang serta waktu tersisa
        //untuk proses
        for (int i = 0; i < ProsesTiket.getSize(); i++) {
            Ticket tiketAmbil = ProsesTiket.peekIndex(i);
            System.out.printf("%-8d | %-15s | %-20s | %-10s%n",
                tiketAmbil.getNomorSeri(),
                tiketAmbil.getUser().getName(),
                TicketFormat.format_waktu(tiketAmbil.getWaktuTersisa()),
                tiketAmbil.getStatus()
            );
        }
        System.out.println("------------------------------------------");

        //Konfirmasi Melakukan pemrosesan
        System.out.print("Mulai Proses (y/n) ?");
        String hasil = input.nextLine();

        switch (hasil) {
            //Jika ketik "y" berarti melakukan pemrosesan
            case "y":
                int countSelesai = 0;
                int countDeadLine = 0;
                while (!CreateTicket.queueTiket.isEmpty()) 
                {
                    Ticket TicketNow = CreateTicket.queueTiket.dequeue();
                    Ticket processed = CreateTicket.process_ticket(TicketNow);
                    if(processed == null)
                    {
                        continue;
                    }
                    else if(processed.getStatus().equals("Tutup"))
                    {
                        listSelesai.insertFirst(processed);
                        countSelesai++;
                    }
                    else if(processed.getStatus().equals("Kena DeadLine"))
                    {
                        listDeadLine.insertFirst(processed);
                        countDeadLine++;
                    }
                    
                    Riwayat.tambahRiwayat(processed);
                }             
                System.out.println("Tiket Selesai = " + countSelesai);
                System.out.println("Tiket Kena DeadLine  = " + countDeadLine);
                  
                break;
            //Jika ketik "n" maka kembali ke Tampilan utama
            //Tetapi queue dibiarkan untuk pemrosesan kembali
            case "n":
                return;
            default:
                System.out.println("Input Tidak Sesuai Kembali Ke Main Tampilan");
                return;
        }
        
    }

    // Method Pemulihan tiket untuk memulihkan tiket status deadline
    public static void pemulihan_tiket()
    {
        //Pemilihan Ticket untuk pemulihan berdasarkan nomor seri
        System.out.println("Pilih Salah Satu Tiket untuk Pemulihan : ");
        listDeadLine.melihat_isi_daftar();
        int nomorSeri = input.nextInt();
        input.nextLine();

        //Pembuatan tiket objek untuk pemulihan
        Ticket pemulihanTiket = null;

        //Mencari Tiket berdasarkan pemulihan user
        ListNode lookUpTiket = SearchData.search(listDeadLine, nomorSeri);

        //Mengkasih False jika tidak ditemukan tiket tersebut
        if(lookUpTiket == null)
        {
            System.out.println("Tiket tidak ditemukan");
            return;
        }
        //Jika dapat, maka melakukan pemulihan tiket
        else
        {
            pemulihanTiket = CreateTicket.pemulihan_ticket(lookUpTiket.getData());
        }

        if (pemulihanTiket == null) return;

        //Update riwayat tiket tersebut dari daftar riwayat
        Riwayat.updateRiwayat(pemulihanTiket);

        //Jika Pemulihan berhasil, maka di hapuskan dari list deadline dan
        //dimasukkan kedalam list selesai
        if (pemulihanTiket.getStatus().equals("Tutup"))
        {
            System.out.println("Tiket Berhasil Dipulihkan masuk daftar Riwayat");
            listDeadLine.remove(pemulihanTiket);
            listSelesai.insertFirst(pemulihanTiket);
        }
        //Jika gagal, maka masuk kedalam status awal
        else
        {
            System.out.println("Tiket Gagal Dipulihkan, kena DeadLine");
        }
    }

    //Method untuk keluar dari sistem HelpDesk
    public static boolean keluar_sistem()
    {
        return berjalan = false;
    }

    //Entry point untuk menjalankan program java
    public static void main(String[] args) 
    {
        //Pengawalan Berjalannya Program
        while (berjalan)
        {
            //Pilihan Untuk melakukan operasi
            main_tampilan();
            int pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    mulai_sistem();
                    break;
                case 2:
                    riwayat_tampilan();
                    break;
                case 3:
                    pemulihan_tiket();
                    break;
                case 4:
                    berjalan = keluar_sistem();
                    break;
                default:
                    break;
            }
        }

        input.close();
    }
}
