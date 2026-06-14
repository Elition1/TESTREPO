package com.Algorithm;

import com.SistemTicket.Ticket;

//Pembuatan kelas node untuk linked list
public class ListNode 
{
    //Atribut berisi data tiket dan node untuk data selanjutnya
    protected Ticket data;
    protected ListNode next;

    //Costructor untuk pembuatan node untuk linked list dan stack
    public ListNode()
    {
        this.data = null;
        next = null;
    }

    public ListNode(Ticket data) 
    {
        this.data = data;
        next = null;
    }

    //getter dan setter data genetic dan node selanjutnya dan ambil node
    public void setData(Ticket data)
    {
        this.data = data;
    }

    public Ticket getData()
    {
        return data;
    }

    public void setNext(ListNode next)
    {
        this.next = next;
    }

    public ListNode getNext()
    {
        return next;
    }

}