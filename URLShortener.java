import java.util.HashMap;
import java.util.Scanner;

public class URLShortener {

    private static final String BASE_URL = "http://short.ly/";
    private static final HashMap<String, String> urlMap = new HashMap<>();
    private static final HashMap<String, String> reverseMap = new HashMap<>();
    private static int counter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Shorten URL");
            System.out.println("2. Retrieve Original URL");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the URL to shorten: ");
                    String longUrl = scanner.nextLine();
                    String shortUrl = shortenUrl(longUrl);
                    System.out.println("Shortened URL: " + shortUrl);
                    break;
                case 2:
                    System.out.print("Enter the shortened URL: ");
                    String shortKey = scanner.nextLine();
                    String originalUrl = getOriginalUrl(shortKey);
                    if (originalUrl != null) {
                        System.out.println("Original URL: " + originalUrl);
                    } else {
                        System.out.println("Shortened URL not found!");
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static String shortenUrl(String longUrl) {
        if (reverseMap.containsKey(longUrl)) {
            return BASE_URL + reverseMap.get(longUrl);
        }
        String shortKey = "u" + counter++;
        urlMap.put(shortKey, longUrl);
        reverseMap.put(longUrl, shortKey);
        return BASE_URL + shortKey;
    }

    private static String getOriginalUrl(String shortUrl) {
        if (shortUrl.startsWith(BASE_URL)) {
            String shortKey = shortUrl.replace(BASE_URL, "");
            return urlMap.get(shortKey);
        }
        return null;
    }
}
