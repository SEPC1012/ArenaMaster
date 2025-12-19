package com.mycompany.arenamaste2;

import java.util.ArrayList;
import java.util.List;

public interface Navigable {
    String getName();
    List<Navigable> getChildren();

}