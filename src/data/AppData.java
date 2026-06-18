package data;

import com.google.gson.reflect.TypeToken;
import model.BahanBaku;
import model.Menu;
import model.User;
import service.AdminService;
import service.ProduksiService;
import service.RegisterAndLoginService;
import service.TransaksiService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AppData {

    public static final String USER_FILE =
            "src/data/files/users.json";

    public static final String MENU_FILE =
            "src/data/files/menu.json";

    public static final String BAHAN_FILE =
            "src/data/files/bahan.json";

    public static RegisterAndLoginService authService =
            new RegisterAndLoginService();

    public static AdminService adminService =
            new AdminService();

    public static ProduksiService produksiService =
            new ProduksiService();

    public static TransaksiService transaksiService =
            new TransaksiService();

    public static void saveAll() {

        JsonUtil.save(
                USER_FILE,
                authService.getUsers()
        );

        JsonUtil.save(
                MENU_FILE,
                adminService.getMenuList()
        );

        JsonUtil.save(
                BAHAN_FILE,
                adminService.getIngredientList()
        );
    }

    public static void loadAll() {

        Type userType =
                new TypeToken<List<User>>(){}.getType();

        Type menuType =
                new TypeToken<ArrayList<Menu>>(){}.getType();

        Type bahanType =
                new TypeToken<ArrayList<BahanBaku>>(){}.getType();

        List<User> users =
                JsonUtil.load(USER_FILE, userType);

        ArrayList<Menu> menus =
                JsonUtil.load(MENU_FILE, menuType);

        ArrayList<BahanBaku> bahan =
                JsonUtil.load(BAHAN_FILE, bahanType);

        if(users != null) {
            authService.setUsers(users);
        }

        if(menus != null) {
            adminService.setMenuList(menus);
        }

        if(bahan != null) {
            adminService.setIngredientList(bahan);
        }
    }
}