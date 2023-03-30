package api.utilities;
import org.testng.annotations.DataProvider;

import java.io.IOException;
public class DataProviders
{
    @DataProvider(name = "Data")
    public Object[][] getAllData() throws IOException
    {
        Object[][] data;
        String path = "testData/Userdata.xlsx";
        XLUtility xl = new XLUtility(path);

        int rowNum = xl.getRowCount("Sheet1");
        int colCount = xl.getCellCount("Sheet1", 1);
        data = new Object[rowNum][colCount];
        for (int i = 1; i <= rowNum; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = xl.getCellData("Sheet1", i, j);
            }
        }
        System.out.println(data);
        return data;
    }
    @DataProvider(name = "UserNames")
    public Object[] getUserNames() throws IOException
    {
        Object[] apiData;
        String path = "testData/Userdata.xlsx";
        XLUtility xl = new XLUtility(path);
        int rowNum = xl.getRowCount("Sheet1");
        apiData = new Object[rowNum];
        for (int i = 1; i <= rowNum; i++) {
            apiData[i - 1] = xl.getCellData("Sheet1", i, 1);
        }
        return apiData;
    }
}
