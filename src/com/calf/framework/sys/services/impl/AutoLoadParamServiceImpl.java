package com.calf.framework.sys.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.calf.framework.services.impl.BaseServiceImpl;
import com.calf.framework.sys.services.AutoLoadParamService;
import com.calf.framework.sys.services.ParamItemService;
import com.calf.framework.um.entity.TbSysParamItem;
import com.calf.framework.util.CodeName;
import com.calf.framework.util.Constants;

@Service("autoLoadParsmService")
public class AutoLoadParamServiceImpl extends BaseServiceImpl implements
		AutoLoadParamService {
	@Resource
	ParamItemService paramItemService;

	@PostConstruct
	@Override
	public void loadAllParams() {
		Map params = Constants.params;
		List<TbSysParamItem> paramItemList = paramItemService.findAllParamItem();
		for (TbSysParamItem item : paramItemList) {
			List<CodeName> dataList = (List<CodeName>) params.get(item.getParamKindCode());

			// 参数如果不在列表中出现，则增加到列表中
			if (dataList == null) {
				List<CodeName> putList = new ArrayList<CodeName>();
				putList.add(item.convertCodeName());
				params.put(item.getParamKindCode(), putList);
			} else {
				// 存在则添加到末尾
				dataList.add(item.convertCodeName());
			}
		}
	}

}
