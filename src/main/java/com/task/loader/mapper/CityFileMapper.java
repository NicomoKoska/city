package com.task.loader.mapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface CityFileMapper<T> {

  List<T> mapFromFile(String filePath) throws IOException, URISyntaxException;

}
