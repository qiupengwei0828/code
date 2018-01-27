package com.setp.service.setpuser;

import java.util.List;
import org.springframework.stereotype.Service;
import com.base.service.BaseServiceImp;
import com.setp.dao.SetpUserDao;
import com.setp.entity.SetpUser;

@Service("SetpUserService")
public class SetpUserServiceImp extends BaseServiceImp<SetpUser> implements SetpUserService {

	@Override
	public List<SetpUser> user_sum_setp_num(SetpUser setpUser) {
		return ((SetpUserDao) baseDao).user_sum_setp_num(setpUser);
	}

}
