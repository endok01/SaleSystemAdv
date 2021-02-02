package com.pci.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pci.entity.MtUser;

public class UserAccount implements UserDetails {
	private static final long serialVersionUID = 1L;
	private MtUser user;
	private Collection<GrantedAuthority> authorities;
	
	/**
	 * コンストラクタ
	 */
	protected UserAccount(){
		
	}
	public UserAccount(MtUser user,Collection<GrantedAuthority> authorities) {
		super();
		this.user =user;
		this.authorities = authorities;
	}
	
	/**
	 * 認可(ロール)リストを返却する
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	/**
	 * 登録されているパスワードを返却する
	 */
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	/**
	 * ユーザー名を返却する
	 */
	@Override
	public String getUsername() {
		return user.getUserName();
	}
	
	/**
	 * アカウントの有効期限を判定する
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * アカウントがロックされていないかを判定する
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 資格の有効期限を判定する
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * ユーザーの有効/無効を判定する
	 */
	@Override
	public boolean isEnabled() {
		// TODO 自動生成されたメソッド・スタブ
		return user.getEnabled();
	}

	/**
	 * ユーザーIDを返却する
	 */
	public String getUserCode() {
		return user.getUserCode();
	}

}
