package std;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alex on 17.09.2016.
 */
public class XlsxDataProviders {
    public XlsxDataProviders() {
    }

    @DataProvider
    public static Iterator<Object[]> xlsxDataProvider(Method m) throws IOException {
        if (!m.isAnnotationPresent(XlsxDataSource.class)) {
            throw new Error("There is no @XlsxDataSource annotation on method " + m);
        } else {
            XlsxDataSource dataSource = m.getAnnotation(XlsxDataSource.class);
            File excel = new File(dataSource.value());
            FileInputStream fis = new FileInputStream(excel);
            XSSFWorkbook book = new XSSFWorkbook(fis);

            XSSFSheet sheet = book.getSheetAt(0);

            Iterator<Row> itr = sheet.iterator();

            List<Object[]> files = new ArrayList<Object[]>();

            // Iterating over Excel file
            while (itr.hasNext()) {
                Row row = itr.next();

                // Iterating over each column of Excel file
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();

                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            files.add(new Object[]{cell.getStringCellValue()});
                            break;

                    }
                }
            }
            return files.iterator();
        }
    }
}
