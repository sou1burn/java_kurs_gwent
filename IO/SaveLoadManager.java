package IO;

import com.fasterxml.jackson.databind.ObjectMapper;
import Model.*;
import Controller.*;
import java.util.Map;
import java.io.File;
import java.io.IOException;

public class SaveLoadManager {
    private static final ObjectMapper mapper = new ObjectMapper();

    public void saveLogsToFile(String filePath, Map<String, ActionLogger> gameLogs) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), gameLogs);
            System.out.println("Логи игры успешно сохранены в файл: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
