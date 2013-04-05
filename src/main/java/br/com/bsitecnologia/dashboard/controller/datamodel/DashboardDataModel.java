package br.com.bsitecnologia.dashboard.controller.datamodel;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.primefaces.model.SelectableDataModel;

import br.com.bsitecnologia.dashboard.resources.db.DashboardDB;
import br.com.bsitecnologia.dashboard.util.BaseEntity;

@SuppressWarnings("unchecked")
public class DashboardDataModel<T extends BaseEntity> extends ListDataModel<T> implements SelectableDataModel<T>, Serializable {

	private static final long serialVersionUID = -4869668320073761326L;
	
	private @Inject @DashboardDB EntityManager entityManager;

	protected Class<?> entityClass;
	
	public void setList(List<T> list) {
		entityClass = list.isEmpty() ? null : list.get(0).getClass();
		super.setWrappedData(list);
	}

	@Override
	public Object getRowKey(T t) {
		return t.getId();
	}
	
	@Override
	public T getRowData(String rowKey) {
		if(rowKey != null){
			return (T) entityManager.find(entityClass, Integer.valueOf(rowKey));
		}
		return null;
	}

}