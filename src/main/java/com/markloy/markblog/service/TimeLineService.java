package com.markloy.markblog.service;

import java.io.IOException;
import java.util.Map;

public interface TimeLineService {


    Map<String, Object> getTimeLineData() throws IOException, ClassNotFoundException;
}
