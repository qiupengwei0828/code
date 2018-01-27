package com.setp.service.setpcount;

import java.util.List;
import org.springframework.stereotype.Service;
import com.base.service.BaseServiceImp;
import com.setp.dao.SetpCountDao;
import com.setp.entity.SetpCount;

@Service("SetpCountService")
public class SetpCountServiceImp extends BaseServiceImp<SetpCount> implements SetpCountService {

	@Override
	public List<SetpCount> getDim(SetpCount setpCount) {
		return ((SetpCountDao) baseDao).getDim(setpCount);
	}

	@Override
	public List<SetpCount> org_avg_setp_num(SetpCount setpCount) {
		return ((SetpCountDao) baseDao).org_avg_setp_num(setpCount);
	}

}
