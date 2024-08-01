package com.example;



import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.text.SimpleDateFormat;
import java.util.Date;


public class YahooFinanceCiti {

    private static final Queue<String> dataQueue = new LinkedList<>();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                fetchAndStoreDJIA();
            }
        };

        // Schedule the task to run every 5 seconds
        timer.scheduleAtFixedRate(task, 0, 5000);
    }

    private static void fetchAndStoreDJIA() {
        try {
            // Fetch the DJIA stock data
            StockQuote djiaQuote = YahooFinance.get("^DJI").getQuote();

            // Get the current price
            BigDecimal price = djiaQuote.getPrice();
            String timestamp = sdf.format(new Date());

            // Store the stock value and timestamp in the queue
            String record = String.format("Timestamp: %s, DJIA Price: %s", timestamp, price);
            dataQueue.add(record);

            // Output the current state of the queue
            System.out.println("Current Queue: " + dataQueue);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



