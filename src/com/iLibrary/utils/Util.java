package com.iLibrary.utils;

import com.iLibrary.models.User;

import java.awt.*;
import java.util.Comparator;
import java.util.List;

import javax.swing.JComponent;

public class Util {
    public static final Color DARK_BLUE = Color.BLUE.darker();

    public static final Dimension WINDOW_DIMENSION = new Dimension(639, 500);

    public static Font makeSmallFont(Font f) {
        return new Font(f.getName(), f.getStyle(), (f.getSize() - 2));
    }

    public static void adjustLabelFont(JComponent label, Color color, boolean bigger) {
        if (bigger) {
            Font f = new Font(label.getFont().getName(),
                    label.getFont().getStyle(), (label.getFont().getSize() + 2));
            label.setFont(f);
        } else {
            Font f = new Font(label.getFont().getName(),
                    label.getFont().getStyle(), (label.getFont().getSize() - 2));
            label.setFont(f);
        }
        label.setForeground(color);

    }

    /**
     * Sorts a list of numeric strings in natural number order
     */
    public static List<String> numericSort(List<String> list) {
        list.sort(new NumericSortComparator());
        return list;
    }

    static class NumericSortComparator implements Comparator<String> {
        @Override
        public int compare(String s, String t) {
            if (isNumeric(s) || isNumeric(t))
                throw new IllegalArgumentException("Input list has non-numeric characters");
            int sInt = Integer.parseInt(s);
            int tInt = Integer.parseInt(t);
            return Integer.compare(sInt, tInt);
        }
    }

    public static boolean isNumeric(String s) {
        if (s == null) return true;
        try {
            Integer.parseInt(s);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public static User findUser(List<User> list, User user) {
        for (User u : list) {
            if (u.equals(user)) return u;
        }
        return null;
    }
}
