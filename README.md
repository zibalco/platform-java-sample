
  

# Zibal Payment Platform Java Code Sample

  

For this code sample we used JSON-java repository to parse json.

You can get the .jar file from here:

https://repo1.maven.org/maven2/org/json/json/20200518/json-20200518.jar

  

## Wallet balance code sample

```java

  

private  static  HttpURLConnection  connection;

  

public  static  void  main(String[] args) {

BufferedReader  reader;

String  line;

StringBuffer  responseContent = new  StringBuffer();

  

StringBuilder  response = null;

try {

URL  url = new  URL("https://api.zibal.ir/v1/wallet/balance");

connection = (HttpURLConnection) url.openConnection();

// Request setup

connection.setRequestMethod("POST");

connection.setRequestProperty("Content-Type", "application/json; utf-8");

connection.setRequestProperty("Accept", "application/json");

connection.setRequestProperty("Authorization", "Bearer: Your Access Token");

connection.setDoOutput(true);

String  jsonInputString = "{" +

"\"id\": \"1010101\"" +

"}";

try (OutputStream  os = connection.getOutputStream()) {

byte[] input = jsonInputString.getBytes("utf-8");

os.write(input, 0, input.length);

}

try (BufferedReader  br = new  BufferedReader(

new  InputStreamReader(connection.getInputStream(), "utf-8"))) {

response = new  StringBuilder();

String  responseLine = null;

while ((responseLine = br.readLine()) != null) {

response.append(responseLine.trim());

}

}

int  status = connection.getResponseCode();

System.out.println(status);

  
  

} catch (MalformedURLException  e) {

e.printStackTrace();

} catch (IOException  e) {

e.printStackTrace();

}

  

// Parse response JSON into JSONObject

JSONObject  result = new  JSONObject(response.toString());

System.out.println(result.getJSONObject("data").getInt("balance"));

  

}

  

```