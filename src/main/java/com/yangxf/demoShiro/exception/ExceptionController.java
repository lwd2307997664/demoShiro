/**
 * FileName: ExceptionController
 * Author:   linwd
 * Date:     2021/4/1 13:27
 * Description: 统一异常处理器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yangxf.demoShiro.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 〈一句话功能简述〉<br>
 * 〈统一异常处理器〉
 *
 * @author linwd
 * @create 2021/4/1
 * @since 1.0.0
 */
@ControllerAdvice
public class ExceptionController {

    /**
     * 处理授权异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public ModelAndView error(AuthorizationException e) {
        ModelAndView mv = new ModelAndView("unauthorized");
        mv.addObject("error", e.getMessage());
        return mv;
    }
}
