package com.bernie.concurrency.example.threadLocal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ThreadLocalController
 *
 * @Description TODO
 * @Author Bernie【xiongyun100@163.com】
 * @Date 2020/2/26
 */
@Slf4j
@Controller
@RequestMapping("/threadLocal")
public class ThreadLocalController {

    @RequestMapping("/test")
    @ResponseBody
    public Long test(){
        log.info("Do controller,{}",RequestHolder.get());
        return RequestHolder.get();
    }
}
