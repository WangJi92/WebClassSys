package com.hdu.cms.common.Utils;

/**
 * Created by JetWang on 2016/10/1.
 */
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFCreationHelper;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelUtil {
    public static final String EXPORT_PIC_SUFFIX = "PicUrl";
    public static final String PIC_SUFFIX = "jpg";

    public static OutputStream getOutputStreamForExcelExport(
            HttpServletResponse response, String fileName) {
        if (response == null) {
            return null;
        }
        OutputStream os = null;
        try {
            os = response.getOutputStream();
        } catch (IOException e) {
            LogUtils.logException(e);
        }
        response.reset();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
        String dateString = sdf.format(new Date());
        response.setHeader("Content-disposition", "attachment; filename=\""
                + fileName + dateString + ".xls\"");
        response.setContentType("application/msexcel");
        return os;
    }

    /**
     * 根据反射针对list数据导出
     *
     * @param maps
     *            excel头部
     * @param list
     *            需导出数据
     * @param os
     *            输出流
     * @param <T>
     *            数据类型
     * @return 导出结果
     */
    @SuppressWarnings({ "unchecked", "RedundantArrayCreation" })
    public static <T> boolean excelExport(Map<String, String> maps,
                                          List<T> list, OutputStream os) {
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFCreationHelper createHelper = wb.getCreationHelper();

            HSSFSheet sheet = wb.createSheet("sheet1");
            // 只需申明一次，导出图片使用
            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

            Set<String> sets = maps.keySet();
            Row row = sheet.createRow(0);
            int i = 0;
            // 定义表头
            for (String key : sets) {
                Cell cell = row.createCell(i++);
                cell.setCellValue(createHelper.createRichTextString(maps
                        .get(key)));
            }
            // 填充表单内容
            float avg = list.size() / 20f;
            int count = 1;
            for (int j = 0; j < list.size(); j++) {
                T p = list.get(j);
                Class classType = p.getClass();
                int index = 0;
                Row row1 = sheet.createRow(j + 1);
                for (String key : sets) {
                    String firstLetter = key.substring(0, 1).toUpperCase();
                    String getMethodName = "get" + firstLetter
                            + key.substring(1);
                    Method getMethod = classType.getMethod(getMethodName,
                            new Class[] {});
                    Object value = getMethod.invoke(p, new Object[] {});

                    if (StringUtils.isNotBlank(key)
                            && key.endsWith(EXPORT_PIC_SUFFIX)) {// 导出图片

                        if (null != value && value instanceof String) {
                            try {
                                patriarch
                                        .createPicture(
                                                createClientAnchor(
                                                        createHelper, j + 1,
                                                        index),
                                                wb.addPicture(
                                                        toByteImage(
                                                                (String) value,
                                                                PIC_SUFFIX),
                                                        HSSFWorkbook.PICTURE_TYPE_JPEG));
                            } catch (Throwable e) {

                            }
                        }
                        index++;
                    } else {

                        Cell cell = row1.createCell(index++);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if (null != value) {
                            if (value instanceof String) {
                                cell.setCellValue(createHelper
                                        .createRichTextString((String) value));
                            } else if (value instanceof Date) {
                                /*cell.setCellValue(createHelper.createRichTextString(PmsDateTimeUtil
                                        .dateToString(
                                                (Date) value,
                                                PmsDateTimeUtil.DATA_FORMAT_yyyy_MM_dd_HH_mm_ss)));*/
                            } else {
                                cell.setCellValue(createHelper
                                        .createRichTextString(String
                                                .valueOf(value)));
                            }
                        }
                    }
                }
                if (j > avg * count) {
                    count++;
                }
                if (count == 20) {
                    count++;
                }
            }
            wb.write(os);
            os.close();

        } catch (Exception e) {
            //PmsLogRecord.logException(e);
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public static  byte[] toByteImage(String url, String suffix) throws IOException {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();

        BufferedImage bufferImg = ImageIO.read(new URL(url));
        ImageIO.write(bufferImg, suffix, byteArrayOut);

        byte[] result = byteArrayOut.toByteArray();
        byteArrayOut.close();

        return result;
    }
    /**
     * 生成excel表格中图片位置anchor
     *
     * @param createHelper 工厂
     * @param row          行
     * @param col          列
     * @return anchor
     */
    private static HSSFClientAnchor createClientAnchor(HSSFCreationHelper createHelper, int row, int col) {
        HSSFClientAnchor anchor = createHelper.createClientAnchor();
        anchor.setDx1(0);
        anchor.setDy1(0);
        anchor.setDx2(1023);
        anchor.setDy2(255);
        anchor.setCol1(col);
        anchor.setRow1(row);
        anchor.setCol2(col);
        anchor.setRow2(row);

        return anchor;
    }

}