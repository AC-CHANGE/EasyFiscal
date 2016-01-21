package EF;

//DB


import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class DbWorker {



    private String HOST = "";
    private String USERNAME = "";
    private String PASSWORD = "";

    private Connection connection;

    public DbWorker() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        SAXPars saxp = new SAXPars();
        try {
            parser.parse(new File("config/DBconfig.xml"), saxp);

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setHost(saxp.getHost());
        setPassword(saxp.getPassword());
        setUsername(saxp.getUsername());

        try {
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    String sqlAskKassi(String what,  String zn) throws SQLException {
        getConnection();
        String query = "SELECT "+what+" FROM fiscal.kassi where zavNum = '"+zn+"'";
        String q;
        q = "";
        Statement statement = getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()){
            q = rs.getString(1);

        }
        statement.close();
        rs.close();
        return q;
    }


    LinkedList<String> sqlAskKassiByOwners (String owner) throws SQLException {

        LinkedList<String> names = new LinkedList<String>();
        String query = "SELECT zavNum FROM fiscal.kassi where ownerName = '"+owner+"'";

        Statement statement = getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            names.add(rs.getString("zavNum"));


        }
        statement.close();
        rs.close();
        return names;
    }

    String sqlAskOwners(String what,  String name) throws SQLException {
        String query = "SELECT "+what+" FROM fiscal.owners where `name` = '"+name+"'";
        String q;
        q = "";
        Statement statement = getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()){
            q = rs.getString(1);

        }
        statement.close();
        rs.close();
        return q;
    }


    LinkedList<String> ownerList() throws SQLException {

        LinkedList<String> names = new LinkedList<String>();
        String query = "SELECT name FROM fiscal.owners";
        Statement statement = getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            names.add(rs.getString("name"));


        }
        statement.close();
        rs.close();


        return names;
    }

    LinkedList<String> fiscList() throws SQLException {

        LinkedList<String> names = new LinkedList<String>();
        String query = "SELECT zavNum FROM fiscal.kassi";
        Statement statement = getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            names.add(rs.getString("zavNum"));
        }
        statement.close();
        rs.close();
        return names;
    }


    void addNewOwner(String name, String adres, String edrpo, String ipn) throws SQLException {
        String query = "INSERT INTO `fiscal`.`owners` (`name`, `adres`, `edrpo`, `IPN`) VALUES ('" + name + "', '" + adres + "', '" + edrpo + "', '" + ipn + "') ON DUPLICATE KEY UPDATE `adres` = '" + adres + "', `edrpo` = '" + edrpo + "', `IPN` = '" + ipn + "'";
        Statement statement = getConnection().createStatement();
        statement.executeUpdate(query);
        statement.close();


    }

    void addNewOwner(String name, String adres, String edrpo) throws SQLException {
        String query = "INSERT INTO `fiscal`.`owners` (`name`, `adres`, `edrpo`, `IPN`) VALUES ('" + name + "', '" + adres + "', '" + edrpo + "', '') ON DUPLICATE KEY UPDATE `adres` = '" + adres + "', `edrpo` = '" + edrpo + "', `IPN` = ''";
        Statement statement = getConnection().createStatement();
        statement.executeUpdate(query);
        statement.close();


    }


    void addNewFiscal(String zavNum, String fiscNum, String dateCreate, String dateActive, String ownerName, String model, String modelPO, String dateFirstActive) throws SQLException {
        String query = "INSERT INTO `fiscal`.`kassi` (`zavNum`, `fiscNum`, `dateCreate`, `dateActive`, `ownerName`, `model`, `modelPO`, `dateFirstActive`)" +
                " VALUES ('" + zavNum + "', '" + fiscNum + "', '" + dateCreate + "', '" + dateActive + "', '" + ownerName + "', " +
                "'" + model + "', '" + modelPO + "', '" + dateFirstActive + "')" +
                "ON DUPLICATE KEY UPDATE `fiscNum` = '" + fiscNum + "',`dateCreate` = '" + dateCreate + "', `dateActive` = '" + dateActive + "', `ownerName` = '" + ownerName + "', `model` = '" + model + "', `modelPO` = '" + modelPO + "', `dateFirstActive` = '" + dateFirstActive + "' ";
        Statement statement = getConnection().createStatement();
        statement.executeUpdate(query);
        statement.close();



    }

    void delOwner(String ownerName) throws SQLException {
        String query = "DELETE FROM `fiscal`.`owners` where name = '" + ownerName + "'";
        Statement statement = getConnection().createStatement();
        statement.executeUpdate(query);
        statement.close();

    }

    int ownersHadKass(String ownName) throws SQLException {
        int quant = 0;
        String query = "SELECT COUNT(*) AS quant FROM `fiscal`.`kassi` where ownerName = '" + ownName + "'";
        Statement statement = getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        rs.next();
        quant = rs.getInt(1);
        rs.close();
        statement.close();
        return quant;
    }

    void deleteFisc (String zavNum) throws SQLException {
        String query = "DELETE FROM `fiscal`.`kassi` WHERE `zavNum` = '" + zavNum + "'";
        Statement statement = getConnection().createStatement();
        statement.executeUpdate(query);
        statement.close();
    }

    int ownersCount() throws SQLException {
        int quant = 0;
        String query = "SELECT COUNT(*) AS quant FROM `fiscal`.`owners`";
        Statement statement = getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        rs.next();
        quant = rs.getInt(1);
        rs.close();
        statement.close();
        return quant;
    }

    int fiscCount() throws SQLException {
        int quant = 0;
        String query = "SELECT COUNT(*) AS quant FROM `fiscal`.`kassi`";
        Statement statement = getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        rs.next();
        quant = rs.getInt(1);
        rs.close();
        statement.close();
        return quant;
    }

    public void generateXls(String tablename, String dir, String filename) throws SQLException, FileNotFoundException, IOException {

        // Create new Excel workbook and sheet
        HSSFWorkbook xlsWorkbook = new HSSFWorkbook();
        HSSFSheet xlsSheet = xlsWorkbook.createSheet();
        short rowIndex = 0;

        // Execute SQL query
        PreparedStatement stmt =
                connection.prepareStatement("select * from " + tablename);
        ResultSet rs = stmt.executeQuery();

        // Get the list of column names and store them as the first
        // row of the spreadsheet.
        ResultSetMetaData colInfo = rs.getMetaData();
        List<String> colNames = new ArrayList<String>();
        HSSFRow titleRow = xlsSheet.createRow(rowIndex++);

        for (int i = 1; i <= colInfo.getColumnCount(); i++) {
            colNames.add(colInfo.getColumnName(i));
            titleRow.createCell((short) (i-1)).setCellValue(
                    new HSSFRichTextString(colInfo.getColumnName(i)));
            xlsSheet.setColumnWidth((short) (i-1), (short) 4000);
        }

        // Save all the data from the database table rows
        while (rs.next()) {
            HSSFRow dataRow = xlsSheet.createRow(rowIndex++);
            short colIndex = 0;
            for (String colName : colNames) {
                dataRow.createCell(colIndex++).setCellValue(
                        new HSSFRichTextString(rs.getString(colName)));
            }
        }

        // Write to disk
        xlsWorkbook.write(new FileOutputStream(dir+filename));
    }

    public void setHost (String Host){
        this.HOST = Host;
    }
    public void setUsername (String Username){
        this.USERNAME = Username;
    }
    public void setPassword (String Password){
        this.PASSWORD = Password;
    }





}












