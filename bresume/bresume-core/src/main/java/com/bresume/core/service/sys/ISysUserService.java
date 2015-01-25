package com.bresume.core.service.sys;

import com.bresume.core.common.base.service.IGenericService;
import com.bresume.core.common.exception.CoreException;
import com.bresume.core.common.exception.impl.PortalException;
import com.bresume.core.common.exception.impl.PwdNotCorrectException;
import com.bresume.core.model.entity.sys.SysUser;

public interface ISysUserService extends IGenericService<SysUser, String> {

	/**
	 * 查询详细信息
	 * 
	 * @param userName
	 * @return
	 */
	SysUser find(String userName) throws CoreException;

	/**
	 * 注册
	 * 
	 * @param merchant
	 */
	String register(SysUser user) throws CoreException;

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
	SysUser loginCheck(String loginName, String password) throws PortalException,
			PwdNotCorrectException;

	/**
	 * 修改个人信息
	 * 
	 * @param merchant
	 * @return
	 */
	void updateSelf(SysUser user) throws CoreException;

	/**
	 * 停用个人信息
	 * 
	 * @param loginName
	 */
	void inactive(String loginName) throws PortalException;

}
