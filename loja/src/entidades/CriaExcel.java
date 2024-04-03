package entidades;

import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Grafico.geral.Barradeprogresso;
import filter.EntityManagerUtil;
import repositorios.PdentcRepository;
import repositorios.PdentiRepository;
import repositorios.PdsaiiRepository;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

public class CriaExcel {

	public String ExcelFileToRead = "";
	
	@PersistenceContext(unitName = "play")
	private EntityManager manager;

	public void cabecarioexcel(Workbook wb, Sheet sheet, String[] titles, String nomearquivo, String titulo, int linha,
			Date data1, Date data2, Map<String, CellStyle> styles) throws IOException {

		Locale local1 = new Locale("pt", "BR");
		DateFormat datestring = new SimpleDateFormat("dd/MM/yyyy", local1);

		PrintSetup printSetup = sheet.getPrintSetup();
		printSetup.setLandscape(true);
		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);
		

		// title row
		Row titleRow = sheet.createRow(0);

		titleRow.setHeightInPoints(27);// altura da primeira linha
		for (int i = 0; i < titles.length; ++i) {
			Cell blankCell1 = titleRow.createCell(i);
			blankCell1.setCellStyle(styles.get("fundocabeçalho"));
		}

		for (int i = 0; i < titles.length; ++i) {
			Cell blankCell1 = titleRow.createCell(i);
			blankCell1.setCellStyle(styles.get("prog"));

		}

