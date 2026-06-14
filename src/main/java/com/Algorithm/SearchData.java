package com.Algorithm;

public class SearchData
{
    //Penggunaan Sequential Search untuk pencarian linked list
    //Berdasarkan nomor seri memberikan sebuah null jika tidak
    //ditemukan
    public static ListNode search(LinkedList list, int nomor)
    {
        ListNode nodeSekarang = list.getFirst();
        while(nodeSekarang != null)
        {
            int nomorSeri = nodeSekarang.getData().getNomorSeri();
            if(nomorSeri == nomor) return nodeSekarang;
            
            nodeSekarang = nodeSekarang.getNext();
        }

        return null;
    }
}
