package Lazy;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GenericService<T> {
	
	// a quantidade de registros que serão carregados
	List<T> buscaPaginada(int inicio, int fim);

	List<T> buscaPaginada(int inicio, int fim, T obj);
	
	List<T> buscaPaginada(int inicio, int fim, Map<String, Object> filters);

	List<T> buscaPaginada(int inicio, int fim, Map<String, Object> filters, T obj);
	
	List<T> buscaPaginada(Set<String> organizationTypes, String countryId, String stateId);

	// a quantidade de registros na fonte de dados
	int countLinhas();

	Object getRowKey(T mandatory);

	T getRowData(String rowKey);
}