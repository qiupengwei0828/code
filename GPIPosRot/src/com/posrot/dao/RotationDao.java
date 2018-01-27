package com.posrot.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.base.page.Page;
import com.posrot.entity.Rotation;

@MyBatis
public interface RotationDao extends BaseDao<Rotation> {

	public void updateStatus(Rotation rotation);

	public List<Rotation> countStatusNum(Rotation rotation);

	public List<Rotation> find_pos(Rotation rotation);

	public List<Rotation> find_undone(@Param("param") Rotation rotation, Page<Rotation> page);
}
