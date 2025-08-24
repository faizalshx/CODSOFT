import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.text.DecimalFormat;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] currencies = {"USD", "EUR", "INR", "GBP", "JPY", "AUD", "CAD", "CNY"};
        DecimalFormat df = new DecimalFormat("#.##");

        try {
            boolean keepRunning = true;

            while (keepRunning) {
                // Show available currencies
                System.out.println("\nAvailable Currencies:");
                for (int i = 0; i < currencies.length; i++) {
                    System.out.println((i + 1) + ". " + currencies[i]);
                }

                // Choose base currency
                System.out.print("Choose base currency (1-" + currencies.length + "): ");
                int baseChoice = scanner.nextInt();
                String baseCurrency = currencies[baseChoice - 1];

                // Choose target currency
                System.out.print("Choose target currency (1-" + currencies.length + "): ");
                int targetChoice = scanner.nextInt();
                String targetCurrency = currencies[targetChoice - 1];

                // Enter amount
                System.out.print("Enter amount: ");
                double amount = scanner.nextDouble();

                // Same currency case
                if (baseCurrency.equals(targetCurrency)) {
                    System.out.println(amount + " " + baseCurrency + " = " + df.format(amount) + " " + targetCurrency);
                } else {
                    // API URL (get rate for 1 unit)
                    String urlStr = "https://api.frankfurter.app/latest?from=" + baseCurrency + "&to=" + targetCurrency;

                    // Connect to API
                    URL url = new URL(urlStr);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");

                    // Read response
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder content = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    in.close();
                    conn.disconnect();

                    // Parse JSON
                    JsonObject json = JsonParser.parseString(content.toString()).getAsJsonObject();
                    if (json.has("rates") && json.getAsJsonObject("rates").has(targetCurrency)) {
                        double rate = json.getAsJsonObject("rates").get(targetCurrency).getAsDouble();
                        double result = amount * rate;

                        System.out.println("\nLive Rate: 1 " + baseCurrency + " = " + df.format(rate) + " " + targetCurrency);
                        System.out.println(amount + " " + baseCurrency + " = " + df.format(result) + " " + targetCurrency);
                    } else {
                        System.out.println("Error: Could not retrieve exchange rate.");
                    }
                }

                // Ask to continue
                System.out.print("\nDo you want to convert again? (yes/no): ");
                String choice = scanner.next().toLowerCase();
                if (!choice.equals("yes")) {
                    keepRunning = false;
                    System.out.println("Thank you for using Currency Converter!");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
