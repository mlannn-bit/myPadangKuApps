import model.Transaksi;
import service.TransaksiService;
import model.Kasir;
import model.Menu;
import model.Admin;
import model.JuruMasak;
import model.User;

public class Main {

    public static void main(String[] args) {

        User admin1 = new Kasir("KontolGaming", "12345");
        User admin2 = new Kasir("KontolKuda", "12345");
        User admin3 = new JuruMasak("KontolNgaceng", "12345");

      if (admin1 instanceof Admin) {
        System.out.println("Ini akun admin");
      } else {
        System.out.println("Ini bukan akun admin");
      }


      
      if (admin2 instanceof Kasir) {
        System.out.println("ini akun Kasir");
      }
      
      if (admin3 instanceof JuruMasak) {
        System.out.println("Ini akun JuruMasak");
      }
    }
}
