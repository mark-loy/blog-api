package com.markloy.markblog.controller;

import com.markloy.markblog.dto.ResultDTO;
import com.markloy.markblog.service.TimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/private/api")
public class TimeLineController {

    @Autowired
    private TimeLineService timeLineService;

    /**
     * 获取时间线数据
     * @return
     */
    @GetMapping("/timeLine")
    public ResultDTO timeLine() throws IOException, ClassNotFoundException {
        Map<String, Object> map = timeLineService.getTimeLineData();
        return ResultDTO.success(map);
    }

}
