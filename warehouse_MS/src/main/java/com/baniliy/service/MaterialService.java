package com.baniliy.service;

import com.baniliy.bean.Material;
import com.baniliy.dao.MaterialDao;

import java.util.List;

public class MaterialService {


    private MaterialDao materialDao = new MaterialDao();

    /*查询所有物料信息*/
    public List<Material> queryAll(){return materialDao.queryAllMaterials();}

    /*根据ID查找*/
    public Material query(Integer id){
        return materialDao.queryMaterialById(id);
    }

    /*根据Key查找所有符合匹配项（模糊查询）*/
    public List<Material> queryByKey(String k ,String s){
        return materialDao.queryMaterialByKey(k,s);
    }
    /*根据ID查找*/
    public boolean delete(Integer serial){return materialDao.deleteMaterialById(serial) != 0;}

    /*添加*/
    public Boolean add(Material material){return materialDao.addMaterial(material) != 0;}

    /*根据ID修改*/
    public Boolean update(Material material){return materialDao.updateMaterial(material) != 0;}

    /********************************************************************************************************分页服务***/
    /*统计数量*/
    public int queryTotalCount(){return materialDao.queryTotalCount();}
    /*分页查询*/
    public List<Material> queryForPage(int begin, int pageSize){return materialDao.queryForPageItems(begin,pageSize);}

    /*统计数量*/
    public int queryTotalCountByKey(String k ,String s){return materialDao.queryTotalCountByKey(k,s);}
    /*根据Key查找所有符合匹配项（模糊查询）*/
    public List<Material> queryForPageByKey(String k ,String s,int begin, int pageSize){return materialDao.queryForPageItemsByKey(k,s,begin,pageSize);}

}
