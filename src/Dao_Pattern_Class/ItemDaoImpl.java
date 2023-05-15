package Dao_Pattern_Class;

import Facade_Pattern_Class.DatabaseFacade;
import Factory_Pattern_Class.GeneralItemCreator;
import Factory_Pattern_Class.ItemCreator;
import Factory_Pattern_Class.ItemProduct;

import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao{
    private DatabaseFacade database;
    public ItemDaoImpl(DatabaseFacade database) {
         this.database = database;
    }
    
    /**아이템 추가*/
    @Override
    public void createItem(ItemProduct item) {
        int item_id = item.getItemID();
        String item_title = item.getTitle();
        int item_price = item.getPrice();
        String item_description = item.getDescription();
        int item_count = item.getCount();
        String userID = item.getUserID();
        String item_type = item.getItemType();
        File item_imageFile = item.getImageFile();
        Blob image = null;
        try {
            // 1. 파일을 바이트 배열로 읽습니다.
            FileInputStream fis = new FileInputStream(item_imageFile);
            byte[] fileData = new byte[(int) item_imageFile.length()];
            fis.read(fileData);
            fis.close();
            // 2. 바이트 배열을 Blob 객체로 변환합니다.
            image = new SerialBlob(fileData);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        try {
            //DB연결
            database.connect();
            String query = "INSERT INTO item (ID, title, price, description, imagefile, count, userid, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = database.getConn().prepareStatement(query);
            pstmt.setInt(1, item_id);
            pstmt.setString(2, item_title);
            pstmt.setInt(3, item_price);
            pstmt.setString(4, item_description);
            pstmt.setBinaryStream(5, image.getBinaryStream());
            pstmt.setInt(6, item_count);
            pstmt.setString(7, userID);
            pstmt.setString(8, item_type);
            pstmt.executeUpdate();

            //DB연결 해제
            database.closeConnect();
            //System.out.println("새로운 아이템 업로드!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ItemProduct> readItem() {
        List<ItemProduct> items = new ArrayList<>();

        try {
            //DB연결
            database.connect();
            String query = "SELECT * FROM item";
            PreparedStatement pstmt = database.getConn().prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                String item_title = rs.getString("title");
                int item_price = rs.getInt("price");
                String item_description = rs.getString("description");
                String id = rs.getString("id");
                Blob blob = rs.getBlob("imagefile");
                File item_imageFile = null;
                if(blob != null) {
                    InputStream inputStream = blob.getBinaryStream();
                    String path = "img\\";
                    String fileName = id + ".jpg";
                    File file = new File(path + fileName);

                    // FileOutputStream을 이용하여 Blob에서 읽은 데이터를 File에 씀
                    try (FileOutputStream outputStream = new FileOutputStream(file)) {
                        int bytesRead = -1;
                        byte[] buffer = new byte[4096];

                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        item_imageFile = new File("img/" + id +".jpg");
                    }
                    inputStream.close();
                }


                int item_count = rs.getInt("count");
                String userID = rs.getString("userid");
                String item_type = rs.getString("type");

                ItemCreator itemCreator = new GeneralItemCreator();
                ItemProduct item = itemCreator.createItemProduct(item_title, item_price, item_count, item_description, item_imageFile, userID, Integer.valueOf(id));
                items.add(item);

            }
            //DB연결 해제
            database.closeConnect();
            rs.close();
            //System.out.println("아이템 리스트를 받아왔습니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return items;
    }

    /**테이블의 값을 모두 수정된 값으로 변경한다.*/
    @Override
    public void updateItem(ItemProduct item) {
        try {
            //DB연결
            database.connect();
            String query = "UPDATE item SET title=?, price=?, description=?, imagefile=?, count=?, userid=?, type=? WHERE ID=?";
            PreparedStatement pstmt = database.getConn().prepareStatement(query);
            pstmt.setString(1, item.getTitle());
            pstmt.setInt(2, item.getPrice());
            pstmt.setString(3, item.getDescription());

            File item_imageFile = item.getImageFile();
            Blob image = null;
            try {
                // 1. 파일을 바이트 배열로 읽습니다.
                FileInputStream fis = new FileInputStream(item_imageFile);
                byte[] fileData = new byte[(int) item_imageFile.length()];
                fis.read(fileData);
                fis.close();
                // 2. 바이트 배열을 Blob 객체로 변환합니다.
                image = new SerialBlob(fileData);

            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
            pstmt.setBinaryStream(4, image.getBinaryStream());

            pstmt.setInt(5, item.getCount());
            pstmt.setString(6, item.getUserID());
            pstmt.setString(7, item.getItemType());
            pstmt.setInt(8, item.getItemID());
            pstmt.executeUpdate();

            //DB연결 해제
            database.closeConnect();
            //System.out.println("아이템 정보 수정");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**아이템 삭제*/
    @Override
    public void deleteItem(int id) {
        //System.out.println("삭제 아이디 " + id);
        try {
            //DB연결
            database.connect();
            String query = "DELETE FROM item WHERE id = ?";
            PreparedStatement pstmt = database.getConn().prepareStatement(query);
            pstmt.setString(1, String.valueOf(id));
            pstmt.executeUpdate();
            //DB연결 해제
            database.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getID() {
        int itemID = -1;
        try {
            database.connect();

            String query = "SELECT MAX(id) FROM item";
            PreparedStatement pstmt = database.getConn().prepareStatement(query);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                itemID = rs.getInt(1);
                itemID++;
            }
            rs.close();
            database.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return itemID;
    }
}
