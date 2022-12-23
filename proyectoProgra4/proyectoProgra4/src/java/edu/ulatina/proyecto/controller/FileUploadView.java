package edu.ulatina.proyecto.controller;

import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FilesUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import org.primefaces.util.EscapeUtils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * @author davidgarcia
 */
@ManagedBean(name = "fileUploadView")
@SessionScoped
public class FileUploadView {

    private UploadedFile file;
    private UploadedFiles files;
    private String dropZoneText = "Drop zone p:inputTextarea demo.";
    private byte[] imagenArray;

    public FileUploadView(byte[] imagenArray) {
        this.imagenArray = imagenArray;
    }

    public void upload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Imagen ", event.getFile().getFileName() + " se esta subiendo");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        this.setImagenArray(event.getFile().getContent());
        this.setFile(event.getFile());
    }

    public FileUploadView() {
    }

    public void uploadMultiple() {
        if (files != null) {
            for (UploadedFile f : files.getFiles()) {
                FacesMessage message = new FacesMessage("Successful", f.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void handleFileUploadTextarea(FileUploadEvent event) {
        String jsVal = "PF('textarea').jq.val";
        String fileName = EscapeUtils.forJavaScript(event.getFile().getFileName());
        PrimeFaces.current().executeScript(jsVal + "(" + jsVal + "() + '\\n\\n" + fileName + " uploaded.')");
    }

    public void handleFilesUpload(FilesUploadEvent event) {
        for (UploadedFile f : event.getFiles().getFiles()) {
            FacesMessage message = new FacesMessage("Successful", f.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFiles getFiles() {
        return files;
    }

    public void setFiles(UploadedFiles files) {
        this.files = files;
    }

    public String getDropZoneText() {
        return dropZoneText;
    }

    public void setDropZoneText(String dropZoneText) {
        this.dropZoneText = dropZoneText;
    }

    public byte[] getImagenArray() {
        return imagenArray;
    }

    public void setImagenArray(byte[] imagenArray) {
        this.imagenArray = imagenArray;
    }
}
