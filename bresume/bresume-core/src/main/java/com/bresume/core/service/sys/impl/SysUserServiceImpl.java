package com.bresume.core.service.sys.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.common.base.service.support.GenericService;
import com.bresume.core.common.constant.enums.UserStatus;
import com.bresume.core.common.exception.CoreException;
import com.bresume.core.common.exception.PortalErrorCode;
import com.bresume.core.common.exception.impl.PortalException;
import com.bresume.core.common.exception.impl.PwdNotCorrectException;
import com.bresume.core.common.log.LogUtils;
import com.bresume.core.common.utils.CopyUtils;
import com.bresume.core.common.utils.EncryptionUtils;
import com.bresume.core.common.utils.GeneralUtils;
import com.bresume.core.dao.sys.ISysUserDao;
import com.bresume.core.model.entity.sys.SysUser;
import com.bresume.core.service.sys.ISysUserService;


@Service
@Transactional
public class SysUserServiceImpl extends GenericService<SysUser, String>
		implements ISysUserService {

	@Resource
	private ISysUserDao sysUserDao;

	@Override
	public IGenericDao<SysUser, String> getDao() {
		return sysUserDao;
	}

	/**
	 * 激活个人信息
	 * 
	 * @param userName
	 * @throws PortalException
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void active(String userName) {

		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL,
				"Execute activing user info", "userName", userName);

		SysUser sUser = sysUserDao.findUniqueBy("username", userName);

		// 校验登录名是否存在
		if (sUser != null) {
			sUser.setStatus(UserStatus.ACTIVE.getCode());
			sUser.setUpdatedTime(new Date());
			sysUserDao.update(sUser);
		}

		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL,
				"Finish activing user info", "userName", userName);
	}

	@Override
	@Transactional(rollbackFor = CoreException.class)
	public void inactive(String userName) throws PortalException {

		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL,
				"Execute activing user info", "userName", userName);

		SysUser sUser = sysUserDao.findUniqueBy("username", userName);

		// 校验登录名是否存在
		if (sUser != null) {
			sUser.setStatus(UserStatus.INACTIVE.getCode());
			sUser.setUpdatedTime(new Date());
			sysUserDao.update(sUser);
		}

		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL,
				"Finish activing user info", "userName", userName);
	}

	/**
	 * 登陆
	 * 
	 * @param userName
	 * @return
	 * @throws PortalException
	 */
	@Override
	@Transactional(noRollbackFor = { PwdNotCorrectException.class }, rollbackFor = CoreException.class)
	public SysUser loginCheck(String userName, String password)
			throws PortalException, PwdNotCorrectException {
		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL,
				"Execute Logining", "userName", userName);
		SysUser user;
//		System.out.println(userName.indexOf("@"));
		if (userName.contains("@")) {
			user = sysUserDao.findUniqueBy("email", userName);
		} else {
			user = sysUserDao.findUniqueBy("userName", userName);
		}

		// 校验登录名是否存在
		if (GeneralUtils.isNull(user)) {

			LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL,
					"Login name not found error!", "Login name", userName);

			throw new PortalException(
					PortalErrorCode.USER_LOGIN_NAME_NOT_EXIST_ERROR,
					"Login name not found error!");
		}

		this.checkPassword(password, user);
		return user;
	}

	/**
	 * 校验密码是否正确
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	private void checkPassword(String password, SysUser user)
			throws PwdNotCorrectException {
		// 校验密码是否正确
		if (!user.getPassword().equals(
				EncryptionUtils.encryptBasedMd5(password))) {

			LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL,
					"Password incorrect error!");
			throw new PwdNotCorrectException(
					PortalErrorCode.USER_PASSWORD_NOT_CORRECT_ERROR,
					"Password incorrect error!");
		}
	}

	/**
	 * 注册或者更新时，是否有重复的值
	 * 
	 * @param user
	 * @throws PortalException
	 */
	private void checkValid(SysUser user) throws PortalException {

		/**
		 * 1. 为空校验
		 */
		/*
		 * // 登录名为空校验 if (GeneralUtils.isNull(user.getUserName())) {
		 * LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL,
		 * "Check user info error,login name is empty!", user.getUserName());
		 * throw new PortalException(
		 * PortalErrorCode.USER_LOGIN_NAME_EMPTY_ERROR,
		 * "Check user info error,login name is empty!"); }
		 */
		// 密码为空校验
		if (GeneralUtils.isNull(user.getPassword())) {
			LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL,
					"Check user info error,password is empty!",
					user.getUserName());
			throw new PortalException(
					PortalErrorCode.USER_PASSWORD_EMPTY_ERROR,
					"Check user info error,password is empty!");
		}

		// 邮箱为空校验
		if (GeneralUtils.isNull(user.getEmail())) {
			LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL,
					"Check user info error,email is empty!", user.getEmail());
			throw new PortalException(PortalErrorCode.USER_EMAIL_EMPTY_ERROR,
					"Check user info error,email is empty!");
		}

		/**
		 * 2. 重复校验
		 */
		/*
		 * // 判断登录名是否重复 if (sysUserDao.isNoDeleteExist("userName",
		 * user.getUserName(), user.getId())) {
		 * 
		 * LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL,
		 * "Check user info error,login name exists!", user.getUserName());
		 * throw new PortalException(
		 * PortalErrorCode.USER_LOGIN_NAME_EXIST_ERROR,
		 * "Check user info error,login name exists!"); }
		 */

		// 判断邮箱是否重复
		if (sysUserDao.isNoDeleteExist("email", user.getEmail(), user.getId())) {

			LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL,
					"Check user info error,email exists!", user.getUserName());
			throw new PortalException(PortalErrorCode.USER_EMAIL_EXIST_ERROR,
					"Check user info error,email exists!");
		}

		/*
		 * // 判断手机号码是否重复 if (sysUserDao.isNoDeleteExist("cellPhone",
		 * user.getCellPhone(), user.getId())) {
		 * 
		 * LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL,
		 * "Check user info error,phone exists!", user.getUserName()); throw new
		 * PortalException(PortalErrorCode.USER_MOBILE_PHONE_EXIST_ERROR,
		 * "Check user info error,phone exists!"); }
		 */
	}

	/**
	 * 注册
	 * 
	 * @param user
	 */
	@Override
	@Transactional(rollbackFor = CoreException.class)
	public String register(SysUser user) throws CoreException {
		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL,
				"Execute registering user ", user);

		// 校验输入有效性
		this.checkValid(user);

		try {
			LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL,
					"Begin to insert user data to DB", user);
			CopyUtils.copyProperty(user, user);
			Date now = new Date();
			user.setStatus(UserStatus.INTITAL.getCode());
			user.setCreatedTime(now);
		} catch (Exception e) {
			LogUtils.getInstance().errorServiceSystem(LogUtils.MODULE_PORTAL,
					"Convert from user to user error", e, user.toString());
			throw new PortalException(
					PortalErrorCode.USER_LOGIN_NAME_EXIST_ERROR,
					"Convert from user to user error", e);
		}

		user.setPassword(EncryptionUtils.encryptBasedMd5(user.getPassword()));

		// 保存商户信息
		String id = sysUserDao.save(user);

		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL,
				"Insert user data successful!", user);
		return id;
	}

	/**
	 * 更新信密码
	 * 
	 * @param user
	 * @throws PortalException
	 */
	@Override
	@Transactional(rollbackFor = CoreException.class)
	public String updatePassword(String userName, String oldPassword,
			String newPassword) throws PortalException {
		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL,
				"Execute updating user password record to DB");

		// 校验旧密码是否为空正确
		oldPassword = EncryptionUtils.encryptBasedMd5(oldPassword);
		SysUser user = sysUserDao.findUniqueBy("userName", userName);
		if (!user.getPassword().equals(oldPassword)) {
			LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL,
					"Updating user password error,old password not correct!",
					userName);
			throw new PortalException(
					PortalErrorCode.USER_OLD_PASSWORD_NOT_CORRECT_ERROR,
					"Updating user password error,old 	password not correct!");
		}

		// 更新新密码
		newPassword = EncryptionUtils.encryptBasedMd5(newPassword);
		user.setPassword(newPassword);
		sysUserDao.update(user);
		return newPassword;
	}

	@Override
	@Transactional(rollbackFor = CoreException.class)
	public String updatePasswordById(String userId, String oldPassword,
			String newPassword) throws PortalException {
		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL,
				"Execute updating user password record to DB");

		// 校验旧密码是否为空正确
		oldPassword = EncryptionUtils.encryptBasedMd5(oldPassword);
		SysUser user = sysUserDao.findById(userId);
		if (!user.getPassword().equals(oldPassword)) {
			LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL,
					"Updating user password error,old password not correct!",
					userId);
			throw new PortalException(
					PortalErrorCode.USER_OLD_PASSWORD_NOT_CORRECT_ERROR,
					"Updating user password error,old 	password not correct!");
		}

		// 更新新密码
		newPassword = EncryptionUtils.encryptBasedMd5(newPassword);
		user.setPassword(newPassword);
		sysUserDao.update(user);
		return newPassword;
	}

	@Override
	@Transactional(rollbackFor = CoreException.class)
	public void updateSelf(SysUser user) throws CoreException {
		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL,
				"Execute updating user himself record to DB", user);

		// 校验输入有效性
		this.checkValid(user);

		SysUser persist = sysUserDao.findById(user.getId());
		persist.setCellPhone(user.getCellPhone());
		persist.setEmail(user.getEmail());
		sysUserDao.update(persist);

		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL,
				"Update user himself successful!", user);
	}

	/**
	 * 查询详细信息
	 * 
	 * @param loginName
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public SysUser find(String userName) throws CoreException {
		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL,
				"Execute finding merchant record from DB", "loginName",
				userName);

		SysUser user;
		if (userName.contains("@")) {
			user = sysUserDao.findUniqueBy("email", userName);
		} else {
			user = sysUserDao.findUniqueBy("userName", userName);
		}

		// 校验登录名是否存在
		if (GeneralUtils.isNull(user)) {
			LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL,
					"Login name not found error!", "Login name", userName);
			throw new PortalException(
					PortalErrorCode.USER_LOGIN_NAME_NOT_EXIST_ERROR,
					"Login name not found error!");
		}
		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL,
				"Find merchant record from dao", user);

		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL,
				"Return user", user);
		return user;
	}

}
