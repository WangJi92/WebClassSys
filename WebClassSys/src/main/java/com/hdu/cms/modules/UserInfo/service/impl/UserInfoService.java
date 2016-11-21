package com.hdu.cms.modules.UserInfo.service.impl;

import com.google.common.collect.Lists;
import com.hdu.cms.common.ConstantParam.ConstantParam;
import com.hdu.cms.common.ConstantParam.DICTIONARY;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.RequestResponseContext.RequestResponseContext;
import com.hdu.cms.common.Utils.ExcelUtil;
import com.hdu.cms.common.Utils.HttpRequestUtils;
import com.hdu.cms.common.Utils.LogUtils;
import com.hdu.cms.modules.Dictionary.service.impl.DictionaryService;
import com.hdu.cms.modules.UserInfo.dao.UserInfoDao;
import com.hdu.cms.modules.UserInfo.dto.UserInfoDto;
import com.hdu.cms.modules.UserInfo.entity.UserInfo;
import com.hdu.cms.modules.UserInfo.service.IUserInfoService;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by JetWang on 2016/10/6.
 * 用户信息Service类
 */
@Service
public class UserInfoService  implements IUserInfoService{
    
    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo getUserInfoById(Integer id) {
        return userInfoDao.getUserById(id);
    }

    @Override
    public UserInfo getUserInfoByIndexCode(String indexCode) {
        return userInfoDao.getUserByIndexCode(indexCode);
    }

    @Override
    public List<UserInfo> getAllInfo() {
        return userInfoDao.getAllUserInfo();
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
       userInfoDao.userDeleteByIds(ids);
    }

    @Override
    public void deleteById(Integer id) {
        userInfoDao.userDeleteById(id);
    }

    @Override
    public void deleteByIndexCode(String indexCode) {
        userInfoDao.userDeleteByIndexcode(indexCode);
    }

    @Override
    public void deleteByIndexCodes(List<String> indexCodes) {
         userInfoDao.userDeleteByIndexcode(indexCodes);
    }

    @Override
    public UserInfo getUserByLgoinAccount(String userName) {
        return userInfoDao.getUserByLgoinAccount(userName);
    }

    @Override
    public boolean login(String loginAccount, String passWord) {
       UserInfo userInfo = getUserByLgoinAccount(loginAccount);
        if(userInfo !=null && userInfo.getPassword().equals(passWord)){
            return true;
        }
        return false ;
    }

    @Override
    public void saveOrUpdate(UserInfo userInfo) {
        userInfoDao.saveorUpdate(userInfo);

    }

    /**
     * 得到当前的数据的类型哦
     * @param pageSize
     * @param pageNo
     * @return
     */
    @Override
    public PageBean  findPageInfo(Integer pageSize, Integer pageNo,String serach,Integer userType) {
        PageBean<UserInfo>  oldPageBean =  userInfoDao.getPageUserInfo(pageSize, pageNo,serach,userType);
        if(CollectionUtils.isNotEmpty(oldPageBean.getRows())){
            PageBean newPageBean = new PageBean();
            BeanUtils.copyProperties(oldPageBean,newPageBean);
            Map<Integer,String> userTypeMap = DictionaryService.mapInteger.get(DICTIONARY.USERINFO);
            List<UserInfoDto> userInfoDtos = Lists.newArrayList();
            for(UserInfo Item : oldPageBean.getRows()){
                UserInfoDto infoDto = new UserInfoDto();
                BeanUtils.copyProperties(Item,infoDto);
                infoDto.setIndexcode(Item.getIndexCode());//前端不能获取到这个变量信息
                infoDto.setUserTypeStr(userTypeMap.get(Item.getUserType()));
                userInfoDtos.add(infoDto);
            }
            newPageBean.setRows(userInfoDtos);
            return  newPageBean;
        }
        return new PageBean();
    }


    @Override
    /**
     * 导出excel
     */
    public void exportUserInfo() {

       OutputStream output =ExcelUtil.getOutputStreamForExcelExport(RequestResponseContext.getResponse(), "user");
        FileOutputStream outputStream=null;
        try {
            outputStream = new FileOutputStream(HttpRequestUtils.getWebContextRootPath()+
            File.separator+ConstantParam.DEFAULT_EXCEL_PATH+"test.xls"
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        WritableWorkbook wbook = null;
        InputStream input = null;

        try {
            //获得gen 目录
            input = new FileInputStream(HttpRequestUtils.getWebContextRootPath()+File.separator+
                    ConstantParam.DEFAULT_EXCEL_PATH
                    +ConstantParam.EXPORT_USER);
            //从之前存在的xls中创建新的excel
            wbook = Workbook.createWorkbook(output, Workbook.getWorkbook(input));
            WritableSheet sheet = wbook.getSheet(0);
            exportToUserInfo(sheet);
            //output.flush();
            outputStream.close();
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
    private void exportToUserInfo(WritableSheet sheet){
        int row =0;
        int col =0;
        try {
            List<UserInfo> userInfoList = getAllInfo();
            Map<Integer,String> userTypeMap = DictionaryService.mapInteger.get(DICTIONARY.USERINFO);
            if(CollectionUtils.isNotEmpty(userInfoList)){
                for(UserInfo Item : userInfoList){
                    row++;
                    col=0;
                    sheet.addCell(new Label(col++,row,Item.getUserName()));
                    sheet.addCell(new Label(col++,row,Item.getLoginAccount()));
                    sheet.addCell(new Label(col++,row,Item.getPassword()));
                    sheet.addCell(new Label(col++,row,Item.getPhone()));
                    sheet.addCell(new Label(col++,row,userTypeMap.get(Item.getUserType())));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
