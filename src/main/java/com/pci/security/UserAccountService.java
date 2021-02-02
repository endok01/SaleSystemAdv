package com.pci.security;

import java.util.Collection;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pci.entity.MtUser;
import com.pci.repository.RoleRepository;
import com.pci.repository.UserRepository;

/**
 * UserDetailsService実装クラス
 */
@Service
public class UserAccountService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * ログインチェックを行うメソッド
	 */
	@Override
	public UserDetails loadUserByUsername(String usercode) throws UsernameNotFoundException {
		if(usercode == null || "".equals(usercode)) {
			throw new UsernameNotFoundException("usercode is empty");
		}
		
        MtUser ac = userRepository.findByUserCode(usercode);
        if (ac == null) {
            throw new UsernameNotFoundException("User not found: " + usercode);
        }
        
        if (!ac.isEnabled()) {
            throw new UsernameNotFoundException("User not found: " + usercode);
        }
        
        UserAccount user = new UserAccount(ac, getAuthorities(ac));

        return user;
	}
	
	/**
	 * ロール情報のリストを返すメソッド
	 * @param account
	 * @return
	 */
	private Collection<GrantedAuthority> getAuthorities(MtUser user){
		String role = user.getMtRole().getRoleName();
		switch(role) {
		case "manager":
			return AuthorityUtils.createAuthorityList("ROLE_MANAGER");
		case "user":
			return AuthorityUtils.createAuthorityList("ROLE_USER");
		default	:	// 該当するロールなし
			return null;
		}
	}
	
	@Transactional
	public void registerManager(String userCode,String username,String password,boolean enable) {
		userRepository.save(new MtUser(userCode,username,passwordEncoder.encode(password),roleRepository.findByRoleCode("2"),enable));
	}
	@Transactional
	public void registerUser(String userCode,String username,String password,boolean enable) {
		userRepository.save(new MtUser(userCode,username,passwordEncoder.encode(password),roleRepository.findByRoleCode("3"),enable));
	}
}
