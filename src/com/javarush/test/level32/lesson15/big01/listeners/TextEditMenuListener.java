package com.javarush.test.level32.lesson15.big01.listeners;

import com.javarush.test.level32.lesson15.big01.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10.11.2016.
 */
public class TextEditMenuListener implements MenuListener {
    private View view;

    @Override
    public void menuSelected(MenuEvent e) {
        JMenu menu = (JMenu) e.getSource();
        boolean isEnabled = view.isHtmlTabSelected();
        Component[] components = menu.getMenuComponents();
        for (Component component : components) {
            component.setEnabled(isEnabled);
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }

    public TextEditMenuListener(View view) {
        this.view = view;
    }
}
