package com.travelplanner.ui;

import com.formdev.flatlaf.FlatDarkLaf; // Modern Dark Mode için
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TravelPlannerFrame extends JFrame {
    private JTable cityTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> sortCombo;
    private JComboBox<String> filterCombo;
    
    // Test verileri (Eğer senin kendi listen varsa burayı ona göre uyarlayabilirsin)
    private List<String[]> cities;

    public TravelPlannerFrame() {
        // FlatLaf Temasını Uygula (Sistem genelini güzelleştirir)
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Travel Planner System");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Verileri hazırla
        cities = new ArrayList<>();
        cities.add(new String[]{"Ankara", "SUNNY", "5.8M"});
        cities.add(new String[]{"Istanbul", "RAINY", "15.9M"});
        cities.add(new String[]{"Izmir", "CLOUDY", "4.5M"});

        // --- ÜST PANEL (Kontroller) ---
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));

        sortCombo = new JComboBox<>(new String[]{"Sort By Population", "Sort By Name"});
        filterCombo = new JComboBox<>(new String[]{"ALL", "SUNNY", "RAINY", "CLOUDY"});
        
        // Stil dokunuşları
        sortCombo.putClientProperty("JComponent.roundRect", true);
        filterCombo.putClientProperty("JComponent.roundRect", true);

        topPanel.add(new JLabel("Sort:"));
        topPanel.add(sortCombo);
        topPanel.add(new JLabel("Weather:"));
        topPanel.add(filterCombo);
        add(topPanel, BorderLayout.NORTH);

        // --- ORTA PANEL (Modern Tablo) ---
        String[] columnNames = {"City Name", "Weather Status", "Population"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hücreler çift tıklamayla düzenlenemesin
            }
        };
        
        cityTable = new JTable(tableModel);
        cityTable.setRowHeight(30); // Satırları daha geniş ve ferah yapalım
        cityTable.setShowGrid(true);
        cityTable.setFillsViewportHeight(true);
        
        JScrollPane scrollPane = new JScrollPane(cityTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
        add(scrollPane, BorderLayout.CENTER);

        // İlk veriyi yükle
        updateTable(cities);

        // --- BUTON AKSİYONLARI (Sıralama ve Filtreleme) ---
        ActionListener actionListener = e -> {
            String sortType = (String) sortCombo.getSelectedItem();
            String filterType = (String) filterCombo.getSelectedItem();

            List<String[]> filteredList = new ArrayList<>();
            for (String[] city : cities) {
                if (filterType.equals("ALL") || city[1].equalsIgnoreCase(filterType)) {
                    filteredList.add(city);
                }
            }

            if (sortType.contains("Population")) {
                // Nüfusa göre sırala (Büyükten küçüğe)
                filteredList.sort((a, b) -> b[2].compareTo(a[2]));
            } else {
                // İsme göre sırala (A-Z)
                filteredList.sort((a, b) -> a[0].compareTo(b[0]));
            }

            updateTable(filteredList);
        };

        sortCombo.addActionListener(actionListener);
        filterCombo.addActionListener(actionListener);

        setVisible(true);
    }

    private void updateTable(List<String[]> dataList) {
        tableModel.setRowCount(0); // Eski verileri temizle
        for (String[] row : dataList) {
            tableModel.addRow(row); // Yeni verileri bas
        }
    }
}
