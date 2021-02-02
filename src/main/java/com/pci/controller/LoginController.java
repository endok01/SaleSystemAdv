package com.pci.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pci.entity.MtUser;
import com.pci.repository.UserRepository;
import com.pci.security.UserAccount;

/**
 * ログインコントローラ
 * @author endo_k01
 *
 */
@Controller
@SessionAttributes("loginUser")
public class LoginController {
	
	@Autowired
	UserRepository userRepository;
	
	MtUser user;

	/**
	 * ログイン画面表示
	 * @param mv ModelAndView
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/",method=RequestMethod.GET)
	public ModelAndView topPage(ModelAndView mav) {
		mav.addObject("iserror", false);
		mav.setViewName("000login");
		return mav;
	}
	
	/**
	 * /indexで起動されるメソッド
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index",method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav,Authentication authentication) {
		mav.addObject("iserror", false);
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		authentication = SecurityContextHolder.getContext().getAuthentication();
	    if(authentication.getPrincipal() instanceof UserAccount){
	    	UserAccount user = UserAccount.class.cast(authentication.getPrincipal());
	    	MtUser loginUser = userRepository.findByUserCode(user.getUserCode());
	    	mav.addObject("loginUser", loginUser);
			for (GrantedAuthority grantedAuthority : authorities){
                    if (grantedAuthority.getAuthority().equals("ROLE_MANAGER")) {
			        	mav.setViewName("forward:/Mgr/SalesList");
			            break;
			        }else if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
			        	mav.setViewName("forward:/Staff/SalesList");	
			        	break;
			        } else {	// 一致するロールがないときはログインエラーに遷移する
			        	mav.setViewName("forward:/login-error");	
			        	break;
			        }
		    }
	    }
	    return mav;
	}
	
	@RequestMapping(value = "/login-error",method=RequestMethod.GET)
	public ModelAndView loginError(ModelAndView mav) {
		mav.addObject("iserror", true);
		mav.setViewName("000login");
		return mav;
	}
}