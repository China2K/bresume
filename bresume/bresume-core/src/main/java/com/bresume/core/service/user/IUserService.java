package com.bresume.core.service.user;

import com.bresume.core.common.base.service.IGenericService;
import com.bresume.core.common.exception.CoreException;
import com.bresume.core.common.exception.impl.PortalException;
import com.bresume.core.common.exception.impl.PwdNotCorrectException;
import com.bresume.core.model.entity.user.User;

public interface IUserService extends IGenericService<User, String> {

	/**
	 * 查询详细信息
	 * 
	 * @param userName
	 * @return
	 */
	User find(String userName) throws CoreException;

	/**
	 * 注册
	 * 
	 * @param merchant
	 */
	String register(User user) throws CoreException;

	/**
	 * 激活个人信息
	 * 
	 * @param loginName
	 */
	void active(String loginName) throws CoreException;

	/**
	 * 更新信密码
	 * 
	 * @param merchant
	 * @throws PortalException
	 */
	String updatePassword(String loginName, String oldPassword,
			String newPassword) throws PortalException;
	
	/**
	 * 更新信密码
	 * 
	 * @param merchant
	 * @throws PortalException
	 */
	String updatePasswordById(String userId, String oldPassword,
			String newPassword) throws PortalException;

	/**
	 * 登陆
	 * 
	 * @param loginName
	 * @return
	 */
	User loginCheck(String loginName, String password) throws PortalException,
			PwdNotCorrectException;

	/**
	 * 修改个人信息
	 * 
	 * @param merchant
	 * @return
	 */
	void updateSelf(User user) throws CoreException;

	/**
	 * 停用个人信息
	 * 
	 * @param loginName
	 */
	void inactive(String loginName) throws PortalException;

	/**
	 * 第三方登录跳过，系统自动注册
	 * 
	 * @param User
	 */
	String registerFromAuth(User user);
	
	boolean isEmailUsed(String email,String userId);
}
