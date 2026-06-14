package com.Data;

import com.SistemTicket.*;

// Kelas untuk otomatis Pembuatan Data yang dikasih
// Bisa Diubah untuk pengetesan
public class DataTest {

    public static final int MAX_USER = 20;
    public static Ticket[] testUser = new Ticket[MAX_USER];
    public static CreateUser[] user = new CreateUser[MAX_USER];
    public static String nama = "Person";
    public static String email = "Email@gmail.com";

    public static void isi_data_user() {
        for (int i = 0; i < user.length; i++) {
            user[i] = new CreateUser(nama + DataUser.userIdCount, DataUser.userIdCount + "_" + email);
        }
    }

    public static void buat_ticket() {
        for (int i = 0; i < testUser.length; i++) {
            Ticket tiket = CreateTicket.create_tiket(user[i]);
            testUser[i] = tiket;

            int randomTime = CreateTicket.randomProcessGenerator.nextInt(1000);

            try {
                Thread.sleep(randomTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
