package com.setp.service.setpuser;

import java.util.List;
import com.base.service.BaseService;
import com.setp.entity.SetpUser;

public interface SetpUserService extends BaseService<SetpUser> {

	public List<SetpUser> user_sum_setp_num(SetpUser setpUser);

}
