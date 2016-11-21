package com.hdu.cms.modules.upload.action;

import com.hdu.cms.common.BaseAction.BaseAction;
import com.hdu.cms.common.ConstantParam.ConstantParam;
import com.hdu.cms.common.ConstantParam.UNIQUETYPE;
import com.hdu.cms.common.Utils.ActionResult;
import com.hdu.cms.common.Utils.RandomUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by JetWang on 2016/10/17.
 */
@Controller
@RequestMapping("/test")
public class UploadAction extends BaseAction{

    /**
     * 返回在message中的是图片在当前项目中保存图片的名称
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public ActionResult getMultiFile(@RequestParam("file")List<MultipartFile> file){
        ActionResult result = new ActionResult(true);
        if(CollectionUtils.isNotEmpty(file)){
            for(MultipartFile Item: file){
                String orginName = Item.getOriginalFilename();
                String orldName =orginName.substring(orginName.indexOf("."));
                String newName = RandomUtil.getUniqueNubmerString(20, UNIQUETYPE.LOWERALPHABET)+orldName;
                String path = getServletContext().getRealPath("/");
                path+=ConstantParam.DEFAULT_IMAGE_PATH;
                File newFile = new File(path+newName);
                try {
                    Item.transferTo(newFile);
                    result.setMessage(newName);
                } catch (IOException e) {
                    e.printStackTrace();
                    result.setMessage("error");
                    result.setSuccess(false);
                }

            }
        }
     return result;
    }

}
