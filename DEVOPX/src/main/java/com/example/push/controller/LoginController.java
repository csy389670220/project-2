package com.example.push.controller;

import com.example.push.mapper.SysUserMapper;
import com.example.push.model.SysUser;
import com.example.push.util.CheckUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Farben
 * @description: LoginController 系统登录页面
 * @create: 2019/8/30-13:36
 **/
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    SysUserMapper sysUserMapper;
    /**
     * 访问项目根路径
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        Subject subject = SecurityUtils.getSubject();
        String loginName=(String) subject.getPrincipal();
        if (loginName == null){
            return "redirect:login";
        }else{
            return "redirect:index";
        }
    }

    /**
     * 跳转到login页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView view = new ModelAndView("login");
        return view;
    }


    /**
     * 跳转到index页面
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        Subject subject = SecurityUtils.getSubject();
        //查询用户信息
        String loginName=(String) subject.getPrincipal();
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        String chinaName= (String) SecurityUtils.getSubject().getSession().getAttribute("chinaName");
        //非匿名登录条件下，sysId为空需要查询数据库更新数据
        if(CheckUtil.isEmpty(sysId)&&!CheckUtil.isEmpty(loginName)){
            SysUser user =sysUserMapper.selectByLoginName(loginName);
            sysId=user.getId();
            chinaName=user.getChinaName();
            SecurityUtils.getSubject().getSession().setAttribute("sysId",sysId);
            SecurityUtils.getSubject().getSession().setAttribute("chinaName",chinaName);
            SecurityUtils.getSubject().getSession().setAttribute("sysToken",user.getSysToken());
        }

        ModelAndView view = new ModelAndView("index");
        view.addObject("loginName", loginName);
        view.addObject("chinaName", chinaName);
        return view;
    }


    /**
     * 用户登录
     *
     * @param request
     * @param loginName
     * @param passWord
     * @param captcha
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(HttpServletRequest request, String loginName, String passWord, boolean rememberMe, String captcha, Model model) {
        //如果有点击  记住我
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginName, passWord, rememberMe);
        //UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            //登录操作
            subject.login(usernamePasswordToken);
            return "redirect:index";
        } catch (Exception e) {
            //登录失败从request中获取shiro处理的异常信息 shiroLoginFailure:就是shiro异常类的全类名
            String exception = (String) request.getAttribute("shiroLoginFailure");
            String msg=exception;
            if (e instanceof UnknownAccountException) {
                msg="用户名或密码错误!";
                model.addAttribute("msg", msg);
            }
            if (e instanceof IncorrectCredentialsException) {
                msg="用户名或密码错误！";
                model.addAttribute("msg", msg);
            }
            if (e instanceof LockedAccountException) {
                msg="账号已被锁定,请联系管理员！";
                model.addAttribute("msg", msg);
            }
            logger.info("登录系统错误,登录名:{},错误原因:{}", loginName, msg);
            //返回登录页面
            return "login";
        }
    }


    /**
     * 跳转到无权限页面
     *
     * @return
     */
    @RequestMapping("/unauthorized")
    public ModelAndView unauthorized() {
        ModelAndView view = new ModelAndView("unauthorized");
        return view;
    }

}
