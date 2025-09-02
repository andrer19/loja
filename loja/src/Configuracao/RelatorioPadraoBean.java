package Configuracao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
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
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.view.JasperViewer;
import repositorios.CadcaminRepository;
import repositorios.CadempRepository;
import repositorios.UsuarioRepository;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

import beans.AcessoBean;
import beans.CadcaminBean;
import entidades.Cadcamin;
import entidades.Cademp;
import entidades.Entidadegenerica;
import entidades.Usuario;


@ManagedBean
public class RelatorioPadraoBean {

	public Cadcamin caminho = new Cadcamin();
	public CadcaminBean caminbean = new CadcaminBean();
	public String caminhoArquivoRelatorio;
	Cademp empresabean = new Cademp();
	int contador = 0, linha = 0;
	Boolean rodape = false, rodapesomas = false;
	String nota = null, nota1 = null, nota2 = null;
	Double subtotal = 0.0, subqtde = 0.0, totalgeral = 0.0, subvricm = 0.0, subvripi = 0.0;
	double somarefeicao = 0, somavrcombustvel = 0, somamanutencao = 0, somapedagio = 0, somahotel = 0, somataxi = 0,
			somatelefone = 0, mandouble = 0, somatrabalho = 0, somaparticular = 0, somaoutras = 0, totrodadokm = 0,
			somav = 0, somab = 0, somapainel = 0, somaestacionamento = 0, somacorreios = 0, somalavagem = 0,
			somatotal = 0, somakmdia = 0, somaqtde = 0, somalucro = 0, somatoalcompra = 0, subtotcompra = 0.0,
			sublucro = 0.0;

	public String ExcelFileToRead = "";

	public Integer progressobean = 0, icontagem = 0, totalReg = 0;
	Double v = 0.0;
	public Boolean mostrabarra = false;
	AcessoBean aces1 = new AcessoBean();
	

	@PostConstruct
	public void init() {
		progressobean = null;
		atualizarProgresso();
	}


