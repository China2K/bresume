package com.bresume.core.service.user.impl;

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
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.CopyUtils;
import com.bresume.core.common.utils.DateUtils;
import com.bresume.core.common.utils.EncryptionUtils;
import com.bresume.core.common.utils.GeneralUtils;
import com.bresume.core.dao.user.IUserDao;
import com.bresume.core.model.entity.user.User;
import com.bresume.core.service.user.IUserService;

@Service
@Transactional
public class UserServiceImpl extends GenericService<User, String> implements
		IUserService {

	@Resource
	private IUserDao userDao;

	@Override
	public IGenericDao<User, String> getDao() {
		return userDao;
	}

	/**
	 * 激活个人信息
	 * 
	 * @param userName
	 * @throws PortalException
	 */
	@Override
	@Transactional(rollbackFor = CoreException.class)
	public void active(String userName) throws PortalException
	{

		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL, "Execute activing user info", "userName", userName);

		// 校验登录名是否存在
		if (!userDao.isNoDeleteExist("userName", userName, null))
		{

			LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL, "Login name not found error!", "Login name", userName);

			throw new PortalException(PortalErrorCode.USER_LOGIN_NAME_NOT_EXIST_ERROR, "Login name not found error!");
		}

		userDao.updateStatus(userName, UserStatus.ACTIVE);

		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL, "Finish activing user info", "userName", userName);
	}
	
	@Override
	@Transactional(rollbackFor = CoreException.class)
	public void inactive(String userName) throws PortalException
	{

		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL, "Execute activing user info", "userName", userName);

		// 校验登录名是否存在
		if (!userDao.isNoDeleteExist("userName", userName, null))
		{

			LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL, "Login name not found error!", "Login name", userName);

			throw new PortalException(PortalErrorCode.USER_LOGIN_NAME_NOT_EXIST_ERROR, "Login name not found error!");
		}

		userDao.updateStatus(userName, UserStatus.INACTIVE);

		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL, "Finish activing user info", "userName", userName);
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
	public void loginCheck(String userName, String password) throws PortalException, PwdNotCorrectException
	{
		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL, "Execute Logining", "userName", userName);

		User user = userDao.findUniqueBy("userName", userName);

		// 校验登录名是否存在
		if (GeneralUtils.isNull(user))
		{

			LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL, "Login name not found error!", "Login name", userName);

			throw new PortalException(PortalErrorCode.USER_LOGIN_NAME_NOT_EXIST_ERROR, "Login name not found error!");
		}

		if (CommonUtils.isNotEmpty(user.getLastPwdErrorTime()))
		{

			int sysPasswordUnlockTimes = 1;//TODO 后续可在数据库配置

			if (DateUtils.minusMinuteByNow(user.getLastPwdErrorTime()) < sysPasswordUnlockTimes)
			{

				// 校验密码是否已经超过最大的次数
				int sysPasswordMaxTimes = 10;//TODO 后续可在数据库配置
				if (user.getPwdErrorTimes() >= sysPasswordMaxTimes)
				{
					LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL, "Password error times exceed error!", "Error times",
							user.getPwdErrorTimes());
					int unlockMins = 1;//TODO 后续可在数据库配置
					throw new PortalException(PortalErrorCode.USER_PASSWORD_ERROR_TIMES_EXCEED_ERROR, new Object[] { sysPasswordMaxTimes,
							unlockMins }, "Password error times exceed error!");
				}

				this.checkPassword(password, user, user.getPwdErrorTimes());
			}
			//如果密码超过解封时间，则将密码的错误次数置空
			else
			{
				this.checkPassword(password, user, 0);
			}
		} else
		{
			this.checkPassword(password, user, 0);
		}

		user.setPwdErrorTimes(0);
		userDao.update(user);
	}

	/**
	 * 校验密码是否正确
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	private void checkPassword(String password, User user, int passwordErrorTimes) throws PwdNotCorrectException
	{
		// 校验密码是否正确
		if (!user.getPassword().equals(EncryptionUtils.encryptBasedMd5(password)))
		{

			LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL, "Password incorrect error!");

			// 将密码出错次数加1
			user.setPwdErrorTimes(++passwordErrorTimes);
			user.setLastPwdErrorTime(new Date());
			userDao.update(user);
			throw new PwdNotCorrectException(PortalErrorCode.USER_PASSWORD_NOT_CORRECT_ERROR, "Password incorrect error!");
		}
	}



	/**
	 * 注册或者更新时，是否有重复的值
	 * 
	 * @param user
	 * @throws PortalException
	 */
	private void checkValid(User user) throws PortalException
	{

		/**
		 * 1. 为空校验
		 */
		// 登录名为空校验
		if (GeneralUtils.isNull(user.getUserName()))
		{
			LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL, "Check user info error,login name is empty!", user.getUserName());
			throw new PortalException(PortalErrorCode.USER_LOGIN_NAME_EMPTY_ERROR, "Check user info error,login name is empty!");
		}

		// 密码为空校验
		if (GeneralUtils.isNull(user.getPassword()))
		{
			LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL, "Check user info error,password is empty!", user.getUserName());
			throw new PortalException(PortalErrorCode.USER_PASSWORD_EMPTY_ERROR, "Check user info error,password is empty!");
		}

		// 邮箱为空校验
		if (GeneralUtils.isNull(user.getEmail()))
		{
			LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL, "Check user info error,email is empty!", user.getEmail());
			throw new PortalException(PortalErrorCode.USER_EMAIL_EMPTY_ERROR, "Check user info error,email is empty!");
		}

		/**
		 * 2. 重复校验
		 */
		// 判断登录名是否重复
		if (userDao.isNoDeleteExist("userName", user.getUserName(), user.getId()))
		{

			LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL, "Check user info error,login name exists!", user.getUserName());
			throw new PortalException(PortalErrorCode.USER_LOGIN_NAME_EXIST_ERROR, "Check user info error,login name exists!");
		}

		// 判断邮箱是否重复
		if (userDao.isNoDeleteExist("email", user.getEmail(), user.getId()))
		{

			LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL, "Check user info error,email exists!", user.getUserName());
			throw new PortalException(PortalErrorCode.USER_EMAIL_EXIST_ERROR, "Check user info error,email exists!");
		}

		// 判断手机号码是否重复
		if (userDao.isNoDeleteExist("cellPhone", user.getCellPhone(), user.getId()))
		{

			LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL, "Check user info error,phone exists!", user.getUserName());
			throw new PortalException(PortalErrorCode.USER_MOBILE_PHONE_EXIST_ERROR, "Check user info error,phone exists!");
		}
	}

	/**
	 * 注册
	 * 
	 * @param user
	 */
	@Override
	@Transactional(rollbackFor = CoreException.class)
	public String register(User user) throws CoreException
	{
		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL, "Execute registering user ", user);

		// 校验输入有效性
		this.checkValid(user);

		try
		{
			LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL, "Begin to insert user data to DB", user);
			CopyUtils.copyProperty(user, user);
			Date now = new Date();
			user.setStatus(UserStatus.INTITAL.getCode());
			user.setCreatedTime(now);
			user.setIsEmailVerified(false);
			user.setIsPhoneVerified(false);
		} catch (Exception e)
		{
			LogUtils.getInstance().errorServiceSystem(LogUtils.MODULE_PORTAL, "Convert from user to user error", e, user.toString());
			throw new PortalException(PortalErrorCode.USER_LOGIN_NAME_EXIST_ERROR, "Convert from user to user error", e);
		}

		user.setPassword(EncryptionUtils.encryptBasedMd5(user.getPassword()));

		// 保存商户信息
		String id=userDao.save(user);

		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL, "Insert user data successful!", user);
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
	public String updatePassword(String userName, String oldPassword, String newPassword) throws PortalException
	{
		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL, "Execute updating user password record to DB");

		// 校验旧密码是否为空正确
		oldPassword = EncryptionUtils.encryptBasedMd5(oldPassword);
		User user = userDao.findUniqueBy("userName", userName);
		if (!user.getPassword().equals(oldPassword))
		{
			LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL, "Updating user password error,old password not correct!", userName);
			throw new PortalException(PortalErrorCode.USER_OLD_PASSWORD_NOT_CORRECT_ERROR,
					"Updating user password error,old 	password not correct!");
		}

		// 更新新密码
		newPassword = EncryptionUtils.encryptBasedMd5(newPassword);
		user.setPassword(newPassword);
		userDao.update(user);
		return newPassword;
	}


	@Override
	@Transactional(rollbackFor = CoreException.class)
	public void updateSelf(User user) throws CoreException
	{
		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL, "Execute updating user himself record to DB", user);

		// 校验输入有效性
		this.checkValid(user);

		User persist = userDao.findById(user.getId());
		persist.setCellPhone(user.getCellPhone());
		persist.setEmail(user.getEmail());
		userDao.update(persist);

		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL, "Update user himself successful!", user);
	}


	/**
	 * 查询详细信息
	 * 
	 * @param loginName
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public User find(String userName) throws CoreException
	{
		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL, "Execute finding merchant record from DB", "loginName", userName);

		User user = userDao.findUniqueBy("userName", userName);

		// 校验登录名是否存在
		if (GeneralUtils.isNull(user))
		{
			LogUtils.getInstance().errorSystem(LogUtils.MODULE_PORTAL, "Login name not found error!", "Login name", userName);
			throw new PortalException(PortalErrorCode.USER_LOGIN_NAME_NOT_EXIST_ERROR, "Login name not found error!");
		}
		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL, "Find merchant record from dao", user);

		LogUtils.getInstance().debugSystem(LogUtils.MODULE_PORTAL, "Return user", user);
		return user;
	}


}
