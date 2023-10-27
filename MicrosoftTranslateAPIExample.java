
import java.io.IOException;

import com.google.gson.*;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.util.Scanner;

public class MicrosoftTranslateAPIExample {
    private static String key = "faaccdce54534a7ebbe552f6be1b4755";

    // location, also known as region.
    // required if you're using a multi-service or regional (not global) resource. It can be found in the Azure portal on the Keys and Endpoint page.
    private static String location = "southeastasia";


    // Instantiates the OkHttpClient.
    OkHttpClient client = new OkHttpClient();

    // This function performs a POST request.
    public String Post(String eWords) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "[{\"Text\":\"" + eWords + "\"}]");
        Request request = new Request.Builder()
                .url("https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&from=en&to=vi")
                .post(body)
                .addHeader("Ocp-Apim-Subscription-Key", key)
                // location required if you're using a multi-service or regional (not global) resource.
                .addHeader("Ocp-Apim-Subscription-Region", location)
                .addHeader("Content-type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    // This function prettifies the json response.
    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(json_text);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String eWords = sc.nextLine();
        try {
            MicrosoftTranslateAPIExample translateRequest = new MicrosoftTranslateAPIExample();
            String response = translateRequest.Post(eWords);
            System.out.println(prettify(response));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
/*import java.io.IOException;
import com.google.gson.*;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class MicrosoftTranslateAPIExample {
    private static String translationKey = "faaccdce54534a7ebbe552f6be1b4755"; // Key dịch
    private static String translationLocation = "southeastasia"; // Vị trí dịch

    // Instantiates the OkHttpClient.
    OkHttpClient client = new OkHttpClient();

    // This function performs a POST request to translate text.
    public String Translate(String eWords) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "[{\"Text\":\"" + eWords + "\"}]");
        Request request = new Request.Builder()
                .url("https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&from=en&to=vi")
                .post(body)
                .addHeader("Ocp-Apim-Subscription-Key", translationKey)
                .addHeader("Ocp-Apim-Subscription-Region", translationLocation)
                .addHeader("Content-type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String eWords = sc.nextLine();
        try {
            MicrosoftTranslateAPIExample app = new MicrosoftTranslateAPIExample();

            // Bước 1: Dịch văn bản
            String translationResponse = app.Translate(eWords);
            String translatedText = extractTranslatedText(translationResponse);

            // Hiển thị phần phiên âm
            System.out.println("Phiên âm: " + translatedText);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Hàm trích xuất phần phiên âm từ phản hồi JSON
    private static String extractTranslatedText(String response) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonResponse = (JSONObject) parser.parse(response);

            if (jsonResponse.containsKey("translations")) {
                JSONArray translations = (JSONArray) jsonResponse.get("translations");
                if (!translations.isEmpty()) {
                    Object translationObj = translations.get(0);
                    if (translationObj instanceof JSONObject) {
                        JSONObject translationObject = (JSONObject) translationObj;
                        String translatedText = (String) translationObject.get("text");
                        return translatedText;
                    }
                } else {
                    return "Không có dịch nào được tìm thấy trong phản hồi.";
                }
            } else {
                return "Không tìm thấy phần tử 'translations' trong phản hồi.";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "Lỗi khi trích xuất dữ liệu từ phản hồi JSON.";
        }
        return null;
    }
}*/