	public void PDF(Map<String, Object> parametros, String jrxml, String nome) throws Exception {

		JasperPrint jasperPrint = null;
		Connection conexao = aces1.conectareport();
		caminho = caminbean.caminhofixo();
		try {

			InputStream arquivojrxml = new FileInputStream(
					new File(this.caminho.getCaminho() + "\\reps\\" + jrxml + ".jrxml"));
			JasperDesign JasperDesign = JRXmlLoader.load(arquivojrxml);
			JasperReport jasperReport = JasperCompileManager.compileReport(JasperDesign);

			jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexao);

			File file = new File(caminho.getCaminho() + "\\pdf\\");

			if (!file.exists()) {
				file.mkdirs();
			}

			String caminhoRelatorio = caminho.getCaminho() + "\\pdf\\";

			/* Carrega o arquivo */
			JRPdfExporter exporter = new JRPdfExporter();
			//exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			caminhoArquivoRelatorio = caminhoRelatorio + nome + ".pdf";
			//OutputStream outputStream = null;
			File arquivoGerado = new File(caminhoArquivoRelatorio);
			//exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(arquivoGerado));
			//SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			//configuration.setPermissions(PdfWriter.AllowCopy | PdfWriter.AllowPrinting);
			//exporter.setConfiguration(configuration);
			exporter.exportReport();
			File arquivofinal = new File(caminhoArquivoRelatorio);
			//visualizarPdf(arquivofinal);
			
		

		} catch (JRException e) {
			JOptionPane.showMessageDialog(null,"Não foi possivel gerar o RELATORIO " + e.getMessage());
		}

	}

	public void PDFSEMVISUALIZAR(Map<String, Object> parametros, String jrxml, String nome, String pasta)
			throws Exception {

		JasperPrint jasperPrint = null;
		Connection conexao = aces1.conectareport();
		caminho = caminbean.caminhofixo();
		caminhoArquivoRelatorio = "";
		try {

			InputStream arquivojrxml = new FileInputStream(
					new File("\\\\" + this.caminho.getCaminho() + "\\reps\\" + jrxml + ".jrxml"));
			JasperDesign JasperDesign = JRXmlLoader.load(arquivojrxml);
			JasperReport jasperReport = JasperCompileManager.compileReport(JasperDesign);

			jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexao);

			File file = new File("\\\\" + caminho.getCaminho() + pasta);

			if (!file.exists()) {
				file.mkdirs();
			}

			String caminhoRelatorio = "\\\\" + caminho.getCaminho() + pasta;

			/* Carrega o arquivo */
			/*JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			caminhoArquivoRelatorio = caminhoRelatorio + nome + ".pdf";
			File arquivoGerado = new File(caminhoArquivoRelatorio);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
			exporter.exportReport();
			File arquivofinal = new File(caminhoArquivoRelatorio);*/

		} catch (JRException e) {
			JOptionPane.showMessageDialog(null,"NÃO FOI POSSIVEL GERAR O ARQUIVO " + jrxml + " " + e.getMessage());
			aces1.demologger.error("NÃO FOI POSSIVEL GERAR O ARQUIVO " + jrxml + " " + e.getMessage());
		}

	}

	public void visualizarPdf(File arquivo) throws Exception {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		BufferedInputStream input = null;
		BufferedOutputStream output = null;

		try {
			// Open file.
			input = new BufferedInputStream(new FileInputStream(arquivo), 10240);

			// Init servlet response.
			response.reset();
			// lire un fichier pdf
			response.setHeader("Content-type", "application/pdf");
			response.setContentLength((int) arquivo.length());

			response.setHeader("Content-disposition", "inline; filename=" + arquivo.getName());
			// response.setHeader("Content-disposition", "attachment; filename=" +
			// arquivo.getName());

			// response.setHeader("pragma", "public");
			output = new BufferedOutputStream(response.getOutputStream(), 10240);

			// Write file contents to response.
			byte[] buffer = new byte[10240];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}

			// Finalize task.
			output.flush();

		} finally {
			// Gently close streams.

			output.close();
			input.close();
			FacesContext.getCurrentInstance().responseComplete();
		}

	}

	public static void concatPDFs(List<InputStream> streamOfPDFFiles, OutputStream outputStream, boolean paginate) {

		Document document = new Document();
		try {
			List<InputStream> pdfs = streamOfPDFFiles;
			List<PdfReader> readers = new ArrayList<PdfReader>();
			int totalPages = 0;
			Iterator<InputStream> iteratorPDFs = pdfs.iterator();

			// Create Readers for the pdfs.
			while (iteratorPDFs.hasNext()) {
				InputStream pdf = iteratorPDFs.next();
				PdfReader pdfReader = new PdfReader(pdf);
				readers.add(pdfReader);
				totalPages += pdfReader.getNumberOfPages();
			}
			// Create a writer for the outputstream
			PdfWriter writer = PdfWriter.getInstance(document, outputStream);

			document.open();
			BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
			// data

			PdfImportedPage page;
			int currentPageNumber = 0;
			int pageOfCurrentReaderPDF = 0;
			Iterator<PdfReader> iteratorPDFReader = readers.iterator();

			// Loop through the PDF files and add to the output.
			while (iteratorPDFReader.hasNext()) {
				PdfReader pdfReader = iteratorPDFReader.next();

				// Create a new page in the target for each source page.
				while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
					document.newPage();
					pageOfCurrentReaderPDF++;
					currentPageNumber++;
					// document.setPageSize(PageSize.A4);
					page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);

					cb.addTemplate(page, 0, 0);

					// Code for pagination.
					if (paginate) {
						cb.beginText();
						cb.setFontAndSize(bf, 9);
						cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "" + currentPageNumber + " of " + totalPages,
								520, 5, 0);
						cb.endText();
					}
				}
				pageOfCurrentReaderPDF = 0;
			}
			outputStream.flush();
			document.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (document.isOpen())
				document.close();
			try {
				if (outputStream != null)
					outputStream.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	public void visualizarjpg(File f) throws Exception {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		BufferedInputStream input = null;
		BufferedOutputStream output = null;

		try {

			input = new BufferedInputStream(new FileInputStream(f), 10240);

			// Init servlet response. response.reset(); // lire un fichier pdf
			response.setHeader("Content-type", "image/jpeg");
			response.setContentLength((int) f.length());

			response.setHeader("Content-disposition", "inline; filename=" + f.getName());
			// response.setHeader("pragma", "public");
			output = new BufferedOutputStream(response.getOutputStream(), 10240);

			// Write file contents to response.
			byte[] buffer = new byte[10240];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}

			// Finalize task. output.flush();

			output.flush();

		} finally { // Gently close streams.

			output.close();
			input.close();
		}

	}
	
	
	

	public InputStream getData(File file1) {

		// pdf files under src\main\resources
		File file = file1;

		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return is;

	}
	