		Cell titulocabecario = titleRow.createCell(0);
		titulocabecario.setCellValue(titulo);
		titulocabecario.setCellStyle(styles.get("cabecalho"));
		int colunafinal = titles.length - 1;
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colunafinal));

		// linha 1
		Row linha1 = sheet.createRow(1);
		linha1.setHeightInPoints(27);
		for (int i = 0; i < 35; ++i) {
			Cell blankCell1 = linha1.createCell(i);
			blankCell1.setCellStyle(styles.get("fundocabeçalho"));
		}

		Row linha2 = sheet.createRow(2);
		linha1.setHeightInPoints(27);
		for (int i = 0; i < 35; ++i) {
			Cell blankCell1 = linha2.createCell(i);
			blankCell1.setCellStyle(styles.get("fundocabeçalho"));
		}

		Row linha3 = sheet.createRow(3);
		linha1.setHeightInPoints(27);
		for (int i = 0; i < 35; ++i) {
			Cell blankCell1 = linha3.createCell(i);
			blankCell1.setCellStyle(styles.get("fundocabeçalho"));
		}

		Row linha4 = sheet.createRow(4);
		linha1.setHeightInPoints(27);
		for (int i = 0; i < 35; ++i) {
			Cell blankCell1 = linha4.createCell(i);
			blankCell1.setCellStyle(styles.get("fundocabeçalho"));
		}
		int linhatitulos = linha - 1;
		// header row
		Row headerRow = sheet.createRow(linhatitulos);
		headerRow.setHeightInPoints(20);
		Cell headerCell;
		for (int i = 0; i < titles.length; i++) {
			headerCell = headerRow.createCell(i);
			headerCell.setCellValue(titles[i]);
			headerCell.setCellStyle(styles.get("header"));

		}

		if (data1 != null && data2 != null) {
			Row linhadata = sheet.createRow(1);
			linhadata.setHeightInPoints(15);

			Cell periodo = linhadata.createCell(1);
			periodo.setCellValue("Periodo de:");
			periodo.setCellStyle(styles.get("menortitulo"));

			Cell datade = linhadata.createCell(2);
			datade.setCellValue(datestring.format(data1));
			datade.setCellStyle(styles.get("menortituloembranco"));

			Cell dataate = linhadata.createCell(3);
			dataate.setCellValue(datestring.format(data2));
			dataate.setCellStyle(styles.get("menortituloembranco"));
		}

	}

	// ==================================================================================================

	public void criarexcelvenda(String tabela, Date datai, Date dataf) throws IOException {

		Workbook workbook = new XSSFWorkbook();
		Sheet sheetVendas = workbook.createSheet("PEDIDOVENDAS");
		List<Pdsaii> listavenda = new ArrayList<Pdsaii>();
		EntityManager manager = this.getManager();

		int rownum = 0;
		Double somatotal = 0.0, somasubtotal = 0.0;
		String[] titulos = null;
		Row row = null;
		int contador = 0;
		int cellnum = 0;
		String ped = "", formpagto = "";
		OutputStream out = null;
		ExcelFileToRead = "C:/loja/vendas.xlsx";

		Locale local1 = new Locale("pt", "BR");
		DateFormat datestring = new SimpleDateFormat("dd/MM/yyyy", local1);
		DecimalFormat df = new DecimalFormat("###,##0.00");
		DecimalFormat f = new DecimalFormat("#.##");
		Map<String, CellStyle> styles = createStyles(workbook);

		if (tabela.equals("pedidovenda")) {
			rownum = 4;
			titulos = new String[] { "DATA", "PEDIDO", "CODIGO", "CLIENTE", "PRODUTO", " DESCRIÇÂO", "UNITARIO",
					"TOTAL VENDA" };
		}

		cabecarioexcel(workbook, sheetVendas, titulos, "PEDIDOVENDAS", "RELATORIO " + "VENDAS".toUpperCase(),
				rownum, datai, dataf, styles);

		contador = rownum;
		row = sheetVendas.createRow(contador - 1);
		row.setHeightInPoints(20);
		Cell headerCell;

		for (int i = 0; i < titulos.length; i++) {
			headerCell = row.createCell(i);
			headerCell.setCellValue(titulos[i]);
			headerCell.setCellStyle(styles.get("titulos"));

		}

		PdsaiiRepository repository = new PdsaiiRepository(manager);
		listavenda = repository.relatvenda(datai, dataf);

		for (Pdsaii pdsaii : listavenda) {

			cellnum = 0;

			Boolean dif = false;

			if (ped.isEmpty()) {
				ped = pdsaii.getPedido();
			}

			if (ped.equals(pdsaii.getPedido())) {

				dif = false;
				somasubtotal += pdsaii.getVrtot();
			} else {
				ped = pdsaii.getPedido();
				dif = true;
			}

			if (dif == true) {
				/*
				 * row = sheetVendas.createRow(contador++);
				 * 
				 * Cell cellbranco = row.createCell(cellnum++);
				 * cellbranco.setCellValue("SUBTOTAL");
				 * cellbranco.setCellStyle(styles.get("somafinal"));
				 * 
				 * Cell cellbranco1 = row.createCell(cellnum++);
				 * cellbranco1.setCellStyle(styles.get("somafinal"));
				 * 
				 * Cell cellbranco2 = row.createCell(cellnum++);
				 * cellbranco2.setCellStyle(styles.get("somafinal"));
				 * 
				 * Cell cellbranco3 = row.createCell(cellnum++);
				 * cellbranco3.setCellStyle(styles.get("somafinal"));
				 * 
				 * Cell cellbranco4 = row.createCell(cellnum++);
				 * cellbranco4.setCellStyle(styles.get("somafinal"));
				 * 
				 * Cell celltextototal = row.createCell(cellnum++);
				 * celltextototal.setCellStyle(styles.get("somafinal"));
				 * 
				 * int mescla = contador - 1; sheetVendas.addMergedRegion(new
				 * CellRangeAddress(mescla, mescla, 0, 5));
				 * 
				 * Cell cellbranco5 = row.createCell(cellnum++);
				 * cellbranco5.setCellStyle(styles.get("somafinal"));
				 * 
				 * Cell cellsomatotal = row.createCell(cellnum++);
				 * cellsomatotal.setCellType(Cell.CELL_TYPE_NUMERIC);
				 * cellsomatotal.setCellValue(convertValorToMoney(somasubtotal));
				 * cellsomatotal.setCellStyle(styles.get("somafinal"));
				 */

				somasubtotal = pdsaii.getVrtot();
			}

			row = sheetVendas.createRow(contador++);

			cellnum = 0;

			Cell celldata = row.createCell(cellnum++);
			celldata.setCellValue(datestring.format(pdsaii.getEmissao()));
			celldata.setCellStyle(styles.get("itemlista"));

			Cell cellpedido = row.createCell(cellnum++);
			cellpedido.setCellValue(pdsaii.getPedido());
			cellpedido.setCellStyle(styles.get("itemlista"));

			Cell cellcodcli = row.createCell(cellnum++);
			cellcodcli.setCellValue(pdsaii.getPedc().getCliente().getCODCLI());
			cellcodcli.setCellStyle(styles.get("itemlista"));

			Cell celldesccli = row.createCell(cellnum++);
			celldesccli.setCellValue(pdsaii.getPedc().getCliente().getDESCCLI());
			celldesccli.setCellStyle(styles.get("itemlista"));

			Cell cellcodpro = row.createCell(cellnum++);
			cellcodpro.setCellValue(pdsaii.getProduto());
			cellcodpro.setCellStyle(styles.get("itemlista"));

			Cell celldescricao = row.createCell(cellnum++);
			celldescricao.setCellValue(pdsaii.getDescpro());
			celldescricao.setCellStyle(styles.get("itemlista"));

			Cell cellunitario = row.createCell(cellnum++);
			cellunitario.setCellType(Cell.CELL_TYPE_NUMERIC);
			cellunitario.setCellValue(pdsaii.getUnitario());
			cellunitario.setCellStyle(styles.get("itemlista"));

			Cell cellvrtotal = row.createCell(cellnum++);
			cellvrtotal.setCellType(Cell.CELL_TYPE_NUMERIC);
			if(pdsaii.getVrtot() > 0.0) {
			cellvrtotal.setCellValue(convertValorToMoney(pdsaii.getVrtot()));
			}else {
				cellvrtotal.setCellValue("0");
			}
			cellvrtotal.setCellStyle(styles.get("itemlista"));

			somatotal += pdsaii.getVrtot();

		}

		row = sheetVendas.createRow(contador++);

		cellnum = 0;

		Cell cellbranco = row.createCell(cellnum++);
		cellbranco.setCellValue("TOTAL");
		cellbranco.setCellStyle(styles.get("somafinal"));

		Cell cellbranco1 = row.createCell(cellnum++);
		cellbranco1.setCellStyle(styles.get("somafinal"));

		Cell cellbranco2 = row.createCell(cellnum++);
		cellbranco2.setCellStyle(styles.get("somafinal"));

		Cell cellbranco3 = row.createCell(cellnum++);
		cellbranco3.setCellStyle(styles.get("somafinal"));

		Cell cellbranco4 = row.createCell(cellnum++);
		cellbranco4.setCellStyle(styles.get("somafinal"));

		Cell celltextototal = row.createCell(cellnum++);
		celltextototal.setCellStyle(styles.get("somafinal"));

		int mescla = contador - 1;
		sheetVendas.addMergedRegion(new CellRangeAddress(mescla, mescla, 0, 5));

		Cell cellbranco5 = row.createCell(cellnum++);
		cellbranco5.setCellStyle(styles.get("somafinal"));

		Cell cellsomatotal = row.createCell(cellnum++);
		cellsomatotal.setCellType(Cell.CELL_TYPE_NUMERIC);
		cellsomatotal.setCellValue(convertValorToMoney(somatotal));
		cellsomatotal.setCellStyle(styles.get("somafinal"));

		if (tabela.equals("pedidovenda")) {
			sheetVendas.setColumnWidth(0, 12 * 256); // 30 characters wide
			sheetVendas.setColumnWidth(1, 12 * 256);
			sheetVendas.setColumnWidth(2, 12 * 256);
			sheetVendas.setColumnWidth(3, 55 * 256);
			sheetVendas.setColumnWidth(4, 15 * 256);
			sheetVendas.setColumnWidth(5, 90 * 256);
			sheetVendas.setColumnWidth(6, 12 * 256);
			sheetVendas.setColumnWidth(7, 15 * 256);
			sheetVendas.setColumnWidth(8, 12 * 256);
			sheetVendas.setColumnWidth(9, 6 * 256);
			sheetVendas.setColumnWidth(10, 12 * 256);
		}

		try {
			out = new FileOutputStream(ExcelFileToRead);
			//out = new BufferedOutputStream(new FileOutputStream(ExcelFileToRead));
			workbook.write(out);
			out.flush();
			out.close();
			JOptionPane.showMessageDialog(null, "Arquivo Excel criado com sucesso!");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Arquivo não encontrado!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro na edição do arquivo!");
		}

	}
 
	    
	    //=================================================================
	
	
	
	// ==================================================================================================

		public void criarexcelcompra(String tabela, Date datai, Date dataf) throws IOException {

			Workbook workbook = new XSSFWorkbook();
			Sheet sheetcompras = workbook.createSheet("PEDIDOCOMPRAS");
			List<Pdenti> listacompra = new ArrayList<Pdenti>();

			int rownum = 0;
			Double somatotal = 0.0, somasubtotal = 0.0;
			String[] titulos = null;
			Row row = null;
			int contador = 0;
			int cellnum = 0;
			String ped = "";
			OutputStream out = null;
			ExcelFileToRead = "C:/loja/compras.xlsx";

			Locale local1 = new Locale("pt", "BR");
			DateFormat datestring = new SimpleDateFormat("dd/MM/yyyy", local1);
			DecimalFormat df = new DecimalFormat("###,##0.00");
			DecimalFormat f = new DecimalFormat("#.##");
			Map<String, CellStyle> styles = createStyles(workbook);

			if (tabela.equals("pedidocompra")) {
				rownum = 4;
				titulos = new String[] { "DATA", "PEDIDO", "CODIGO", "FORNECEDOR", "PRODUTO", " DESCRIÇÂO", "UNITARIO",
						"TOTAL COMPRA" };
			}

			cabecarioexcel(workbook, sheetcompras, titulos, "PEDIDOCOMPRAS", "RELATORIO " + "COMPRAS".toUpperCase(),
					rownum, datai, dataf, styles);

			contador = rownum;
			row = sheetcompras.createRow(contador - 1);
			row.setHeightInPoints(20);
			Cell headerCell;

			for (int i = 0; i < titulos.length; i++) {
				headerCell = row.createCell(i);
				headerCell.setCellValue(titulos[i]);
				headerCell.setCellStyle(styles.get("titulos"));

			}

			PdentiRepository repository = new PdentiRepository(EntityManagerUtil.manager);
			listacompra = repository.relatcompra(datai, dataf);

			for (Pdenti pdenti : listacompra) {

				cellnum = 0;

				Boolean dif = false;

				if (ped.isEmpty()) {
					ped = pdenti.getPedido();
				}

				if (ped.equals(pdenti.getPedido())) {

					dif = false;
					somasubtotal += pdenti.getVrtot();
				} else {
					ped = pdenti.getPedido();
					dif = true;
				}

				if (dif == true) {
					/*
					 * row = sheetVendas.createRow(contador++);
					 * 
					 * Cell cellbranco = row.createCell(cellnum++);
					 * cellbranco.setCellValue("SUBTOTAL");
					 * cellbranco.setCellStyle(styles.get("somafinal"));
					 * 
					 * Cell cellbranco1 = row.createCell(cellnum++);
					 * cellbranco1.setCellStyle(styles.get("somafinal"));
					 * 
					 * Cell cellbranco2 = row.createCell(cellnum++);
					 * cellbranco2.setCellStyle(styles.get("somafinal"));
					 * 
					 * Cell cellbranco3 = row.createCell(cellnum++);
					 * cellbranco3.setCellStyle(styles.get("somafinal"));
					 * 
					 * Cell cellbranco4 = row.createCell(cellnum++);
					 * cellbranco4.setCellStyle(styles.get("somafinal"));
					 * 
					 * Cell celltextototal = row.createCell(cellnum++);
					 * celltextototal.setCellStyle(styles.get("somafinal"));
					 * 
					 * int mescla = contador - 1; sheetVendas.addMergedRegion(new
					 * CellRangeAddress(mescla, mescla, 0, 5));
					 * 
					 * Cell cellbranco5 = row.createCell(cellnum++);
					 * cellbranco5.setCellStyle(styles.get("somafinal"));
					 * 
					 * Cell cellsomatotal = row.createCell(cellnum++);
					 * cellsomatotal.setCellType(Cell.CELL_TYPE_NUMERIC);
					 * cellsomatotal.setCellValue(convertValorToMoney(somasubtotal));
					 * cellsomatotal.setCellStyle(styles.get("somafinal"));
					 */

					somasubtotal = pdenti.getVrtot();
				}

				row = sheetcompras.createRow(contador++);

				cellnum = 0;

				Cell celldata = row.createCell(cellnum++);
				celldata.setCellValue(datestring.format(pdenti.getEmissao()));
				celldata.setCellStyle(styles.get("itemlista"));

				Cell cellpedido = row.createCell(cellnum++);
				cellpedido.setCellValue(pdenti.getPedido());
				cellpedido.setCellStyle(styles.get("itemlista"));

				Cell cellcodcli = row.createCell(cellnum++);
				cellcodcli.setCellValue(pdenti.getPedc().getForn().getCODFOR());
				cellcodcli.setCellStyle(styles.get("itemlista"));

				Cell celldesccli = row.createCell(cellnum++);
				celldesccli.setCellValue(pdenti.getPedc().getForn().getDESCFOR());
				celldesccli.setCellStyle(styles.get("itemlista"));

				Cell cellcodpro = row.createCell(cellnum++);
				cellcodpro.setCellValue(pdenti.getProduto());
				cellcodpro.setCellStyle(styles.get("itemlista"));

				Cell celldescricao = row.createCell(cellnum++);
				celldescricao.setCellValue(pdenti.getDescpro());
				celldescricao.setCellStyle(styles.get("itemlista"));

				Cell cellunitario = row.createCell(cellnum++);
				cellunitario.setCellType(Cell.CELL_TYPE_NUMERIC);
				cellunitario.setCellValue(pdenti.getUnitario());
				cellunitario.setCellStyle(styles.get("itemlista"));

				Cell cellvrtotal = row.createCell(cellnum++);
				cellvrtotal.setCellType(Cell.CELL_TYPE_NUMERIC);
				//JOptionPane.showMessageDialog(null, "somatotal " + pdenti.getVrtot());
				if(pdenti.getVrtot() > 0.0) {
				cellvrtotal.setCellValue(convertValorToMoney(pdenti.getVrtot()));
				}else {
					cellvrtotal.setCellValue("0");
				}
				cellvrtotal.setCellStyle(styles.get("itemlista"));

				somatotal += pdenti.getVrtot();

			}

			row = sheetcompras.createRow(contador++);

			cellnum = 0;

			Cell cellbranco = row.createCell(cellnum++);
			cellbranco.setCellValue("TOTAL");
			cellbranco.setCellStyle(styles.get("somafinal"));

			Cell cellbranco1 = row.createCell(cellnum++);
			cellbranco1.setCellStyle(styles.get("somafinal"));

			Cell cellbranco2 = row.createCell(cellnum++);
			cellbranco2.setCellStyle(styles.get("somafinal"));

			Cell cellbranco3 = row.createCell(cellnum++);
			cellbranco3.setCellStyle(styles.get("somafinal"));

			Cell cellbranco4 = row.createCell(cellnum++);
			cellbranco4.setCellStyle(styles.get("somafinal"));

			Cell celltextototal = row.createCell(cellnum++);
			celltextototal.setCellStyle(styles.get("somafinal"));

			int mescla = contador - 1;
			sheetcompras.addMergedRegion(new CellRangeAddress(mescla, mescla, 0, 5));

			Cell cellbranco5 = row.createCell(cellnum++);
			cellbranco5.setCellStyle(styles.get("somafinal"));

			Cell cellsomatotal = row.createCell(cellnum++);
			cellsomatotal.setCellType(Cell.CELL_TYPE_NUMERIC);
			
			cellsomatotal.setCellValue(convertValorToMoney(somatotal));
			
			cellsomatotal.setCellStyle(styles.get("somafinal"));

			if (tabela.equals("pedidocompra")) {
				sheetcompras.setColumnWidth(0, 12 * 256); // 30 characters wide
				sheetcompras.setColumnWidth(1, 12 * 256);
				sheetcompras.setColumnWidth(2, 12 * 256);
				sheetcompras.setColumnWidth(3, 55 * 256);
				sheetcompras.setColumnWidth(4, 15 * 256);
				sheetcompras.setColumnWidth(5, 90 * 256);
				sheetcompras.setColumnWidth(6, 12 * 256);
				sheetcompras.setColumnWidth(7, 15 * 256);
				sheetcompras.setColumnWidth(8, 12 * 256);
				sheetcompras.setColumnWidth(9, 6 * 256);
				sheetcompras.setColumnWidth(10, 12 * 256);
			}

			try {
				out = new FileOutputStream(ExcelFileToRead);
				//out = new BufferedOutputStream(new FileOutputStream(ExcelFileToRead));
				workbook.write(out);
				out.flush();
				out.close();
				JOptionPane.showMessageDialog(null, "Arquivo Excel criado com sucesso!");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("Arquivo não encontrado!");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Erro na edição do arquivo!");
			}

		}
	 
		    
		    //=================================================================


	public static Map<String, CellStyle> createStyles(Workbook wb) {

		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

		CellStyle headerStyle = null;
		CellStyle linhaStyle = null;
		CellStyle somaStyle = null;
		CellStyle fundoheader = null;
		CellStyle titulop = null;
		CellStyle titulopbranco = null;
		CellStyle headercabecalho = null;
		CellStyle progrStyle = null;
		CellStyle cabecalhoStyle = null;
		Font font = null;

		font = wb.createFont();
		font.setBold(true);

		headerStyle = wb.createCellStyle();

		headerStyle.setWrapText(true);
		headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setBorderRight(BorderStyle.THIN);
		headerStyle.setBorderLeft(BorderStyle.THIN);
		headerStyle.setBorderTop(BorderStyle.THIN);
		headerStyle.setBorderBottom(BorderStyle.THIN);
		headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		styles.put("titulos", headerStyle);

		linhaStyle = wb.createCellStyle();
		linhaStyle.setWrapText(true);
		linhaStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		linhaStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		linhaStyle.setBorderRight(BorderStyle.THIN);
		linhaStyle.setBorderLeft(BorderStyle.THIN);
		linhaStyle.setBorderTop(BorderStyle.THIN);
		linhaStyle.setBorderBottom(BorderStyle.THIN);
		linhaStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		linhaStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		linhaStyle.setAlignment(HorizontalAlignment.CENTER);
		styles.put("itemlista", linhaStyle);

		somaStyle = wb.createCellStyle();
		somaStyle.setWrapText(true);
		somaStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		somaStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		somaStyle.setBorderRight(BorderStyle.THIN);
		somaStyle.setBorderLeft(BorderStyle.THIN);
		somaStyle.setBorderTop(BorderStyle.THIN);
		somaStyle.setBorderBottom(BorderStyle.THIN);
		somaStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		somaStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		somaStyle.setAlignment(HorizontalAlignment.CENTER);
		somaStyle.setFont(font);
		styles.put("somafinal", somaStyle);

		fundoheader = wb.createCellStyle();
		fundoheader.setAlignment(HorizontalAlignment.CENTER);
		fundoheader.setWrapText(true);
		fundoheader.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		fundoheader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styles.put("fundocabeçalho", fundoheader);

		Font menortitulo1 = wb.createFont();
		menortitulo1.setFontHeightInPoints((short) 10);
		menortitulo1.setFontName("Arial Narrow");
		menortitulo1.setBold(true);
		titulop = wb.createCellStyle();
		titulop.setAlignment(HorizontalAlignment.CENTER);
		titulop.setWrapText(true);
		titulop.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		titulop.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		titulop.setBorderRight(BorderStyle.THIN);
		titulop.setRightBorderColor(IndexedColors.BLACK.getIndex());
		titulop.setBorderLeft(BorderStyle.THIN);
		titulop.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		titulop.setBorderTop(BorderStyle.THIN);
		titulop.setTopBorderColor(IndexedColors.BLACK.getIndex());
		titulop.setBorderBottom(BorderStyle.THIN);
		titulop.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		titulop.setFont(menortitulo1);
		styles.put("menortitulo", titulop);

		Font menortituloembranco = wb.createFont();
		menortituloembranco.setFontHeightInPoints((short) 10);
		menortituloembranco.setFontName("Arial Narrow");
		menortituloembranco.setBold(true);
		titulopbranco = wb.createCellStyle();
		titulopbranco.setAlignment(HorizontalAlignment.CENTER);
		titulopbranco.setWrapText(true);
		titulopbranco.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		titulopbranco.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		titulopbranco.setVerticalAlignment(VerticalAlignment.CENTER);
		titulopbranco.setBorderRight(BorderStyle.THIN);
		titulopbranco.setRightBorderColor(IndexedColors.BLACK.getIndex());
		titulopbranco.setBorderLeft(BorderStyle.THIN);
		titulopbranco.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		titulopbranco.setBorderTop(BorderStyle.THIN);
		titulopbranco.setTopBorderColor(IndexedColors.BLACK.getIndex());
		titulopbranco.setBorderBottom(BorderStyle.THIN);
		titulopbranco.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		titulopbranco.setFont(menortituloembranco);
		styles.put("menortituloembranco", titulopbranco);

		Font monthFont = wb.createFont();
		monthFont.setFontHeightInPoints((short) 10);
		monthFont.setColor(IndexedColors.BLACK.getIndex());
		headercabecalho = wb.createCellStyle();
		headercabecalho.setAlignment(HorizontalAlignment.CENTER);
		headercabecalho.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		headercabecalho.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headercabecalho.setVerticalAlignment(VerticalAlignment.CENTER);
		headercabecalho.setBorderRight(BorderStyle.THIN);
		headercabecalho.setRightBorderColor(IndexedColors.BLACK.getIndex());
		headercabecalho.setBorderLeft(BorderStyle.THIN);
		headercabecalho.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		headercabecalho.setBorderTop(BorderStyle.THIN);
		headercabecalho.setTopBorderColor(IndexedColors.BLACK.getIndex());
		headercabecalho.setBorderBottom(BorderStyle.THIN);
		headercabecalho.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		headercabecalho.setFont(monthFont);
		headercabecalho.setWrapText(true);
		styles.put("header", headercabecalho);

		Font programacao = wb.createFont();
		programacao.setFontHeightInPoints((short) 12);
		programacao.setFontName("Arial");
		programacao.setBold(true);
		progrStyle = wb.createCellStyle();
		progrStyle.setAlignment(HorizontalAlignment.CENTER);
		progrStyle.setWrapText(true);
		progrStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		progrStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		progrStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		progrStyle.setBorderRight(BorderStyle.THIN);
		progrStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		progrStyle.setBorderLeft(BorderStyle.THIN);
		progrStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		progrStyle.setBorderTop(BorderStyle.THIN);
		progrStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		progrStyle.setBorderBottom(BorderStyle.THIN);
		progrStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		progrStyle.setFont(programacao);
		styles.put("prog", progrStyle);

		Font fontcabecalho = wb.createFont();
		fontcabecalho.setFontHeightInPoints((short) 12);
		fontcabecalho.setFontName("Arial");
		fontcabecalho.setBold(true);
		cabecalhoStyle = wb.createCellStyle();
		cabecalhoStyle.setAlignment(HorizontalAlignment.CENTER);
		cabecalhoStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cabecalhoStyle.setWrapText(true);
		cabecalhoStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		cabecalhoStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cabecalhoStyle.setBorderRight(BorderStyle.THIN);
		cabecalhoStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cabecalhoStyle.setBorderLeft(BorderStyle.THIN);
		cabecalhoStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cabecalhoStyle.setBorderTop(BorderStyle.THIN);
		cabecalhoStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		cabecalhoStyle.setBorderBottom(BorderStyle.THIN);
		cabecalhoStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cabecalhoStyle.setFont(fontcabecalho);
		styles.put("cabecalho", cabecalhoStyle);

		return styles;
	}

	private String convertValorToMoney(Double valor) {
		DecimalFormat df = new DecimalFormat("###,###.00");
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator(',');
		dfs.setGroupingSeparator('.');
		df.setDecimalFormatSymbols(dfs);
		return df.format(valor);
	}
	
	private EntityManager getManager() {
		EntityManager manager = EntityManagerUtil.getManager();
		
		return manager;
	}


}