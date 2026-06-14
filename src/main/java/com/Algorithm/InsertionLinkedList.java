package com.Algorithm;

//Kelas LinkedList statis
//menggunakan InsertionSort untuk penyortir isi data Riwayat
public class InsertionLinkedList 
{
    //Method untuk memberikan sturuktur data linked list untuk sorting
    public static void sortList(LinkedList dataList)
    {
        if(dataList == null || dataList.getFirst() == null || dataList.getFirst().getNext() == null)
        {
            System.out.println("Linked List belum ada");
            return;
        }

        //Membuat Node kosong untuk menjadi perbandingan nilai
        //Dari linked list pertama
        ListNode bantu = new ListNode();
        bantu.next = dataList.getFirst();

        //Mengambil Node selanjutnya dari node pertama
        ListNode nodeSekarang = dataList.getFirst().getNext();

        //Membuat node pertama yaitu bantu membuat node
        //selanjutnya menjadi null
        bantu.getNext().next = null; 

        //Looping terjadi jika nodeSekarang tidak null
        while (nodeSekarang != null)
        {
            //Mengambil node sekarang dari linked list sebelumnya dan
            //Mengambul node selanjutnya dan ditampung ke node lanjut
            ListNode nodeLanjut = nodeSekarang.getNext();

            //Mengambil node bantu yang sebelumnya dan ditampung ke
            //NodeSebelum
            ListNode nodeSebelum = bantu;
            
            //Melakukan Perbandingan nilai dengan node sebelumnya berdasarkan
            //Perekaman waktu tersisa saat pemrosesan tiket
            while(nodeSebelum.getNext() != null && 
                nodeSebelum.getNext().getData().getWaktuTersisaAkhir() > nodeSekarang.getData().getWaktuTersisaAkhir())
            {
                //Iterasi sampai nilai deadline lebih besar dengan nodeSekarang
                nodeSebelum = nodeSebelum.getNext();
            }

            //Menyisipkan Node yang diambil kedalam NodeSekarang
            //Yaitu linked list yang udah kesort
            nodeSekarang.next = nodeSebelum.getNext();
            nodeSebelum.next = nodeSekarang;

            //Melanjutkan node selanjutnya dari nodeSekarang
            //Untuk perbandingan berlanjut
            nodeSekarang = nodeLanjut;
        }

        //Mengganti linked list sebelumnya ke 
        //linked list yang sudah kesort
        //Dari nodesebelumnya
        dataList.setFirst(bantu.next);
    }
}
