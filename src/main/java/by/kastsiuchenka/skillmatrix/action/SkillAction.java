package by.kastsiuchenka.skillmatrix.action;

import by.kastsiuchenka.skillmatrix.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class SkillAction {
    private static Logger logger = LogManager.getLogger(SkillAction.class);
    private String id;
    private String data;
    private String xlsFile;

    public SkillAction(String id, String xlsFile) {
        this.id = id;
        this.xlsFile = xlsFile;
    }

    public SkillAction(String id, String data, String xlsFile) {
        this.id = id;
        this.xlsFile = xlsFile;
        this.data = data;
    }

    public void updateSkill() throws ServiceException{
        InputStream inputStream = null;

        try {
            inputStream = SkillAction.class.getClassLoader().getResourceAsStream(xlsFile);
            Workbook workbook = new XSSFWorkbook(inputStream);
            CellReference cr = new CellReference(id);
            int rowIndex=cr.getRow();
            int colIndex=cr.getCol();
            workbook.getSheetAt(0).createRow(rowIndex).getCell(colIndex).setCellValue(data);

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
    public void deleteSkill() throws ServiceException{
        InputStream inputStream = null;

        try {
            inputStream = SkillAction.class.getClassLoader().getResourceAsStream(xlsFile);
            Workbook workbook = new XSSFWorkbook(inputStream);
            CellReference cr = new CellReference(id);
            int rowIndex=cr.getRow();
            int colIndex=cr.getCol();
            workbook.getSheetAt(0).createRow(rowIndex).getCell(colIndex).removeCellComment();

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
    public void createSkill() throws ServiceException{
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
