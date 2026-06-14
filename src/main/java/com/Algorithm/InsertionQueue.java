package com.Algorithm;

import com.SistemTicket.Ticket;

//Kelas untuk melakukan penyortiran
//Terhadap Queue yang dikasih dimana
//Penggunaan method peek dan peek index
//Untuk melakukan perbandingan terhadap
//Nilai data tersebut
public class InsertionQueue 
{
    public static void sortQueue(Queue<Ticket> arrayTiket) 
    {
        if (arrayTiket == null) 
        {
            System.out.print("Queue Belum Dibuat");
            return;
        }

        if (arrayTiket.isEmpty()) 
        {
            System.out.println("Queue belum ada tiket");
            return;
        }

        int size = arrayTiket.getSize();

        for (int i = 0; i < size; i++) 
        {
            //Mengambil data tiket dari index yang dilihat
            Ticket tiket = arrayTiket.peekIndex(i);
            int index = i - 1;

            //Looping insertion sort untuk penyortiran
            while (index >= 0) 
            {
                //Mengambil tiket dari index untuk melakukan perbandingan
                Ticket tiketBandingkan = arrayTiket.peekIndex(index);
                //Jika Nilai tiket yang dibandingkan lebih besar dari sebelumnya
                //Maka berhenti looping dan ke iterasi selanjutnya
                if (tiket.getWaktuTersisa() <= tiketBandingkan.getWaktuTersisa()) 
                {
                    break;
                }
                arrayTiket.setIndex(index + 1, tiketBandingkan);
                index--;
            }

            arrayTiket.setIndex(index + 1, tiket);
        }

        //Reset pointer setiap melakukan Sorting
        arrayTiket.resetPointer();
    }
}
