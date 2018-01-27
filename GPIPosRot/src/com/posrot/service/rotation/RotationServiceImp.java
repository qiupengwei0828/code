package com.posrot.service.rotation;

import java.util.List;
import org.springframework.stereotype.Service;
import com.base.page.Page;
import com.base.service.BaseServiceImp;
import com.posrot.dao.RotationDao;
import com.posrot.entity.Rotation;

@Service("RotationService")
public class RotationServiceImp extends BaseServiceImp<Rotation> implements RotationService {

	@Override
	public void updateStatus(Rotation rotation) {
		((RotationDao) baseDao).updateStatus(rotation);
	}

	@Override
	public List<Rotation> countStatusNum(Rotation rotation) {
		return ((RotationDao) baseDao).countStatusNum(rotation);
	}

	@Override
	public List<Rotation> find_pos(Rotation rotation) {
		return ((RotationDao) baseDao).find_pos(rotation);
	}

	@Override
	public Page<Rotation> find_undone(Rotation rotation, Page<Rotation> page) {
		page.setList(((RotationDao) baseDao).find_undone(rotation, page));
		return page;
	}
}
