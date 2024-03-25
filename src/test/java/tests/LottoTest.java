package tests;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoTest {
    public static void main(String[] args) {
           // путь к файлу CSV на macOS может выглядеть так: String filePath = "src/file/Lotto.csv";
    String filePath = "src\\file\\Lotto.csv";
    int startRow = 2;
    int startColumn = 3;
    int endColumn = 8;
    int endRow = 500;
    
    if(filePath.endsWith(".csv")){
        readCSV(filePath, startRow,startColumn,endColumn, endRow);
    } else if (filePath.endsWith(".xlsx")) {
        
    }else {
        System.out.println("Unknown format...");
    }

    }
    private static void readCSV(String filePath,
                                int startRow, int startColumn, int endColumn, int endRow){
        Map<Integer, Integer> valueCounts = new HashMap<>();
        try (CSVReader reader = new CSVReader(new BufferedReader(new FileReader(filePath)))){
            List<String[]> lines = reader.readAll();
            for (int i = startRow-1; i <Math.min(endRow, lines.size()); i++){
                String[] line  = lines.get(i);
                for(int j = startColumn-1; j < Math.min(endColumn, line.length);j++){
                    if(!line[j].isEmpty()){
                        int value = Integer.parseInt(line[j]);
                        valueCounts.put(value, valueCounts.getOrDefault(value,0)+1);
                    }
                }

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
        printValueMap(valueCounts);

    }

    private static void printValueMap(Map<Integer,Integer> valueCounts){
        System.out.println("Встречаемость значений: ");
        valueCounts.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entity -> System.out.println(entity
                        .getKey()+" : " + entity.getValue()+ " раз"));
    }






}
