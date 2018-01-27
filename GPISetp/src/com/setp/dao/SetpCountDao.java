package com.setp.dao;

import java.util.List;
import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.setp.entity.SetpCount;

@MyBatis
public interface SetpCountDao extends BaseDao<SetpCount> {

	public List<SetpCount> getDim(SetpCount setpCount);

	public List<SetpCount> org_avg_setp_num(SetpCount setpCount);

}
