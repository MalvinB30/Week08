package Week08;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Item>    ListOfItems    = new ArrayList<>();
    static ArrayList<Payment2> ListOfPayments = new ArrayList<>();
    static Scanner s = new Scanner(System.in);

    public static void seedData() {
        if (ListOfItems.isEmpty()) {
            ListOfItems.add(new Item("Kulkas", "Elektronik", 4_800_000));
            ListOfItems.add(new Item("TV",     "Elektronik", 1_280_000));
            ListOfItems.add(new Item("Laptop", "Komputer",    6_000_000));
            ListOfItems.add(new Item("PC",     "Komputer",   12_000_000));
        }
    }

    public static void printItem(Item item) {
        System.out.println("Nama   : " + item.getName());
        System.out.println("Tipe   : " + item.getType());
        System.out.println("Harga  : " + item.getPrice());
    }

    public static void printTransaction(Payment2 p, int num) {
        System.out.println("No                 : " + num);
        System.out.println("Nama               : " + p.getItemName());
        System.out.println("Tipe               : " + p.getItem().getType());
        System.out.println("Status             : " + p.getStatus());
        System.out.println("Sisa Pembayaran    : " + p.getRemainingAmount());
        System.out.println("---------------------------------");
    }

    public static void main(String[] args) {
        seedData();
        int opt;

        do {
            System.out.println("---Program Toko Elektronik---");
            System.out.println("1. Pesan Barang");
            System.out.println("2. Lihat Pesanan");
            System.out.println("0. Keluar");
            System.out.print("Pilih : ");
            while (!s.hasNextInt()) { s.next(); System.out.print("Pilih : "); }
            opt = s.nextInt(); s.nextLine();

            if (opt == 1) {
                System.out.println("---Daftar Barang---");
                for (int i = 0; i < ListOfItems.size(); i++) {
                    System.out.println("No : " + (i + 1));
                    printItem(ListOfItems.get(i));
                    System.out.println("---------------------------------");
                }

                System.out.print("Pilih : ");
                while (!s.hasNextInt()) { s.next(); System.out.print("Pilih : "); }
                int id = s.nextInt() - 1; s.nextLine();

                if (id < 0 || id >= ListOfItems.size()) {
                    System.out.println("Pilihan tidak valid.");
                    continue;
                }

                Item chosenItem = ListOfItems.get(id);
                printItem(chosenItem);

                System.out.println("---Tipe pembayaran---");
                System.out.println("1. Cash");
                System.out.println("2. Credit");
                System.out.print("Pilih : ");
                while (!s.hasNextInt()) { s.next(); System.out.print("Pilih : "); }
                int payOpt = s.nextInt(); s.nextLine();

                if (payOpt == 1) {
                    Payment2 newPayment = new Cash(chosenItem);
                    System.out.print("Bayar (Y/N): ");
                    String confirm = s.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        int amount = newPayment.pay();
                        System.out.println("Harga Pembayaran : " + amount);
                        System.out.println("Bayar : " + amount);
                        System.out.println("Transaksi telah dibayar lunas");
                    } else {
                        System.out.println("Transaksi telah disimpan");
                    }
                    ListOfPayments.add(newPayment);

                } else if (payOpt == 2) {
                    int install;
                    do {
                        System.out.print("Lama Cicilan (3/6/9/12): ");
                        while (!s.hasNextInt()) { s.next(); System.out.print("Lama Cicilan (3/6/9/12): "); }
                        install = s.nextInt();
                    } while (install != 3 && install != 6 && install != 9 && install != 12);
                    s.nextLine();

                    Payment2 newPayment = new Credit(chosenItem, install);
                    int installmentAmount = newPayment.pay();
                    System.out.println("Harga Pembayaran : " + installmentAmount);
                    System.out.println("Bayar : " + installmentAmount);
                    System.out.println("Transaksi telah dibayar");
                    ListOfPayments.add(newPayment);
                } else {
                    System.out.println("Pilihan pembayaran tidak valid.");
                }

            } else if (opt == 2) {
                if (ListOfPayments.isEmpty()) {
                    System.out.println("Belum ada transaksi.");
                    System.out.println("---------------------------------");
                    continue;
                }

                for (int i = 0; i < ListOfPayments.size(); i++) {
                    printTransaction(ListOfPayments.get(i), i + 1);
                }

                System.out.print("Pilih No Transaksi : ");
                while (!s.hasNextInt()) { s.next(); System.out.print("Pilih No Transaksi : "); }
                int transId = s.nextInt() - 1; s.nextLine();

                if (transId < 0 || transId >= ListOfPayments.size()) {
                    System.out.println("Transaksi tidak ditemukan.");
                    continue;
                }

                Payment2 p = ListOfPayments.get(transId);

                System.out.println("No                 : " + (transId + 1));
                System.out.println("Nama               : " + p.getItemName());
                System.out.println("Tipe               : " + p.getItem().getType());
                System.out.println("Status             : " + p.getStatus());
                System.out.println("Sisa Pembayaran    : " + p.getRemainingAmount());

                if (p.isPaidOff()) {
                    System.out.println("Transaksi telah lunas");
                    continue;
                }

                int paymentAmount;
                if (p instanceof Cash) {
                    paymentAmount = p.getRemainingAmount();
                } else if (p instanceof Credit c) {
                    paymentAmount = p.getItem().getPrice() / c.getMaxInstallmentAmount();
                } else {
                    paymentAmount = p.getRemainingAmount(); // default
                }

                System.out.println("Harga Pembayaran : " + paymentAmount);
                System.out.print("Bayar : ");
                while (!s.hasNextInt()) { s.next(); System.out.print("Bayar : "); }
                int bayar = s.nextInt(); s.nextLine();

                if (bayar == paymentAmount) {
                    p.pay();
                    System.out.println(p.isPaidOff() ? "Transaksi telah dibayar lunas"
                                                     : "Transaksi telah dibayar");
                } else {
                    System.out.println("Pembayaran gagal, jumlah tidak sesuai.");
                }

            } else if (opt == 0) {
                System.out.println("Terima Kasih");
            } else {
                System.out.println("Salah Input");
            }
        } while (opt != 0);
    }
}
