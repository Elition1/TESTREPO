package com.Algorithm;

//Penggunaan Queue untuk limitasi
//Pemrosesan yang bisa dilakukan
//agen dimana dalam dunia nyata
//ada namanya rate limiting
//Untuk melimitasi kegunaan data
//satu user sehingga bisa di gabungin
//dengan Sistem Helpdesk Program tersebut
public class Queue<T> {
    private T[] arrQueue;
    private int depan, belakang, size, kapasitas;

    //Pembuatan Queue dengan kapasitas tertentu
    public Queue(int kapasitas) 
    {
        this.kapasitas = kapasitas;
        this.arrQueue = (T[]) new Object[kapasitas];
        depan = 0;
        belakang = -1;
        size = 0;
    }

    //Memasukkan data kedalam Queue berdasarkan pointer
    //belakang
    public void enqueue(T tiket) {
        if (!isFull()) {
            belakang = (belakang + 1) % kapasitas;
            arrQueue[belakang] = tiket;
            size++;
        } else {
            System.out.println("Queue Tiket Full");
        }
    }

    //Mengeluarkan data dari Queue berdasarkan pointer
    //depan
    public T dequeue() {
        if (isEmpty()) return null;
        T tiket = arrQueue[depan];
        arrQueue[depan] = null;
        depan = (depan + 1) % kapasitas;
        size--;
        return tiket;
    }

    //Melihat isi data depan
    public T peek() {
        if (isEmpty()) return null;
        return arrQueue[depan];
    }

    //Melihat isi data berdasarkan pointer depan dengan
    //index 
    public T peekIndex(int index) {
        return arrQueue[(depan + index) % kapasitas];
    }

    //Menggantikan isi index Queue dari depan menggunakan
    //index
    public void setIndex(int index, T data) {
        arrQueue[(depan + index) % kapasitas] = data;
    }

    //Method buatan untuk InsertionQueue
    //Dimana Pointer depan dan belakang
    //Direset menjadi saat pertama kali
    //dibuat serta mengdepankan data-data
    //dalam queue ke paling depan Queue
    public void resetPointer() {
        if (isEmpty()) return;
        T[] temp = (T[]) new Object[kapasitas];
        for (int i = 0; i < size; i++) {
            temp[i] = arrQueue[(depan + i) % kapasitas];
        }
        arrQueue = temp;
        depan = 0;
        belakang = size - 1;
    }

    //Getter dan setter dan boolean Queue Penuh dan Kosong
    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == kapasitas; }
    public int getKapasitas() { return kapasitas; }
    public int getSize() { return size; }
}