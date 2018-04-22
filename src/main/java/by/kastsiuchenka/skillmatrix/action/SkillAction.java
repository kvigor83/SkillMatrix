package by.kastsiuchenka.skillmatrix.action;

import by.kastsiuchenka.skillmatrix.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class SkillAction {
    private static Logger logger = LogManager.getLogger(SkillAction.class);
    private XSSFWorkbook workbook = null;
    XSSFSheet sheet = null;
//    private String id;
//    private String data;
//    private String xlsFile;

//    public SkillAction(String id, String xlsFile) {
//        this.id = id;
//        this.xlsFile = xlsFile;
//    }
//
//    public SkillAction(String id, String data, String xlsFile) {
//        this.id = id;
//        this.xlsFile = xlsFile;
//        this.data = data;
//    }


    public SkillAction() {
    }

    public void updateSkill(String id, String data, String xlsFile) throws ServiceException {
        OutputStream outputStream = null;
        InputStream inputStream = null;

        try {
            inputStream = SkillAction.class.getClassLoader().getResourceAsStream(xlsFile);
            workbook = new XSSFWorkbook(inputStream);
            CellReference cr = new CellReference(id);
            int rowIndex = cr.getRow();
            int colIndex = cr.getCol();
            sheet=workbook.getSheetAt(0);
            sheet.getRow(rowIndex).getCell(colIndex).setCellValue(data);
            inputStream.close();
            outputStream = new FileOutputStream(SkillAction.class.getClassLoader().getResource(xlsFile).getFile());
            workbook.write(outputStream);
            outputStream.close();
        } catch (FileNotFoundException e) {
            throw new ServiceException("incorrect file path: " + id, e);
        } catch (IOException e) {
            throw new ServiceException("Fail to input data from file" + id, e);
        }finally {
            if(workbook != null)
                try {
                    workbook.close();
                } catch (IOException e) {
                    logger.error("Reader/Writer closing error");
                }
        }
    }

    public void deleteSkill(String id, String xlsFile) throws ServiceException {
        InputStream inputStream = null;

        try {
            inputStream = SkillAction.class.getClassLoader().getResourceAsStream(xlsFile);
            Workbook workbook = new XSSFWorkbook(inputStream);
//            sheet=workbook.getSheetAt(0);
            CellReference cr = new CellReference(id);
            int rowIndex = cr.getRow();
            int lastRowNum = sheet.getLastRowNum();
            if (rowIndex >= 0 && rowIndex < lastRowNum) {
                sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
            }
            if (rowIndex == lastRowNum) {
                XSSFRow removingRow=sheet.getRow(rowIndex);
                if(removingRow != null) {
                    sheet.removeRow(removingRow);
                }
            }

        } catch (FileNotFoundException e) {
            throw new ServiceException("incorrect file path: " + id, e);
        } catch (IOException e) {
            throw new ServiceException("Fail to input data from file" + id, e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.error("Reader/Writer closing error");
            }
        }
    }

    public void createSkill(String id, String data, String xlsFile) throws ServiceException {
        InputStream inputStream = null;

        try {
            inputStream = SkillAction.class.getClassLoader().getResourceAsStream(xlsFile);
            Workbook workbook = new XSSFWorkbook(inputStream);
            CellReference cr = new CellReference(id);
//            int rowIndex=cr.getRow();
//            int colIndex=cr.getCol();
//            workbook.getSheetAt(0).createRow(rowIndex).getCell(colIndex).setCellValue(data);

        } catch (FileNotFoundException e) {
            throw new ServiceException("incorrect file path: " + id, e);
        } catch (IOException e) {
            throw new ServiceException("Fail to input data from file" + id, e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.error("Reader/Writer closing error");
            }
        }
    }
}
