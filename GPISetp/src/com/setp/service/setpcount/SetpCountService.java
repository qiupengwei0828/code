package com.setp.service.setpcount;

import java.util.List;

import com.base.service.BaseService;
import com.setp.entity.SetpCount;

public interface SetpCountService extends BaseService<SetpCount> {

	public List<SetpCount> getDim(SetpCount setpCount);

	public List<SetpCount> org_avg_setp_num(SetpCount setpCount);

}
