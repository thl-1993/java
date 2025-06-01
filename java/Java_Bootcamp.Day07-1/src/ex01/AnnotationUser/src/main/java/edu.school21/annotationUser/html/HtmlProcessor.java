package edu.school21.annotationUser.html;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

@SupportedAnnotationTypes({"edu.school21.annotations.HtmlForm", "edu.school21.annotations.HtmlInput"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class HtmlProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(HtmlForm.class)) {
            HtmlForm htmlForm = element.getAnnotation(HtmlForm.class);
            String fileName = htmlForm.fileName();
            String action = htmlForm.action();
            String method = htmlForm.method();

            StringBuilder htmlContent = new StringBuilder();
            htmlContent.append("<form action=\"").append(action).append("\" method=\"").append(method).append("\">\n");

            // Обрабатываем все поля с аннотацией HtmlInput
            for (Element field : roundEnv.getElementsAnnotatedWith(HtmlInput.class)) {
                HtmlInput htmlInput = field.getAnnotation(HtmlInput.class);
                String type = htmlInput.type();
                String name = htmlInput.name();
                String placeholder = htmlInput.placeholder();

                htmlContent.append("\t<input type=\"").append(type).append("\" name=\"").append(name)
                        .append("\" placeholder=\"").append(placeholder).append("\">\n");
            }

            htmlContent.append("\t<input type=\"submit\" value=\"Send\">\n</form>");

            // Генерация файла
            try {
                File outputDir = new File("target/classes");
                if (!outputDir.exists()) {
                    outputDir.mkdirs();
                }

                File outputFile = new File(outputDir, fileName);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                    writer.write(htmlContent.toString());
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Generated HTML file: " + fileName);
                }
            } catch (IOException e) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Error generating HTML file: " + e.getMessage());
            }
        }
        return true;
    }
}
