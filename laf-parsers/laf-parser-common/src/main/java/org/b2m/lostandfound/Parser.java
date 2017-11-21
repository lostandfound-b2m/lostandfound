package org.b2m.lostandfound;

import java.io.IOException;
import java.util.List;

public interface Parser {

    List<Item> getItemList() throws IOException;

}
