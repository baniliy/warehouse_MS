package com.baniliy.dao;

import com.baniliy.bean.Material;
import com.baniliy.utils.DBUtils1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDao {

    /*
    * 添加
    * */
    public int addMaterial(Material material) {
        int num=0;
        try {
            Connection conn=DBUtils1.getConnection();
            PreparedStatement psta = conn.prepareStatement("insert into Material(serial,name,kind,num,location,updatetime,status) " +
                    "values(?,?,?,?,?,?,?)");
            psta.setString(1,material.getSerial());
            psta.setString(2,material.getName());
            psta.setString(3,material.getKind());
            psta.setInt(4,material.getNum());
            psta.setString(5,material.getLocation());
            psta.setString(6,material.getUpdatetime());
            psta.setInt(7,material.getStatus());
            num=psta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    /*
    * 根据Id删除
    * */
    public int deleteMaterialById(Integer id) {
        int num=0;
        try {
            Connection conn= DBUtils1.getConnection();
            PreparedStatement psta = conn.prepareStatement("delete from Material where id=?");
            psta.setInt(1,id);
            num=psta.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    /*
    * 根据Id修改信息
    * */
    public int updateMaterial(Material material) {
        int num=0;
        try {
            Connection conn= DBUtils1.getConnection();
            PreparedStatement psta = conn.prepareStatement("update Material set serial=?,name=?,kind=?,num=?,location=?,updatetime=?,status=? where id=?");
            psta.setString(1,material.getSerial());
            psta.setString(2,material.getName());
            psta.setString(3,material.getKind());
            psta.setInt(4,material.getNum());
            psta.setString(5,material.getLocation());
            psta.setString(6,material.getUpdatetime());
            psta.setInt(7,material.getStatus());
            psta.setInt(8,material.getId());
            num=psta.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }



    /*
     * 根据关键词模糊查询
     * Param:
     *      k (serial,name,kind,) 种类；
     *      s 具体值；
     * */
    public List<Material> queryMaterialByKey(String k,String s) {
        List<Material> materials= new ArrayList<>();
        try {
            Connection conn= DBUtils1.getConnection();
            String sql = "select * from Material where "+k+" like ?";
            PreparedStatement psta = conn.prepareStatement(sql);
//            psta.setString(1,k);
            psta.setString(1,"%"+s+"%");               //  模糊查询
            System.out.println("\tSQL----------"+psta.toString());
            ResultSet rs = psta.executeQuery();

            while (rs.next()) {//name=?,kind=?,num=?,location=?,updatetime=?,status=? serial=?,
                Integer id=rs.getInt("id");
                String serial=rs.getString("serial");
                String name1=rs.getString("name");
                String kind1=rs.getString("kind");
                Integer num=rs.getInt("num");
                String location=rs.getString("location");
                String updatetime=rs.getString("updatetime");
                Integer status = rs.getInt("status");
                Material material=new Material(id,serial,name1,kind1,num,location,updatetime,status);
                materials.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materials;
    }

    /*
     * 根据Id查询
     * */
    public Material queryMaterialById(Integer id) {
        Material material = new Material();
        try {
            Connection conn= DBUtils1.getConnection();
            PreparedStatement psta = conn.prepareStatement("select * from Material where id=?");
            psta.setInt(1,id);
            ResultSet rs = psta.executeQuery();

            if (rs.next()) {//name=?,kind=?,num=?,location=?,updatetime=?,status=? serial=?,
                material.setId(rs.getInt("id"));
                material.setSerial(rs.getString("serial"));
                material.setName(rs.getString("name"));
                material.setKind(rs.getString("kind"));
                material.setNum(rs.getInt("num"));
                material.setLocation(rs.getString("location"));
                material.setUpdatetime(rs.getString("updatetime"));
                material.setStatus(rs.getInt("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return material;
    }

    /*
    * 查询所有
    * */
    public List<Material> queryAllMaterials() {
        List<Material> materials = new ArrayList<>();
        try {
            Connection conn = DBUtils1.getConnection();
            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery("select * from Material");
            int rows = 0;
            while (rs.next()) {
                Integer id=rs.getInt("id");
                String serial=rs.getString("serial");
                String name=rs.getString("name");
                String kind=rs.getString("kind");
                Integer num=rs.getInt("num");
                String location=rs.getString("location");
                String updatetime=rs.getString("updatetime");
                Integer status = rs.getInt("status");
                Material material=new Material(id,serial,name,kind,num,location,updatetime,status);
                materials.add(material);
                rows++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materials;
    }

    /*
    * 查询总数
    * */
    public Integer queryTotalCount() {
        int count=0;
        try {
            Connection conn= DBUtils1.getConnection();
            Statement sta = conn.createStatement();
            ResultSet rs=sta.executeQuery("select count(*) from Material");
            if (rs.next())
            {
                count=rs.getInt(1);
//                count = count +1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /*
    * 分页查询
    * */
    public List<Material> queryForPageItems(int begin, int pageSize) {
        List<Material> materials = new ArrayList<>();
        try {
            Connection conn = DBUtils1.getConnection();
            PreparedStatement psta = conn.prepareStatement("select * from Material limit ?,?");
            psta.setInt(1,begin);
            psta.setInt(2,pageSize);
            ResultSet rs = psta.executeQuery();
            int rows = 0;
            while (rs.next()) {
                Integer id=rs.getInt("id");
                String serial=rs.getString("serial");
                String name=rs.getString("name");
                String kind=rs.getString("kind");
                Integer num=rs.getInt("num");
                String location=rs.getString("location");
                String updatetime=rs.getString("updatetime");
                Integer status = rs.getInt("status");
                Material material=new Material(id,serial,name,kind,num,location,updatetime,status);
                materials.add(material);
                rows++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materials;
      
    }

    /*
     * 查询总数
     * */
    public Integer queryTotalCountByKey(String k, String s) {
        int count=0;
        try {
            Connection conn= DBUtils1.getConnection();
            String sql = "select * from Material where "+k+" like ?";
            PreparedStatement psta = conn.prepareStatement(sql);
            psta.setString(1,"%"+s+"%");               //  模糊查询
//            System.out.println("\tSQL----------"+psta.toString());
            ResultSet rs = psta.executeQuery();
            while (rs.next()) {
                count = count + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    /* 根据关键词模糊查询
    * Param:
    *      k (serial,name,kind,) 种类；
    *      s 具体值；
    */
    public List<Material> queryForPageItemsByKey(String k, String s , int begin, int pageSize) {
        List<Material> materials = new ArrayList<>();
        try {
            Connection conn = DBUtils1.getConnection();
            String sql = "select * from Material where "+k+" like ? limit ?,?";
            PreparedStatement psta = conn.prepareStatement(sql);
            psta.setString(1,"%"+s+"%");
            psta.setInt(2,begin);
            psta.setInt(3,pageSize);
            ResultSet rs = psta.executeQuery();
            int rows = 0;
            while (rs.next()) {
                Integer id=rs.getInt("id");
                String serial=rs.getString("serial");
                String name=rs.getString("name");
                String kind=rs.getString("kind");
                Integer num=rs.getInt("num");
                String location=rs.getString("location");
                String updatetime=rs.getString("updatetime");
                Integer status = rs.getInt("status");
                Material material=new Material(id,serial,name,kind,num,location,updatetime,status);
                materials.add(material);
                rows++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materials;

    }

}
