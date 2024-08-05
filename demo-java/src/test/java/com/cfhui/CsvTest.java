//package com.cfhui;
//
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class CsvTest {
//
//    public static void main(String[] args) throws IOException {
//        // Read CSV data
//        CSVReader reader = new CSVReader(new FileReader("data.csv"));
//        String[] row;
//        List<Double> data = new ArrayList<>();
//        while ((row = reader.readNext()) != null) {
//            double value = Double.parseDouble(row[0]);
//            data.add(value);
//        }
//        reader.close();
//
//        // Calculate statistics
//        DescriptiveStatistics stats = new DescriptiveStatistics(data);
//        double mean = stats.getMean();
//        double stdDev = stats.getStandardDeviation();
//
//        // Create a bar chart
//        int width = 500, height = 300;
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        Graphics2D g2d = image.createGraphics();
//
//        // Draw data bars
//        int barWidth = width / data.size();
//        for (int i = 0; i < data.size(); i++) {
//            double value = data.get(i);
//            int barHeight = (int) ((value - mean) / stdDev * height * 0.5 + height * 0.5);
//            g2d.fillRect(i * barWidth, height - barHeight, barWidth, barHeight);
//        }
//
//        // Write JPEG image
//        ImageIO.write(image, "JPEG", new File("stats.jpg"));
//    }
//}