public void visualizaireport(String nomearquivo, Map<String, Object> parametros) throws Exception {
		
		JDialog viewer = new JDialog();
		viewer.setModal(true);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		viewer.setBounds(-7, 0, (screenSize.width + 17), (screenSize.height - 30));
		File file = new File("\\\\" + aces1.caminhoireport() + "\\reps\\");
		if (!file.exists()) {
			file.mkdirs();
		}

		String report = "\\\\" + aces1.caminhoireport() + "\\reps\\" + nomearquivo + ".jrxml";
		JasperDesign JasperDesign = JRXmlLoader.load(report);
		JasperReport jasperReport = JasperCompileManager.compileReport(JasperDesign);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, aces1.conectareport());
		JasperViewer jrViewer = new JasperViewer(jasperPrint);
		jrViewer.setBounds(-7, 0, (screenSize.width + 17), (screenSize.height - 30));
		viewer.getContentPane().add(jrViewer.getContentPane());
		viewer.setVisible(true);
		
		
	}

	// ==========================cabecarioexcel==========================================

	public void cabecarioexcel(Workbook wb, Sheet sheet, String[] titles, String nomearquivo, String titulo, int linha,
			Date data1, Date data2, Map<String, CellStyle> styles) throws IOException {

		caminho = caminbean.caminhofixo();
		empresabean = aces1.empresabanco();
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
			blankCell1.setCellStyle(styles.get("fundocabecalho"));
		}

		for (int i = 0; i < titles.length; ++i) {
			Cell blankCell1 = titleRow.createCell(i);
			blankCell1.setCellStyle(styles.get("prog"));

		}

		Cell titulocabecario = titleRow.createCell(0);
		titulocabecario.setCellValue(titulo.replace("\\PLANILHAS\\", ""));
		titulocabecario.setCellStyle(styles.get("cabecalho"));
		int colunafinal = titles.length - 1;
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colunafinal));

		// linha 1
		Row linha1 = sheet.createRow(1);
		linha1.setHeightInPoints(27);
		for (int i = 0; i < 35; ++i) {
			Cell blankCell1 = linha1.createCell(i);
			blankCell1.setCellStyle(styles.get("fundocabecalho"));
		}

		Row linha2 = sheet.createRow(2);
		linha1.setHeightInPoints(27);
		for (int i = 0; i < 35; ++i) {
			Cell blankCell1 = linha2.createCell(i);
			blankCell1.setCellStyle(styles.get("fundocabecalho"));
		}

		Row linha3 = sheet.createRow(3);
		linha1.setHeightInPoints(27);
		for (int i = 0; i < 35; ++i) {
			Cell blankCell1 = linha3.createCell(i);
			blankCell1.setCellStyle(styles.get("fundocabecalho"));
		}

		Row linha4 = sheet.createRow(4);
		linha1.setHeightInPoints(27);
		for (int i = 0; i < 35; ++i) {
			Cell blankCell1 = linha4.createCell(i);
			blankCell1.setCellStyle(styles.get("fundocabecalho"));
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
	
	// ==============================estiloceluarexcel================================

	public static Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		XSSFCellStyle style;

		Font titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 15);
		titleFont.setBold(true);
		style = (XSSFCellStyle) wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFont(titleFont);
		styles.put("title", style);

		// stilo fonte programacao de visitas

		style = (XSSFCellStyle) wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setWrapText(true);
		style.setFillForegroundColor(IndexedColors.WHITE.index);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		styles.put("fundocabecalho", style);

		Font fontcabecalho = wb.createFont();
		fontcabecalho.setFontHeightInPoints((short) 12);
		fontcabecalho.setFontName("Arial");
		fontcabecalho.setBold(true);
		style = (XSSFCellStyle) wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setWrapText(true);
		XSSFColor header = new XSSFColor(new Color(156, 189, 230));
		style.setFillForegroundColor(header);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setFont(fontcabecalho);
		styles.put("cabecalho", style);

		Font fontsubtitulo = wb.createFont();
		fontsubtitulo.setFontHeightInPoints((short) 12);
		fontsubtitulo.setFontName("Arial");
		fontsubtitulo.setBold(true);
		style = (XSSFCellStyle) wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.LEFT);
		style.setWrapText(true);
		XSSFColor subtitulo = new XSSFColor(new Color(240, 230, 140));
		style.setFillForegroundColor(subtitulo);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setFont(fontsubtitulo);
		styles.put("subtitulo", style);

		Font programacao = wb.createFont();
		programacao.setFontHeightInPoints((short) 12);
		programacao.setFontName("Arial");
		programacao.setBold(true);
		style = (XSSFCellStyle) wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setWrapText(true);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setFont(programacao);
		styles.put("prog", style);

		Font menortitulo1 = wb.createFont();
		menortitulo1.setFontHeightInPoints((short) 10);
		menortitulo1.setFontName("Arial Narrow");
		menortitulo1.setBold(true);
		style = (XSSFCellStyle) wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setWrapText(true);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setFont(menortitulo1);
		styles.put("menortitulo", style);

		Font menortituloembranco = wb.createFont();
		menortituloembranco.setFontHeightInPoints((short) 10);
		menortituloembranco.setFontName("Arial Narrow");
		menortituloembranco.setBold(true);
		style = (XSSFCellStyle) wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setWrapText(true);
		style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setFont(menortituloembranco);
		styles.put("menortituloembranco", style);

		Font itemlista = wb.createFont();
		itemlista.setFontHeightInPoints((short) 10);
		itemlista.setColor(IndexedColors.BLACK.getIndex());
		style = (XSSFCellStyle) wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setFont(itemlista);
		style.setWrapText(true);
		styles.put("itemlista", style);

		Font monthFont = wb.createFont();
		monthFont.setFontHeightInPoints((short) 10);
		monthFont.setColor(IndexedColors.BLACK.getIndex());
		style = (XSSFCellStyle) wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setFont(monthFont);
		style.setWrapText(true);
		styles.put("header", style);

		Font linhapreta = wb.createFont();
		linhapreta.setFontHeightInPoints((short) 10);
		linhapreta.setFontName("Arial");
		linhapreta.setBold(true);
		linhapreta.setItalic(true);
		linhapreta.setColor(IndexedColors.WHITE.getIndex());
		style = (XSSFCellStyle) wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setWrapText(true);
		style.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setFont(linhapreta);
		styles.put("linhapreta", style);

		return styles;
	}

	public void atualizarProgresso() {
		if (progressobean == null) {
			progressobean = (int) (0);
		} else {

			progressobean = (int) (v.intValue());
		}

	}

	// fim das funï¿½oes

	public Cadcamin getCaminho() {
		return caminho;
	}

	public void setCaminho(Cadcamin caminho) {
		this.caminho = caminho;
	}

	public Integer getProgressobean() {
		// atualizarProgresso();
		return progressobean;
	}

	public void setProgressobean(Integer progressobean) {
		this.progressobean = progressobean;
	}

	public Boolean getMostrabarra() {
		return mostrabarra;
	}

	public void setMostrabarra(Boolean mostrabarra) {
		this.mostrabarra = mostrabarra;
	}

}