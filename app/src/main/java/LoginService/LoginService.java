package LoginService;
import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
public class LoginService {
    public static boolean saveInfo(Context context, String username,
                                   String password) {
        try {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                File file = new File(Environment.getExternalStorageDirectory(), "info.txt");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write((username + "#" + password).getBytes());
                fos.flush();
                fos.close();
                return true;
            } else {
                Toast.makeText(context, "cdcard被卸载", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static HashMap<String, String> getInfo(Context context) {
        File file = new File(Environment.getExternalStorageDirectory(), "info.txt");
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String[] result = br.readLine().split("#");
            HashMap<String, String> map = new HashMap<>();
            map.put("username", result[0]);
            map.put("password", result[1]);
            br.close();
            return map;
        } catch (Exception e) {
            Toast.makeText(context, "无法读取用户信息", Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}
