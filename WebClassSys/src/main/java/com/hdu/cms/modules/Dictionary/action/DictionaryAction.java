package com.hdu.cms.modules.Dictionary.action;

import com.google.common.collect.Lists;
import com.hdu.cms.common.BaseAction.BaseAction;
import com.hdu.cms.common.ConstantParam.ConstantParam;
import com.hdu.cms.common.ConstantParam.DICTIONARY;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.ActionResult;
import com.hdu.cms.common.Utils.LogUtils;
import com.hdu.cms.common.Utils.RandomUtil;
import com.hdu.cms.common.Utils.SelectBean;
import com.hdu.cms.modules.Dictionary.entity.Dictionary;
import com.hdu.cms.modules.Dictionary.service.impl.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by JetWang on 2016/10/10.
 */
@Controller(value = "dictionaryAction")
@RequestMapping(value = "/dictionary")
public class DictionaryAction  extends BaseAction{

    @Resource
    private DictionaryService dictionaryService;

    /**
     * 添加数据字典
     * @param dictionary
     * @return
     */
    @RequestMapping(value = "/addOrUpdate")
    @ResponseBody
    public ActionResult addorUpdate(Dictionary dictionary){
        ActionResult result = new ActionResult(true);
        try {
            if(dictionary.getId() == null){
                DICTIONARY dicType = DICTIONARY.getDictionaryByKey(Integer.valueOf(dictionary.getClassfiyType()));
                dictionary.setClassfiyType(dicType.getValue());
                dictionary.setFatherState(0);
                dictionary.setFixed(0);
                dictionary.setIndexCode(RandomUtil.getUUID32BIT());
                dictionaryService.saveOrUpdateDic(dictionary);
            }else{
                Dictionary old = dictionaryService.findById(dictionary.getId());
                if(old !=null){
                    BeanUtils.copyProperties(dictionary,old);
                    dictionaryService.saveOrUpdateDic(old);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.logException(e);
            result.setMessage("添加字典系统错误");
            result.setSuccess(false);
        }
        return  result;
    }

    /**
     * 分页查找
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "findPage")
    @ResponseBody
    public ActionResult getPageBean(Integer pageNo,Integer pageSize,String search){
        ActionResult result  = new ActionResult(true);
        PageBean bean =  new PageBean();
        try {
            if(pageNo == null || pageSize == null){
                pageNo = ConstantParam.DEFAULT_PAGE_NO;
                pageSize =ConstantParam.DEFAULT_PAGE_SIZE;
            }
            PageBean pageBean = dictionaryService.findPageBean(pageNo, pageSize,search);
            result.setData(pageBean);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.logError("查找数据字典的pageBean 出现异常");
            result.setMessage("寻找pageBean出现异常");
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 通过Ids删除数据
     * @param ids
     * @return
     */
    @RequestMapping(value = "deleteByIds")
    @ResponseBody
    public ActionResult deleteByIds(@RequestParam("ids[]")List<Integer> ids){
        ActionResult result = new ActionResult(true);
        try {
            dictionaryService.deleteDicByIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.logError("删除出现异常");
            result.setSuccess(false);
            result.setMessage("删除出现异常");
        }
        return result;
    }
    @RequestMapping(value = "deleteByIndexcodes")
    @ResponseBody
    public ActionResult deleteByIndexcodes(List<String> indexcodes){
        ActionResult result = new ActionResult(true);
        try {
            dictionaryService.delteDicByIndexcodes(indexcodes);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.logError("删除出现异常");
            result.setSuccess(false);
            result.setMessage("删除出现异常");
        }
        return result;
    }

    /**
     * 通过indexcode查找
     * @param indexcode
     * @return
     */
    @RequestMapping(value = "findByIndexcode")
    @ResponseBody
    public ActionResult findByIndexcode(String  indexcode){
        ActionResult result = new ActionResult(true);
        try {
            Dictionary dictionary = dictionaryService.findByIndexcode(indexcode);
            result.setData(dictionary);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.logError("查找出现异常");
            result.setSuccess(false);
            result.setMessage("查找出现异常");
        }
        return result;
    }
    @RequestMapping(value = "findById")
    @ResponseBody
    public ActionResult findById(Integer  id){
        ActionResult result = new ActionResult(true);
        try {
            Dictionary dictionary = dictionaryService.findById(id);
            result.setData(dictionary);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.logError("查找出现异常");
            result.setSuccess(false);
            result.setMessage("查找出现异常");
        }
        return result;
    }

    @RequestMapping(value = "delById")
    @ResponseBody
    public ActionResult deldById(Integer  id){
        ActionResult result = new ActionResult(true);
        try {
            dictionaryService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.logError("删除出现异常");
            result.setSuccess(false);
            result.setMessage("删除出现异常");
        }
        return result;
    }

    /**
     * 通过Indexcode删除
     * @param indexcode
     * @return
     */
    @RequestMapping(value = "delByIndexcode")
    @ResponseBody
    public ActionResult deldByIndexcode(String  indexcode){
        ActionResult result = new ActionResult(true);
        try {
            dictionaryService.findByIndexcode(indexcode);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.logError("删除出现异常");
            result.setSuccess(false);
            result.setMessage("删除出现异常");
        }
        return result;
    }
    @RequestMapping(value = "dicFatherSelectBean")
    @ResponseBody
    public ActionResult getDicFatherSelectBean(){
        ActionResult result = new ActionResult(true);
        try {
            List<SelectBean> selectBeanList = dictionaryService.getDicFatherSelectBean();
            result.setData(selectBeanList);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("系统异常");
        }
        return result;
    }
    @RequestMapping(value = "getDicSelectByName")
    @ResponseBody
    public ActionResult getDicSelectBeanByName(String typeName){
        ActionResult result = new ActionResult(true);
        if(StringUtils.isNotEmpty(typeName)){
            try {
                DICTIONARY dictionary = DICTIONARY.getDictionary(typeName);
                List<SelectBean> selectBeanList = dictionaryService.getDicSelectBaeanByType(dictionary);
                result.setData(selectBeanList);
            } catch (Exception e) {
                e.printStackTrace();
                result.setSuccess(false);
                result.setMessage("系统异常");
            }
        }
        return  result;
    }

    @RequestMapping(value="dicAdd")
    public String getAddDialog(){
        return "/views/dictionary/dialog/add/dic_add";
    }
    @RequestMapping(value="dicEdit")
    public ModelAndView getEditDialog(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/views/dictionary/dialog/edit/edit");
        return modelAndView;
    }

    /**
     * 数据字典的值
     * type USERINFO
     * name  XXXXX
     * value 11111
     * @return
     */
    @RequestMapping(value="getDicTypeValue")
    @ResponseBody
    public ActionResult getDicTypeValue(){
        List<SelectBean> list = Lists.newArrayList();
        for(DICTIONARY item:DICTIONARY.values()){
            SelectBean selectBean = new SelectBean();
            selectBean.setKey(item.getValue());
            selectBean.setValue(item.getKey());
            list.add(selectBean);
        }
        ActionResult actionResult = new ActionResult(true);
        actionResult.setData(list);
        return  actionResult;
    }

    @RequestMapping("exportdic")
    @ResponseBody
    public  void exportDic(){
        dictionaryService.exprotDic();
    }



}
