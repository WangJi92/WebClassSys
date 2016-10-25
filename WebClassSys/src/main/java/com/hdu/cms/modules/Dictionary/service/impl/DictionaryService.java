package com.hdu.cms.modules.Dictionary.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hdu.cms.common.ConstantParam.ConstantParam;
import com.hdu.cms.common.ConstantParam.DICTIONARY;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.RequestResponseContext.RequestResponseContext;
import com.hdu.cms.common.Utils.*;
import com.hdu.cms.modules.Dictionary.dao.DictionaryDao;
import com.hdu.cms.modules.Dictionary.entity.Dictionary;
import com.hdu.cms.modules.Dictionary.service.IDictionary;
import com.hdu.cms.modules.Equipment.entity.Equipment;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by JetWang on 2016/10/10.
 * 数据字典工具类
 */
@Service(value = "dictionaryService")
public class DictionaryService implements IDictionary, InitializingBean {

    @Resource
    private DictionaryDao dictionaryDao;

    /**
     *使用缓存处理信息，不用每次都去查找数据库信息，这样不好！
     */
    public  static Map<DICTIONARY, Map<String, Integer>> mapString = Maps.newHashMap();
    public static Map<DICTIONARY, Map<Integer, String>> mapInteger = Maps.newHashMap();
    public static Map<DICTIONARY, List<SelectBean>> mapSelectBean = Maps.newHashMap();

    @Override
    public void afterPropertiesSet() throws Exception {
        reloadAll();
    }

    public  void reloadItem(DICTIONARY dictionary) {
        mapString.put(dictionary, dictionaryDao.dicGetAllMapKeyStringClassfyByType(dictionary.getValue()));
        mapInteger.put(dictionary, dictionaryDao.dicGetAllMapKeyIntegerClassfyByType(dictionary.getValue()));
        mapSelectBean.put(dictionary, getSeletBean(dictionary));
    }

    public  void reloadAll() {
        for (DICTIONARY item : DICTIONARY.values()) {
            mapString.put(item, dictionaryDao.dicGetAllMapKeyStringClassfyByType(item.getValue()));
            mapInteger.put(item, dictionaryDao.dicGetAllMapKeyIntegerClassfyByType(item.getValue()));
            mapSelectBean.put(item, getSeletBean(item));
        }
    }

    public  List<SelectBean> getSeletBean(DICTIONARY dictionary) {
        List<Dictionary> dictionaryList = dictionaryDao.dicGetAllClassfyByType(dictionary.getValue());
        return DicSelectBeanUtils.createSelectBean(dictionaryList);
    }

    /**
     * 根据类型查找所有的select
     *
     * @param dictionary
     * @return
     */
    @Override
    public List<SelectBean> getDicSelectBaeanByType(DICTIONARY dictionary) {
        return mapSelectBean.get(dictionary);
    }

    @Override
    public List<SelectBean> getDicFatherSelectBean() {
        List<Dictionary> dictionaryList = dictionaryDao.dicGetFatherClassFy();
        return DicSelectBeanUtils.createSelectBean(dictionaryList);
    }

    @Override
    public Map<String, Integer> getDicMapByTypeKeyString(DICTIONARY dictionary) {
        return mapString.get(dictionary);
    }

    @Override
    public Map<Integer, String> getDicMapByTypeKeyInteger(DICTIONARY dictionary) {
        return mapInteger.get(dictionary);
    }

    /**
     * 添加或者更新结果
     *
     * @param dictionary
     */
    @Override
    public void saveOrUpdateDic(Dictionary dictionary) {
        dictionaryDao.dicSaveOrUpdate(dictionary);
        // 根据当前数据字典的 classfyType 查找到
        DICTIONARY item = DICTIONARY.getDictionary(dictionary.getClassfiyType());
        //修改缓存！
        reloadItem(item);
    }

    /**
     * 通过idｓ删除所有的数据字典
     *
     * @param ids
     */
    @Override
    public void deleteDicByIds(List<Integer> ids) {
        dictionaryDao.dicDeleteByIds(ids);
        reloadAll();
    }

    /**
     * 通过indexcodes 删除所有的数据字典
     *
     * @param indexcodes
     */
    @Override
    @Transactional(readOnly = false)
    public void delteDicByIndexcodes(List<String> indexcodes) {
        dictionaryDao.dicDeleteByIndexCode(indexcodes);
        reloadAll();
    }

    /**
     * 通过indexcode查看信息
     *
     * @param indexcode
     * @return
     */
    @Override
    public Dictionary findByIndexcode(String indexcode) {
        return dictionaryDao.dicFindByIndexcode(indexcode);
    }

    /**
     * 通过Id查看当前的信息
     *
     * @param id
     * @return
     */
    @Override
    public Dictionary findById(Integer id) {
        return dictionaryDao.dicFindById(id);
    }

    /**
     * 查看当前的分页
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageBean findPageBean(Integer pageNo, Integer pageSize,String serarch) {
        return dictionaryDao.dicFindPageBean(pageNo, pageSize,serarch);
    }
    @Override
    public List<Dictionary> findAllDic() {
        return dictionaryDao.dicFindAll();
    }

    @Override
    public void exprotDic() {
        OutputStream output = ExcelUtil.getOutputStreamForExcelExport(RequestResponseContext.getResponse(), "dictionary");
        WritableWorkbook wbook = null;
        InputStream input = null;
        try {
            //获得gen 目录
            input = new FileInputStream(HttpRequestUtils.getWebContextRootPath()+ File.separator+
                    ConstantParam.DEFAULT_EXCEL_PATH
                    +ConstantParam.EXPORT_DIC);
            //从之前存在的xls中创建新的excel
            wbook = Workbook.createWorkbook(output, Workbook.getWorkbook(input));
            WritableSheet sheet = wbook.getSheet(0);
            exportDicSheet(sheet);
        } catch (IOException e){
            e.printStackTrace();
            LogUtils.logException(e);
        }
        catch (BiffException e){
            e.printStackTrace();
            LogUtils.logException(e);
        }finally {
            try {
                if(wbook!=null){
                    wbook.write();
                    wbook.close();
                }
                if(output != null){
                    output.flush();
                    output.close();
                }
                if(input != null){
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                LogUtils.logException(e);
            } catch (WriteException e) {
                e.printStackTrace();
                LogUtils.logException(e);
            }
        }
    }

    private void exportDicSheet(WritableSheet sheet){
        int row =0;
        int col =0;
        try {
            List<Dictionary> dictionaryList= findAllDic();
            if(CollectionUtils.isNotEmpty(dictionaryList)){
                for(Dictionary Item : dictionaryList){
                    row++;
                    col=0;
                    sheet.addCell(new Label(col++,row,Item.getClassfiyType()));//字典分类
                    sheet.addCell(new Label(col++,row,Item.getName()));//字典名称
                    sheet.addCell(new Label(col++,row,Item.getValue().toString()));//字典值
                    sheet.addCell(new Label(col++,row,(Item.getFixed() !=null && Item.getFixed()==1)?"是":"否"));
                    sheet.addCell(new Label(col++,row,(Item.getFatherState() !=null && Item.getFatherState()==1)?"是":"否"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
