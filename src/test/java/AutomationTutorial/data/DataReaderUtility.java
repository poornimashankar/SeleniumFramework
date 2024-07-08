package AutomationTutorial.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReaderUtility {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")
				+ "\\Users\\poorn\\Workspace Spring\\SeleniumFrameworkDesign\\src\\test\\java\\AutomationTutorial\\data\\purchaseOrder.json"),
				StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<HashMap<String, String>>> typeRef = new TypeReference<List<HashMap<String, String>>>() {
		};
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, typeRef);
		return data;
	}
}
