package com.Algorithm;

import com.SistemTicket.Ticket;
import com.SistemTicket.TicketFormat;

//Pembuatan Kelas LinkedList yang 
//bertujuan untuk menyimpan riwayat dan
//mempisahkan Tiket yang sudah berstatus
//Tutup atau deadline, Serta LinkedList
//sudah dimodifikasi dimana menggunakan
//Gabungan Data struktur Queue
public class LinkedList {

    //Atribut node pertama dan akhir
    private ListNode first;
    private ListNode last;
    private int size;

    //Constructor untuk membuat linked list
    public LinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    //Method linked list dengan memasukkan node dari pertama
    public void insertFirst(Ticket data) {
        ListNode bantu = new ListNode(data);
        bantu.setNext(first);
        first = bantu;

        if (last == null) {
            last = bantu;
        }

        size++;
    }

    //Method untuk memasukkan node ke akhir linked list
    public void insertLast(Ticket data) {
        ListNode bantu = new ListNode(data);
        if (isEmpty()) {
            first = bantu;
            last = bantu;
        } else {
            last.setNext(bantu);
            last = bantu;
        }
        size++;
    }

    //Method untuk mengambil data node dari linked list dari urutan pertama
    //Serta menghapuskan
    public Ticket pop_first() {
        if (isEmpty()) {
            return null;
        }

        Ticket dataAmbil = first.getData();

        first = first.getNext();
        if (isEmpty()) {
            last = null;
        }

        size--;
        return dataAmbil;
    }

    //Method untuk mengambil data node dari linked list dari urutan akhir
    //Serta menghapuskan
    public Ticket pop_last() {
        if (isEmpty()) {
            return null;
        }
        if (first == last) {
            return pop_first();
        }

        ListNode bantu = first;
        while (bantu.getNext() != last) {
            bantu = bantu.getNext();
        }
        Ticket dataAmbil = bantu.getData();
        bantu.setNext(null);
        last = bantu;
        size++;
        return dataAmbil;
    }

    //Method mengambil dan menghapuskan data node dari linkedlist
    //Berdasarkan index
    public Ticket pop_index(int index) {
        if (isEmpty()) {
            return null;
        }
        if (index == 0) {
            return pop_first();
        }
        if (index < 0 || index >= size) {
            return null;
        }

        int iterasi = 0;

        ListNode bantu = first;
        while (iterasi < index - 1 && bantu.getNext() != null) {
            bantu = bantu.getNext();
            iterasi++;
        }
        ListNode indexDapat = bantu.getNext();
        Ticket data = indexDapat.getData();
        bantu.setNext(indexDapat.getNext());

        if (indexDapat == last) 
        {
            last = bantu;
        }

        size--;
        return data;
    }

    //Method boolean jika isi linked list kosong
    public boolean isEmpty() {
        return first == null;
    }

    //Method tampil daftar tambahan untuk 
    //specialize buat sistem tiket
    public void melihat_isi_daftar() {
        if (isEmpty()) {
            return;
        }

        ListNode bantu = first;
        while (bantu != null) {
            long waktuTersisa = bantu.getData().getWaktuTersisaAkhir();
            String waktuString;

            if (waktuTersisa <= 0) {
                waktuString = "Expired/Kena DeadLine";
            } else {
                waktuString = TicketFormat.format_waktu(waktuTersisa);
            }

            System.out.printf("""
                %-7d | %-5s | %-10s 
                """,
                    bantu.getData().getNomorSeri(),
                    waktuString,
                    bantu.getData().getStatus()
            );
            bantu = bantu.getNext();
        }
        System.out.println("===============================");
        System.out.print("Pilihan : ");
    }

    //Method untuk mengisi node dari index urutan tertentu
    public boolean insertAfter(int index, Ticket data) {
        if (isEmpty()) {
            return false;
        }

        ListNode bantu = first;
        for (int i = 0; i < index; i++) {
            if (bantu.getNext() == null) {
                return false;
            }
            bantu = bantu.getNext();
        }

        ListNode next = new ListNode(data);
        next.setNext(bantu.getNext());
        bantu.setNext(next);
        if (next.getNext() == null) {
            last = next;
        }
        size++;
        return true;
    }

    //Method untuk menghapus node berdasarkan dari data parameter dari linked list
    public boolean remove(Ticket data) {
        if (isEmpty()) 
        {
            return false;
        }

        if (first.getData().equals(data)) {
            first = first.getNext();
            if (first == null) 
            {
                last = null; 
            }
                size--;
            return true;
        }
        ListNode bantu = first;
        while (bantu.getNext() != null) {
            if (bantu.getNext().getData().equals(data)) {
                if (bantu.getNext() == last) {
                    last = bantu;
                }
                bantu.setNext(bantu.getNext().getNext());
                size--;
                return true;
            }
            bantu = bantu.getNext();
        }
        return false;
    }

    //Getter algoritma linked list
    public ListNode getFirst() {
        if (isEmpty()) {
            return null;
        }
        return first;
    }

    public void setFirst(ListNode first) {
        this.first = first;
    }

    public int getSize() {
        return size;
    }
}
