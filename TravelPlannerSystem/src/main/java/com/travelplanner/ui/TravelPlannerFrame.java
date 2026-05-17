package com.travelplanner.ui;

import com.travelplanner.model.City;
import com.travelplanner.repository.CityRepository;

import javax.swing.*;
import java.awt.*;

public class TravelPlannerFrame extends JFrame {

    public TravelPlannerFrame() {

        setTitle("Travel Planner System");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();

        JComboBox<String> sortCombo = new JComboBox<>(
                new String[]{
                        "Sort By Name",
                        "Sort By Population",
                        "Sort By Area"
                }
        );

        JComboBox<String> weatherCombo = new JComboBox<>(
                new String[]{
                        "SUNNY",
                        "CLOUDY",
                        "RAINY",
                        "SNOWY"
                }
        );

        topPanel.add(sortCombo);
        topPanel.add(weatherCombo);

        add(topPanel, BorderLayout.NORTH);

        DefaultListModel<String> model = new DefaultListModel<>();

        for(City city : CityRepository.getInstance().getCities()) {
            model.addElement(city.toString());
        }

        JList<String> cityList = new JList<>(model);

        add(new JScrollPane(cityList), BorderLayout.CENTER);

        setVisible(true);
    }
}