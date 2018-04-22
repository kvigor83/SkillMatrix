package by.kastsiuchenka.skillmatrix.parser;

import by.kastsiuchenka.skillmatrix.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class XLSParser {
    private static Logger parserLogger = LogManager.getLogger(XLSParser.class);
    private String sourceXLSFile;
    private String outputJSONFile;


    public XLSParser(String xlsFile, String jsonFile) {
        this.sourceXLSFile = xlsFile;
        this.outputJSONFile = jsonFile;
    }

    public void parseXLSToJSON() throws ServiceException{
        BufferedWriter writer = null;
        InputStream inputStream = null;
        int prevLevelMarker = 0;

        try {
            inputStream = XLSParser.class.getClassLoader().getResourceAsStream(sourceXLSFile);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            writer = new BufferedWriter(new FileWriter(outputJSONFile));
            writer.write("[\n");
            for (Row row : sheet) {
                for (Cell cell : row) {
                    String curSkill = cell.getStringCellValue();
                    int index = cell.getColumnIndex();
                    if (curSkill != "") {
                        if (cell.getColumnIndex() == 1 && prevLevelMarker == 0) {
                            writer.write("{\n\"name\":\"" + curSkill + "\", \n" +
                                    "\"id\":\"" + cell.getAddress() + "\"");
                            prevLevelMarker = 1;
                        } else if (cell.getColumnIndex() == 1 && prevLevelMarker == 5) {
                            writer.write("\n}\n]\n}\n]\n}\n]\n}\n]\n},\n{\"name\":\"" + curSkill + "\", \n" +
                                    "\"id\":\"" + cell.getAddress() + "\"");
                            prevLevelMarker = 1;
                        } else if (cell.getColumnIndex() == 1 && prevLevelMarker == 4) {
                            writer.write("\n}\n]\n}\n]\n}\n]\n},\n{\"name\":\"" + curSkill + "\", \n" +
                                    "\"id\":\"" + cell.getAddress() + "\"");
                            prevLevelMarker = 1;
                        } else if (cell.getColumnIndex() == 1 && prevLevelMarker == 3) {
                            writer.write("\n}\n]\n}\n]\n},\n{\"name\":\"" + curSkill + "\", \n" +
                                    "\"id\":\"" + cell.getAddress() + "\"");
                            prevLevelMarker = 1;
                        } else if (cell.getColumnIndex() == 1 && prevLevelMarker == 2) {
                            writer.write("\n}\n]\n},\n{\"name\":\"" + curSkill + "\", \n" +
                                    "\"id\":\"" + cell.getAddress() + "\"");
                            prevLevelMarker = 1;
                        } else if (cell.getColumnIndex() == 2 && prevLevelMarker == 1) {
                            writer.write(",\n\"count-nested\":" + cell.getColumnIndex() + ", \n" +
                                    "\"nested1\":[\n" +
                                    "{\n\"name\":\"" + curSkill + "\", \n" +
                                    "\"n1-id\":\"" + cell.getAddress() + "\"");
                            prevLevelMarker = 2;
                        } else if (cell.getColumnIndex() == 2 && prevLevelMarker == 5) {
                            writer.write("\n}\n]\n}\n]\n}\n]\n},\n{\n\"name\":\"" + curSkill + "\", \n" +
                                    "\"n1-id\":\"" + cell.getAddress() + "\"");
                            prevLevelMarker = 2;
                        } else if (cell.getColumnIndex() == 2 && prevLevelMarker == 4) {
                            writer.write("\n}\n]\n}\n]\n},\n{\n\"name\":\"" + curSkill + "\", \n" +
                                    "\"n1-id\":\"" + cell.getAddress() + "\"");
                            prevLevelMarker = 2;
                        } else if (cell.getColumnIndex() == 2 && prevLevelMarker == 3) {
                            writer.write("\n}\n]\n},\n{\n\"name\":\"" + curSkill + "\", \n" +
                                    "\"n1-id\":\"" + cell.getAddress() + "\"");
                            prevLevelMarker = 2;
                        } else if (cell.getColumnIndex() == 3 && prevLevelMarker == 2) {
                            writer.write(",\n\"count-nested\":" + cell.getColumnIndex() + ", \n" +
                                    "\"nested2\":[\n" +
                                    "{\n\"name\":\"" + curSkill + "\", \n" +
                                    "\"n2-id\":\"" + cell.getAddress() + "\"");
                            prevLevelMarker = 3;
                        } else if (cell.getColumnIndex() == 3 && prevLevelMarker == 5) {
                            writer.write("\n}\n]\n}\n]\n},\n{\n\"name\":\"" + curSkill + "\", \n" +
                                    "\"n2-id\":\"" + cell.getAddress() + "\"");
                            prevLevelMarker = 3;
                        } else if (cell.getColumnIndex() == 3 && prevLevelMarker == 4) {
                            writer.write("\n}\n]\n},\n{\n\"name\":\"" + curSkill + "\", \n" +
                                    "\"n2-id\":\"" + cell.getAddress() + "\"");
                            prevLevelMarker = 3;
                        } else if (cell.getColumnIndex() == 4 && prevLevelMarker == 3) {
                            writer.write(",\n\"count-nested\":" + cell.getColumnIndex() + ", \n" +
                                    "\"nested3\":[\n" +
                                    "{\n\"name\":\"" + curSkill + "\", \n" +
                                    "\"n3-id\":\"" + cell.getAddress() + "\"");
                            prevLevelMarker = 4;
                        } else if (cell.getColumnIndex() == 4 && prevLevelMarker == 5) {
                            writer.write("\n}\n]\n},\n{\n\"name\":\"" + curSkill + "\", \n" +
                                    "\"n3-id\":\"" + cell.getAddress() + "\"");
                            prevLevelMarker = 4;
                        } else if (cell.getColumnIndex() == 5 && prevLevelMarker == 4) {
                            writer.write(",\n\"count-nested\":" + cell.getColumnIndex() + ", \n" +
                                    "\"nested4\":[\n" +
                                    "{\n\"name\":\"" + curSkill + "\", \n" +
                                    "\"n4-id\":\"" + cell.getAddress() + "\"");
                            prevLevelMarker = 5;
                        } else if (cell.getColumnIndex() == 2 && prevLevelMarker == 2) {
                            writer.write("\n},\n{\"name\":\"" + curSkill + "\", \n" +
                                    "\"n1-id\":\"" + cell.getAddress() + "\"");
                        } else if (cell.getColumnIndex() == 3 && prevLevelMarker == 3) {
                            writer.write("\n},\n{\"name\":\"" + curSkill + "\", \n" +
                                    "\"n2-id\":\"" + cell.getAddress() + "\"");
                        } else if (cell.getColumnIndex() == 4 && prevLevelMarker == 4) {
                            writer.write("\n},\n{\"name\":\"" + curSkill + "\", \n" +
                                    "\"n3-id\":\"" + cell.getAddress() + "\"");
                        } else if (cell.getColumnIndex() == 5 && prevLevelMarker == 5) {
                            writer.write("\n},\n{\"name\":\"" + curSkill + "\", \n" +
                                    "\"n4-id\":\"" + cell.getAddress() + "\"");
                        }
                    }
                    writer.flush();
                }
            }
            if (prevLevelMarker == 5) {
                writer.write("\n}\n]\n}\n]\n}\n]\n}\n]\n}\n]");
            } else if (prevLevelMarker == 4) {
                writer.write("\n}\n]\n}\n]\n}\n]\n}\n]");
            } else if (prevLevelMarker == 3) {
                writer.write("\n}\n]\n}\n]\n}\n]");
            } else if (prevLevelMarker == 2) {
                writer.write("\n}\n]\n}\n]");
            } else if (prevLevelMarker == 1) {
                writer.write("\n}\n]");
            }


        } catch (FileNotFoundException e) {
            throw new ServiceException("incorrect file path: " + sourceXLSFile, e);
        } catch (IOException e) {
            throw new ServiceException("Fail to input data from file" + sourceXLSFile, e);
        } finally {
            try {
                inputStream.close();
                writer.close();
            } catch (IOException e) {
                parserLogger.error("Reader/Writer closing error");
            }
        }
    }
}
