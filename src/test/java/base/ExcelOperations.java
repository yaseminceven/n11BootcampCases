package base;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelOperations {
    public static String path = ".\\storesList.xlsx";                  //set path to write the file
    private static String storeUrl;
    private static XSSFWorkbook workbook = new XSSFWorkbook();        //create one workbook

    //method to write into excel
    public static void writeAllStoresToExcel(List<String> storeNames, List<String> storeURLs){
        XSSFSheet spreadsheet = workbook.createSheet();    //create excel sheet
        Row header = spreadsheet.createRow(0);
        header.createCell(0).setCellValue("storeName");    //set first row first column value
        header.createCell(1).setCellValue("URL");          //set first row second column value

        int rowNum=1;
        for (String name: storeNames) {
            Row row = spreadsheet.createRow(rowNum);
            row.createCell(0).setCellValue(name);
            row.createCell(1).setCellValue(storeURLs.get(rowNum-1));
            rowNum++;
        }

        File file = new File(path);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //read second store starts with S from excel and obtain the store url
    public static String getStoresFromExcel(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);         //obtaining bytes from the file
        XSSFWorkbook workbook = new XSSFWorkbook(fis);           //creating workbook to read from excel
        XSSFSheet sheet = workbook.getSheetAt(21);          //obtain sheet that contains stores starts with S
        Row row=sheet.getRow(2);
        Cell cell=row.getCell(1);
        storeUrl = cell.getStringCellValue();                   //obtain the URL from excel
        return storeUrl;
    }
}
