package com.org.application.CRUD.angular;

import net.sf.jasperreports.engine.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class CrudAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudAngularApplication.class, args);
	}

	/*@Bean
	CommandLineRunner init(){
		return args -> {
			String destinationPath = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" +
					File.separator + "ReportGenerated.pdf";

			String filePath = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "templates" +
					File.separator + "report" + File.separator + "report.jrxml";

			LocalDateTime fechaActual = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

			Map<String, Object> parameters = new HashMap<>();
			parameters.put("nombreCompleto", "Ricardo Symor R.");
			parameters.put("fechaActual", formatter.format(fechaActual));
			parameters.put("imageDir", "classpath:/static/images/");

			JasperReport report = JasperCompileManager.compileReport(filePath);
			JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
			JasperExportManager.exportReportToPdfFile(print, destinationPath);
			System.out.println("Report created succesfully");

		};
	}*/

}
