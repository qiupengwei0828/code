package com.sys.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.base.controller.BaseController;
import com.base.utils.comm.Const;
import com.base.utils.comm.MD5;
import com.sys.service.user.UserService;

@Controller
public class LoginController extends BaseController<Object> {

	@Autowired
	private UserService userService;

	/**
	 * 访问登录页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loginIndex")
	public ModelAndView toLogin() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sys/login/login");
		return mv;
	}

	/**
	 * 单点登录的简单实现 调用方式：/ssoLogin/{userId}/{token}/访问URL
	 * 
	 * @ResponseBody 请求登录，验证用户, produces = "application/json;charset=UTF-8"
	 */
	@RequestMapping(value = "/ssoLogin/{userId}/{token}")
	public String ssoLogin(@PathVariable String userId, @PathVariable String token, @RequestParam(required = true) String url, String relogin, Model model) throws Exception {
		String errInfo = "";
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		String message = "";
		if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(token)) {
			errInfo = "nullup"; // 缺少用户名或密码
		} else {
			// shiro加入身份验证
			UsernamePasswordToken uptToken = new UsernamePasswordToken(userId, token.toUpperCase());
			uptToken.setRememberMe(true);
			try {
				if (!currentUser.isAuthenticated()) {
					currentUser.login(uptToken);
				}
				// 记录登录日志
				addLog(OP_CLASS_LOGIN, OP_TYPE_LOGIN);
			} catch (UnknownAccountException uae) {
				errInfo = "usererror";// 用户名或密码有误
			} catch (IncorrectCredentialsException ice) {
				errInfo = "usererror"; // 密码错误
			} catch (LockedAccountException lae) {
				errInfo = "inactive";// 未激活
			} catch (ExcessiveAttemptsException eae) {
				errInfo = "attemptserror";// 错误次数过多
			} catch (AuthenticationException ae) {
				errInfo = "codeerror";// 验证未通过
			}
			// 验证是否登录成功
			if (!currentUser.isAuthenticated()) {
				message = "用户或密码错误, 请重试.";
				model.addAttribute("message", message);
				uptToken.clear();
			} else {
				// 验证用户是否具有进入本系统的权限
				if (userService.checkUserRole(userId)) {
					// 验证成功
					errInfo = "success";
					// 移除SESSION的验证
					session.removeAttribute(Const.SESSION_SECURITY_CODE);
					return "redirect:" + url;
				} else {// 没有本系统权限
					message = "您没有使用本系统的权限，请与管理员联系！";
					model.addAttribute("message", message);
					currentUser.logout();
					uptToken.clear();
				}
			}
		}
		return "sys/login/login";
	}

	/**
	 * @ResponseBody 请求登录，验证用户, produces = "application/json;charset=UTF-8"
	 */
	@RequestMapping(value = "/system_login")
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		String errInfo = "";

		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		String message = "";
		String username = this.getRequest().getParameter("username");
		String password = MD5.md5(this.getRequest().getParameter("password"));

		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			errInfo = "nullup"; // 缺少用户名或密码
		} else {
			// shiro加入身份验证
			UsernamePasswordToken token = new UsernamePasswordToken(username, password.toUpperCase());
			token.setRememberMe(true);
			try {
				if (!currentUser.isAuthenticated()) {
					currentUser.login(token);
				}
				// 记录登录日志
				addLog(OP_CLASS_LOGIN, OP_TYPE_LOGIN);
			} catch (UnknownAccountException uae) {
				errInfo = "usererror";// 用户名或密码有误
			} catch (IncorrectCredentialsException ice) {
				errInfo = "usererror"; // 密码错误
			} catch (LockedAccountException lae) {
				errInfo = "inactive";// 未激活
			} catch (ExcessiveAttemptsException eae) {
				errInfo = "attemptserror";// 错误次数过多
			} catch (AuthenticationException ae) {
				errInfo = "codeerror";// 验证未通过
			}
			// 验证是否登录成功
			if (!currentUser.isAuthenticated()) {
				message = "用户或密码错误, 请重试.";
				model.addAttribute("message", message);
				token.clear();
			} else {
				// 验证用户是否具有进入本系统的权限
				if (userService.checkUserRole(username)) {
					errInfo = "success"; // 验证成功
					session.removeAttribute(Const.SESSION_SECURITY_CODE);// 移除SESSION的验证
					// 登录名传送给用户添加的创建者
					HttpSession userNameSession = request.getSession();
					userNameSession.setAttribute("username", username);
					// 将当前用户加密后的密码做为令牌保存到SESSION中
					userNameSession.setAttribute(Const.USER_TOKEN, password);
					return "redirect:/portal/index";
				} else {// 没有本系统权限
					message = "您没有使用本系统的权限，请与管理员联系！";
					model.addAttribute("message", message);
					currentUser.logout();
					token.clear();
				}
			}
		}
		return "sys/login/login";
	}

	/**
	 * 帐号注销
	 * 
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpSession session) {
		// 记录登录日志
		addLog(OP_CLASS_LOGIN, OP_TYPE_LOGOUT);
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		session = request.getSession(true);
		session.removeAttribute(Const.SESSION_USER);
		session.removeAttribute(Const.SESSION_MENULIST);
		return "redirect:loginIndex.html";

	}

}
