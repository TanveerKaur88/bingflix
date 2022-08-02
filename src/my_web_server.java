
import java.util.Properties;
import com.vmm.JHTTPServer;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class my_web_server extends JHTTPServer {

    public my_web_server(int portno) throws Exception {
        super(portno);
    }

    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) {

        Response res = new Response(HTTP_OK, "text/plain", "Hi");
        System.out.println("uri--------------->" + uri);
        if (uri.equals("/login")) {
            String user = parms.getProperty("username");
            String pass = parms.getProperty("password");
            try {
                ResultSet rs = db_loader.executeSQL("select *  from admin where username='" + user + "' and password='" + pass + "'");
                if (rs.next()) {
                    res = new Response(HTTP_OK, "text/plain", "success");
                } else {
                    res = new Response(HTTP_OK, "text/plain", "fails");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (uri.equals("/admin_change_password")) {
            String op = parms.getProperty("old_password");
            String np = parms.getProperty("new_password");
            String u = parms.getProperty("username");

            try {
                ResultSet rs = db_loader.executeSQL("select *  from admin where username='" + u + "' and  password='" + op + "'");
                if (rs.next()) {
                    rs.updateString("password", np);
                    rs.updateRow();
                    res = new Response(HTTP_OK, "text/plain", "success");
                } else {
                    res = new Response(HTTP_OK, "text/plain", "fails");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (uri.equals("/admin_add_category")) {
            String cn = parms.getProperty("category_name");
            String des = parms.getProperty("description");

            try {
                ResultSet rs = db_loader.executeSQL("select *  from category where category_name='" + cn + "' ");
                if (rs.next()) {

                    res = new Response(HTTP_OK, "text/plain", "fails");
                } else {

                    String filename = saveFileOnServerWithRandomName(files, parms, "photo", "src/bingeflixPhotos");
                    System.out.println("photoname   " + filename);
                    String photoname = "src/bingeflixPhotos/" + filename;
                    rs.moveToInsertRow();
                    rs.updateString("category_name", cn);
                    rs.updateString("description", des);
                    rs.updateString("photo", photoname);
                    rs.insertRow();
                    res = new Response(HTTP_OK, "text/plain", "success");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (uri.equals("/getAllCategories")) {

            String ans = "";

            try {
                ResultSet rs = db_loader.executeSQL("select *  from category");
                while (rs.next()) {
                    String category_name = rs.getString("category_name");
                    String description = rs.getString("description");
                    String photo = rs.getString("photo");

                    ans += category_name + "**" + description + "**" + photo + "#$#";
                }
                res = new Response(HTTP_OK, "text/plain", ans);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (uri.equals("/admindeletecategory")) {
            String category_name = parms.getProperty("catname");
            ResultSet rs = db_loader.executeSQL("select *  from category where category_name='" + category_name + "'");
            try {
                if (rs.next()) {
                    rs.deleteRow();
                    res = new Response(HTTP_OK, "text/plain", "success");
                } else {
                    res = new Response(HTTP_OK, "text/plain", "fails");
                }
                //get catname
                //rs= DBloader(select * from category where category_name=+catname)
//            if(rs.next)
//delete row
//            send response success
//            else
//            send response fails
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

        return res;
    }

}
