package com.posrot.service.rotation;

import java.util.List;

import com.base.page.Page;
import com.base.service.BaseService;
import com.posrot.entity.Rotation;

public interface RotationService extends BaseService<Rotation> {

	public void updateStatus(Rotation rotation);

	public List<Rotation> countStatusNum(Rotation rotation);

	public List<Rotation> find_pos(Rotation rotation);

	public Page<Rotation> find_undone(Rotation rotation, Page<Rotation> page);

}
