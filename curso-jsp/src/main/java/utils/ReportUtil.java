package utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.ServletContext;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

@SuppressWarnings({"rawtypes","unchecked"})
public class ReportUtil  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public byte[] geraRelatorioExcel(List listaDados, String nome, HashMap<String, Object> params, ServletContext context) throws Exception {
		
		//cria a lista de dados q vem do nosso sql
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDados);
		
		String caminhoJasper = context.getRealPath("relatorio") + File.separator + nome + ".jasper";
		
		JasperPrint impressora = JasperFillManager.fillReport(caminhoJasper, params, jrbcds);
		
		JRExporter exporter = new JRXlsExporter(); //excel
		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressora);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		
		exporter.exportReport();
		
		return baos.toByteArray();
		
	}

	public byte[] geraRelatorioPDF(List listaDados, String nome, ServletContext context) throws Exception {
		
		//cria a lista de dados q vem do nosso sql
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDados);
		
		String caminhoJasper = context.getRealPath("relatorio") + File.separator + nome + ".jasper";
		
		JasperPrint impressora = JasperFillManager.fillReport(caminhoJasper, new HashMap(), jrbcds);
		
		return JasperExportManager.exportReportToPdf(impressora);
		
	}
	
	// com sub relatorio
	public byte[] geraRelatorioPDF(List listaDados, String nome, HashMap<String, Object> params, ServletContext context) throws Exception {
		
		//cria a lista de dados q vem do nosso sql
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDados);
		
		String caminhoJasper = context.getRealPath("relatorio") + File.separator + nome + ".jasper";
		
		JasperPrint impressora = JasperFillManager.fillReport(caminhoJasper, params, jrbcds);
		
		return JasperExportManager.exportReportToPdf(impressora);
		
	}

}
