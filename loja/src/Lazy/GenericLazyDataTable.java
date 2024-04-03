package Lazy;

import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.swing.SortOrder;

import filter.EntityManagerUtil;
import repositorios.Repository;

public class GenericLazyDataTable<T> {

	private List<T> datasource;
	private final GenericService genericService;
	private List<T> list;
	
	
	public GenericLazyDataTable(GenericService genericService) {
		this.genericService = genericService;
	}

	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
	
		EntityManager manager = EntityManagerUtil.getManager();
		Repository repository = new Repository(manager);
        
		int linhas = genericService.countLinhas();
       // this.setRowCount(linhas);
        
        return genericService.buscaPaginada(first, first + pageSize, filters);
		
	}
	
	public Object getRowKey(T mandatory) {
		return mandatory;
	}
	
	public T getRowData(String rowKey) {
		return 	(T) genericService.getRowData(rowKey);
		
	}
   
	
}
