package com.example.doma;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.springframework.core.io.Resource;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;

public class XlsDataSetLoader extends AbstractDataSetLoader {

	@Override
	protected IDataSet createDataSet(Resource resource) throws Exception {

		return new XlsDataSet(resource.getInputStream());
	}
	
}