package com.JieCheng.util;

import com.JieCheng.dao.model.Subject;
import com.JieCheng.dao.model.User;
import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Zhang on 2017/5/10.
 */
@Service
public class ExcelUtil {
    /*
    导出模板
     */
    public void excelTempletExport(String sheetName, String label[], HttpServletResponse response) throws Exception {
        int len = label.length;
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = format.format(date);
        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition", "attachment;   filename=\"" + time + ".xls" + "\"");
        OutputStream os = response.getOutputStream();
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        WritableSheet sheet = workbook.createSheet(sheetName, 0);
        Label lab = null;
        for (int i = 0; i < len; i++) {
            lab = new Label(i, 0, label[i]); //Label(col,row,str);
            sheet.addCell(lab);
        }
        workbook.write();
        workbook.close();
    }

    /*
    模板数据转题目对象
     */
    public Subject[] templetToSubjects(MultipartFile file) throws IOException, BiffException {
        Workbook workBook = Workbook.getWorkbook(file.getInputStream());
        Sheet sheets = workBook.getSheets()[0];
        Subject[] subjects = new Subject[sheets.getRows() - 1];
        for (int i = 1; i < sheets.getRows(); i++) {
            Cell[] cells = sheets.getRow(i);
            subjects[i - 1] = new Subject();
            subjects[i - 1].setContent(cells[0].getContents());
            subjects[i - 1].setInfo(cells[1].getContents());
            subjects[i - 1].setSubjecttype(cells[2].getContents());
            subjects[i - 1].setSubjectselect(cells[3].getContents());
            subjects[i - 1].setAnswer(cells[4].getContents());
            try {
                subjects[i - 1].setGrade(Integer.parseInt(cells[5].getContents()));
            } catch (Exception e) {
                subjects[i - 1].setGrade(0);
            }
            subjects[i - 1].setCarexam(cells[6].getContents());
            subjects[i - 1].setCartype(cells[7].getContents());
            subjects[i - 1].setUrl("");
        }
        return subjects;
    }

    /*
    模板数据转用户对象
     */
    public User[] templetToUser(MultipartFile file) throws IOException, BiffException {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        Workbook workBook = Workbook.getWorkbook(file.getInputStream());
        Sheet sheets = workBook.getSheets()[0];
        User[] users = new User[sheets.getRows() - 1];
        for (int i = 1; i < sheets.getRows(); i++) {
            Cell[] cells = sheets.getRow(i);
            users[i - 1] = new User();
            users[i - 1].setName(cells[0].getContents());
            users[i - 1].setAge(cells[1].getContents());
            users[i - 1].setSex(cells[2].getContents());
            users[i - 1].setPhone(cells[3].getContents());
            users[i - 1].setIDCard(cells[4].getContents());
            users[i - 1].setMail(cells[5].getContents());
            users[i - 1].setLoginName(cells[6].getContents());
            users[i - 1].setPassword(cells[7].getContents());
            users[i - 1].setOnline("N");
            switch (cells[8].getContents()) {
                case "学员":
                    users[i - 1].setRoleId(1);
                    break;
                case "区域代理":
                    users[i - 1].setRoleId(2);
                    break;
                case "加盟伙伴":
                    users[i - 1].setRoleId(3);
                    break;
                case "管理员":
                    users[i - 1].setRoleId(4);
                    break;
                default:
                    users[i - 1].setRoleId(0);
                    break;
            }
            users[i - 1].setCarType(cells[9].getContents());
            users[i - 1].setCarExam(cells[10].getContents());
            try {
                DateCell dateCellBegin = (DateCell) cells[11];
                DateCell dateCellEnd = (DateCell) cells[11];
                users[i - 1].setBeginTime(dateFormater.format(dateCellBegin.getDate()));
                users[i - 1].setEndTime(dateFormater.format(dateCellEnd.getDate()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public void returnResoult(String result, MultipartFile file, HttpServletResponse response) throws Exception {
        String[] results = result.split("End;");
        Label lab = null;
        //获取上传数据表
        Workbook workBook = Workbook.getWorkbook(file.getInputStream());
        Sheet sheets = workBook.getSheets()[0];
        //得到总列数
        int len = sheets.getColumns();
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = format.format(date);
        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition", "attachment;   filename=\"" + time + ".xls" + "\"");
        OutputStream os = response.getOutputStream();
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        WritableSheet sheet = workbook.createSheet("结果表", 0);

        WritableCellFormat cellFormat = new WritableCellFormat();
        cellFormat.setBackground(Colour.RED);
        //获取第一列的表头，并且添加结果字段
        Cell[] c = sheets.getRow(0);
        for (int i = 0; i < len; i++) {
            lab = new Label(i, 0, c[i].getContents()); //Label(col,row,str);
            sheet.addCell(lab);
        }
        lab = new Label(len, 0, "结果"); //Label(col,row,str);
        sheet.addCell(lab);
        for (int i = 1; i < sheets.getRows(); i++) {
            Cell[] cells = sheets.getRow(i);
            for (int j = 0; j < len; j++) {
                lab = new Label(j, i, cells[j].getContents()); //Label(col,row,str);
                sheet.addCell(lab);
            }
            if (results[i - 1].equals("成功")) {
                lab = new Label(len, i, results[i - 1]); //Label(col,row,str);
                sheet.addCell(lab);
            } else {
                lab = new Label(len, i, results[i - 1], cellFormat); //Label(col,row,str);
                sheet.addCell(lab);
            }
        }
        workbook.write();
        workbook.close();
        os.close();
    }
}
